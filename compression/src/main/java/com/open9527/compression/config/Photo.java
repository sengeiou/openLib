package com.open9527.compression.config;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by     : open9527
 * Created times  : on 2019/9/30 13:34.
 * E-Mail Address ：open_9527@163.com.
 * DESC :描述文件.
 */
public class Photo implements Serializable {

    //原始路径
    private String oriinalPath;
    //是否压缩过
    private boolean compressed;
    //压缩后路径
    private String compressPath;

    public Photo(String oriinalPath) {
        this.oriinalPath = oriinalPath;
    }

    public String getOriinalPath() {
        return oriinalPath;
    }

    public void setOriinalPath(String oriinalPath) {
        this.oriinalPath = oriinalPath;
    }

    public boolean getCompressed() {
        return compressed;
    }

    public void setCompressed(boolean compressed) {
        this.compressed = compressed;
    }

    public String getCompressPath() {
        return compressPath;
    }

    public void setCompressPath(String compressPath) {
        this.compressPath = compressPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(oriinalPath, photo.oriinalPath) &&
                Objects.equals(compressed, photo.compressed) &&
                Objects.equals(compressPath, photo.compressPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(oriinalPath, compressed, compressPath);
    }
}
