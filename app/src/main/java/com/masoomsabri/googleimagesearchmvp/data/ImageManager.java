package com.masoomsabri.googleimagesearchmvp.data;

import android.support.annotation.NonNull;

import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;
import com.masoomsabri.googleimagesearchmvp.networking.services.ImageServiceAPI;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImageManager implements ImageRepository {
    private ImageServiceAPI mImageServiceApi;
    private List<GoogleImageSearchResults> mImages = new ArrayList<>();

    public ImageManager(@NonNull ImageServiceAPI imageServiceAPI)
    {
        this.mImageServiceApi = imageServiceAPI;
    }
    @Override
    public void getImages(@NonNull final LoadImagesCallback callback, boolean loadMore, boolean forceUpdate, String query) {
        if (forceUpdate) {
            mImages.clear();
        }
        if (!loadMore && !mImages.isEmpty()) {
            callback.onImagesLoaded(mImages);
        } else {
            mImageServiceApi.getImages(new ImageServiceAPI.ImageServiceCallback<List<GoogleImageSearchResults>>() {
                @Override
                public void onLoaded(List<GoogleImageSearchResults> images) {
                    mImages.addAll(new ArrayList<>(images));
                    callback.onImagesLoaded(mImages);
                }
            }, mImages.size(), query);
        }
    }

    public void switchAPILayer(@NonNull ImageServiceAPI imageServiceAPI) {
        this.mImageServiceApi = imageServiceAPI;
    }
}
