package com.masoomsabri.googleimagesearchmvp.networking.services;

import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;

import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public interface ImageServiceAPI {
    interface ImageServiceCallback<T> {

        void onLoaded(T posts);

    }
    void getImages(ImageServiceCallback<List<GoogleImageSearchResults>> callback, int page, String query);
    void getImages(ImageServiceCallback<List<GoogleImageSearchResults>> callback, String query);
}
