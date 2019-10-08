package com.open9527.code.image.compression;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.open9527.code.image.utils.CommonImageUtils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 14:27.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressImageUtil {
    private static final String TAG = "CompressImageUtil";
    private CompressConfig config;
    private Context context;

    public CompressImageUtil(Context context, CompressConfig config) {
        this.context = context;
        this.config = config;
        CommonImageUtils.showLogInfo(TAG, "Image compression config---->" + config.toString());
    }

    public void compress(String imagePath, CompressResultListener listener) {

        CommonImageUtils.showLogInfo(TAG, "Image oriinalPath---->" + imagePath);
        if (config.isEnableCompressPixel()) {
            //像素压缩
            compressImageByPixel(imagePath, listener);
        } else {
            if (getFileByPath(imagePath) != null) {
                compressImageByQuality(getFileByPath(imagePath), listener);
            } else {
                listener.onCompressFailed(imagePath, "imagePath is null !");
            }
        }
    }

    //根据像素压缩
    private void compressImageByPixel(String imagePath, CompressResultListener listener) {
        //创建压缩图片
        File compressFile = CachePathUtils.getCompressCacheFile(TextUtils.isEmpty(config.getCacheDir()) ? "" : config.getCacheDir());
        //获取bitmap
        Bitmap bitmap = getBitmap(imagePath, config.getMaxPixel(), config.getMaxPixel());
        if (bitmap != null) {
            //根据bitmap大小进行像素压缩
            Bitmap compressBitmap = compressBySampleSize(bitmap, config.getMaxPixel(), config.getMaxPixel(), false);
            if (compressBitmap != null) {
                //保存图片
                boolean isSave = save(compressBitmap, compressFile, Bitmap.CompressFormat.JPEG, false);
                CommonImageUtils.showLogInfo(TAG, "Image compression save!  ----->" + isSave);
                if (config.isEnableQualityCompress()) {
                    //再根据质量压缩
                    if (getFileByPath(imagePath) != null) {
                        compressImageByQuality(compressFile, listener);
                    } else {
                        //压缩失败
                        listener.onCompressFailed(imagePath, "imagePath is null !");
                    }
                } else {
                    //压缩成功
                    listener.onCompressSuccess(compressFile.getAbsolutePath());
                }
            } else {
                //压缩失败
                listener.onCompressFailed(imagePath, "compressBitmap is null !");
            }

        } else {
            //压缩失败
            listener.onCompressFailed(imagePath, "bitmap is null !");
        }
    }


    //根据质量压缩
    private void compressImageByQuality(File file, CompressResultListener listener) {

        //获取bitmap
        Bitmap bitmap = getBitmap(file.getAbsolutePath(), config.getMaxPixel(), config.getMaxPixel());
        //根据bitmap进行像素压缩
        Bitmap compressBitmap = compressByQuality(bitmap, config.getMaxSize(), false);
        //保存图片
        boolean isSave = save(compressBitmap, file, Bitmap.CompressFormat.JPEG, false);
        if (isSave) {
            //压缩成功
            listener.onCompressSuccess(file.getAbsolutePath());
        } else {
            //压缩失败
            listener.onCompressFailed(file.getAbsolutePath(), "quality compression failed !");
        }
    }


    /**
     * Return bitmap.
     *
     * @param filePath  The path of file.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @return bitmap
     */
    public static Bitmap getBitmap(final String filePath, final int maxWidth, final int maxHeight) {
        if (isSpace(filePath)) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
//        options.inPreferredConfig = Bitmap.Config.RGB_565;
//        options.inDither = true;
        try {
            Bitmap bitmap = BitmapFactory.decodeFile(filePath, options);
            if (bitmap != null) {
                return bitmap;
            }
        } catch (Exception e) {
            CommonImageUtils.showLogInfo(TAG, e.getMessage() + e.toString() + e.getLocalizedMessage());
        }
        return null;
    }


    /**
     * Return bitmap.
     *
     * @param data      The data.
     * @param offset    The offset.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @return bitmap
     */
    public static Bitmap getBitmap(final byte[] data,
                                   final int offset,
                                   final int maxWidth,
                                   final int maxHeight) {
        if (data.length == 0) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(data, offset, data.length, options);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeByteArray(data, offset, data.length, options);
    }


    /**
     * Return the compressed bitmap using sample size.
     *
     * @param src       The source of bitmap.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @param recycle   True to recycle the source of bitmap, false otherwise.
     * @return the compressed bitmap
     */
    public static Bitmap compressBySampleSize(final Bitmap src,
                                              final int maxWidth,
                                              final int maxHeight,
                                              final boolean recycle) {
        if (isEmptyBitmap(src)) return null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
        options.inSampleSize = calculateInSampleSize(options, maxWidth, maxHeight);
        options.inJustDecodeBounds = false;
        if (recycle && !src.isRecycled()) src.recycle();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
    }

    private static boolean isEmptyBitmap(final Bitmap src) {
        return src == null || src.getWidth() == 0 || src.getHeight() == 0;
    }


    private static boolean isSpace(final String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Return the sample size.
     *
     * @param options   The options.
     * @param maxWidth  The maximum width.
     * @param maxHeight The maximum height.
     * @return the sample size
     */
    private static int calculateInSampleSize(final BitmapFactory.Options options,
                                             final int maxWidth,
                                             final int maxHeight) {
        int height = options.outHeight;
        int width = options.outWidth;
        int inSampleSize = 1;
        while (height > maxHeight || width > maxWidth) {
            height >>= 1;
            width >>= 1;
            inSampleSize <<= 1;
        }
        return inSampleSize;
    }


    /**
     * Return the compressed bitmap using quality.
     *
     * @param src         The source of bitmap.
     * @param maxByteSize The maximum size of byte.
     * @param recycle     True to recycle the source of bitmap, false otherwise.
     * @return the compressed bitmap
     */
    public static Bitmap compressByQuality(final Bitmap src,
                                           final long maxByteSize,
                                           final boolean recycle) {
        if (isEmptyBitmap(src) || maxByteSize <= 0) return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes;
        if (baos.size() <= maxByteSize) {
            bytes = baos.toByteArray();
        } else {
            baos.reset();
            src.compress(Bitmap.CompressFormat.JPEG, 0, baos);
            if (baos.size() >= maxByteSize) {
                bytes = baos.toByteArray();
            } else {
                // find the best quality using binary search
                int st = 0;
                int end = 100;
                int mid = 0;
                while (st < end) {
                    mid = (st + end) / 2;
                    baos.reset();
                    src.compress(Bitmap.CompressFormat.JPEG, mid, baos);
                    int len = baos.size();
                    if (len == maxByteSize) {
                        break;
                    } else if (len > maxByteSize) {
                        end = mid - 1;
                    } else {
                        st = mid + 1;
                    }
                }
                if (end == mid - 1) {
                    baos.reset();
                    src.compress(Bitmap.CompressFormat.JPEG, st, baos);
                }
                bytes = baos.toByteArray();
            }
        }
        if (recycle && !src.isRecycled()) src.recycle();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


    /**
     * Save the bitmap.
     *
     * @param src     The source of bitmap.
     * @param file    The file.
     * @param format  The format of the image.
     * @param recycle True to recycle the source of bitmap, false otherwise.
     * @return {@code true}: success<br>{@code false}: fail
     */
    public static boolean save(final Bitmap src,
                               final File file,
                               final Bitmap.CompressFormat format,
                               final boolean recycle) {
        if (isEmptyBitmap(src) || !createFileByDeleteOldFile(file)) return false;
        OutputStream os = null;
        boolean ret = false;
        try {
            os = new BufferedOutputStream(new FileOutputStream(file));
            ret = src.compress(format, 100, os);
            if (recycle && !src.isRecycled()) src.recycle();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ret;
    }

    private static File getFileByPath(final String filePath) {
        return isSpace(filePath) ? null : new File(filePath);
    }

    private static boolean createFileByDeleteOldFile(final File file) {
        if (file == null) return false;
        if (file.exists() && !file.delete()) return false;
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean createOrExistsDir(final File file) {
        return file != null && (file.exists() ? file.isDirectory() : file.mkdirs());
    }


    /**
     * Return the bytes in file by channel.
     *
     * @param file The file.
     * @return the bytes in file
     */
    public static byte[] readFile2BytesByChannel(final File file) {
        if (!isFileExists(file)) return null;
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(file, "r").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) fc.size());
            while (true) {
                if (!((fc.read(byteBuffer)) > 0)) break;
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static boolean isFileExists(final File file) {
        return file != null && file.exists();
    }


    /**
     * Create a file if it doesn't exist, otherwise do nothing.
     *
     * @param file The file.
     * @return {@code true}: exists or creates successfully<br>{@code false}: otherwise
     */
    public static boolean createOrExistsFile(final File file) {
        if (file == null) return false;
        if (file.exists()) return file.isFile();
        if (!createOrExistsDir(file.getParentFile())) return false;
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean createOrExistsFile(final String filePath) {
        return createOrExistsFile(getFileByPath(filePath));
    }
}
