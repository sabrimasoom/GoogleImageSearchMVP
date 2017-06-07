package com.masoomsabri.googleimagesearchmvp.data;

import android.support.annotation.NonNull;

import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;

import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public interface ImageRepository {
    interface LoadImagesCallback {

        void onImagesLoaded(List<GoogleImageSearchResults> images);
    }

    void getImages(@NonNull LoadImagesCallback callback, boolean loadMore, boolean forceUpdate, String query);
}
