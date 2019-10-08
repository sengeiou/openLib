package com.open9527.code.image.compression;


/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 12:43.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class CompressConfig {
    //最小像素不压缩
    private int unCompressMinPixel = 1000;
    //标准像素不压缩
    private int unCompressNormalPixel = 2000;
    //长宽不超过最大像素(单位px)
    private int MaxPixel = 1200;
    //压缩到的最大大小(单位B)
    private int MaxSize = 2 * 1024;
    //是否启用像素压缩
    private boolean enableCompressPixel = true;
    //是否启用质量压缩
    private boolean enableQualityCompress = true;
    //是否保留源文件
    private boolean enableReserveRaw = true;
    //压缩后缓存图片目录,非文件路径
    private String cacheDir;
    //是否显示压缩进度条
    private boolean showCompressDialog;

    public static CompressConfig getDefaultConfig() {
        return new CompressConfig();
    }

    public CompressConfig() {
    }

    public int getUnCompressMinPixel() {
        return unCompressMinPixel;
    }

    public void setUnCompressMinPixel(int unCompressMinPixel) {
        this.unCompressMinPixel = unCompressMinPixel;
    }

    public int getUnCompressNormalPixel() {
        return unCompressNormalPixel;
    }

    public void setUnCompressNormalPixel(int unCompressNormalPixel) {
        this.unCompressNormalPixel = unCompressNormalPixel;
    }

    public int getMaxPixel() {
        return MaxPixel;
    }

    public void setMaxPixel(int maxPixel) {
        MaxPixel = maxPixel;
    }

    public int getMaxSize() {
        return MaxSize;
    }

    public void setMaxSize(int maxSize) {
        MaxSize = maxSize;
    }

    public boolean isEnableCompressPixel() {
        return enableCompressPixel;
    }

    public void setEnableCompressPixel(boolean enableCompressPixel) {
        this.enableCompressPixel = enableCompressPixel;
    }

    public boolean isEnableQualityCompress() {
        return enableQualityCompress;
    }

    public void setEnableQualityCompress(boolean enableQualityCompress) {
        this.enableQualityCompress = enableQualityCompress;
    }

    public boolean isEnableReserveRaw() {
        return enableReserveRaw;
    }

    public void setEnableReserveRaw(boolean enableReserveRaw) {
        this.enableReserveRaw = enableReserveRaw;
    }

    public String getCacheDir() {
        return cacheDir;
    }

    public void setCacheDir(String cacheDir) {
        this.cacheDir = cacheDir;
    }

    public boolean isShowCompressDialog() {
        return showCompressDialog;
    }

    public void setShowCompressDialog(boolean showCompressDialog) {
        this.showCompressDialog = showCompressDialog;
    }

    @Override
    public String toString() {
        return "CompressConfig{" +
                "unCompressMinPixel=" + unCompressMinPixel +
                ", unCompressNormalPixel=" + unCompressNormalPixel +
                ", MaxPixel=" + MaxPixel +
                ", MaxSize=" + MaxSize +
                ", enableCompressPixel=" + enableCompressPixel +
                ", enableQualityCompress=" + enableQualityCompress +
                ", enableReserveRaw=" + enableReserveRaw +
                ", cacheDir='" + cacheDir + '\'' +
                ", showCompressDialog=" + showCompressDialog +
                '}';
    }

    public static CompressConfig.Bulider builder() {
        return new Bulider();
    }

    public CompressConfig(Bulider builder) {
        this.unCompressMinPixel = builder.unCompressMinPixel;
        this.unCompressNormalPixel = builder.unCompressNormalPixel;
        this.MaxPixel = builder.MaxPixel;
        this.MaxSize = builder.MaxSize;
        this.enableCompressPixel = builder.enableCompressPixel;
        this.enableQualityCompress = builder.enableQualityCompress;
        this.enableReserveRaw = builder.enableReserveRaw;
        this.cacheDir = builder.cacheDir;
        this.showCompressDialog = builder.showCompressDialog;
    }


    public static class Bulider {
        //最小像素不压缩
        private int unCompressMinPixel = 1000;
        //标准像素不压缩
        private int unCompressNormalPixel = 2000;
        //长宽不超过最大像素(单位px)
        private int MaxPixel = 1200;
        //压缩到的最大大小(单位B)
        private int MaxSize = 2 * 1024;
        //是否启用像素压缩
        private boolean enableCompressPixel = true;
        //是否启用质量压缩
        private boolean enableQualityCompress = true;
        //是否保留源文件
        private boolean enableReserveRaw = true;
        //压缩后缓存图片目录,非文件路径
        private String cacheDir;
        //是否显示压缩进度条
        private boolean showCompressDialog;


        public Bulider setUnCompressMinPixel(int unCompressMinPixel) {
            this.unCompressMinPixel = unCompressMinPixel;
            return this;
        }

        public Bulider setUnCompressNormalPixel(int unCompressNormalPixel) {
            this.unCompressNormalPixel = unCompressNormalPixel;
            return this;
        }

        public Bulider setMaxPixel(int MaxPixel) {
            this.MaxPixel = MaxPixel;
            return this;
        }

        public Bulider setMaxSize(int MaxSize) {
            this.MaxSize = MaxSize;
            return this;
        }

        public Bulider setEnableCompressPixel(boolean enableCompressPixel) {
            this.enableCompressPixel = enableCompressPixel;
            return this;
        }

        public Bulider setEnableQualityCompress(boolean enableQualityCompress) {
            this.enableQualityCompress = enableQualityCompress;
            return this;
        }

        public Bulider setEnableReserveRaw(boolean enableReserveRaw) {
            this.enableReserveRaw = enableReserveRaw;
            return this;
        }

        public Bulider setCacheDir(String cacheDir) {
            this.cacheDir = cacheDir;
            return this;
        }

        public Bulider setShowCompressDialog(boolean showCompressDialog) {
            this.showCompressDialog = showCompressDialog;
            return this;
        }

        public CompressConfig create() {
            return new CompressConfig(this);
        }
    }
}
