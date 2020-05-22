package com.open9527.code.lib.model;

import android.os.Bundle;
import android.os.Parcelable;

public class CommonKey {

    public interface IBundleKey {
        String COMMON_BUNDLE_KEY = "common_bundle_key";

        String PHOTO_LIST = "photo_list";
        String PHOTO = "photo";
    }

    public static Bundle createBundle(Object object) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(CommonKey.IBundleKey.COMMON_BUNDLE_KEY, (Parcelable) object);
        return bundle;
    }
}
