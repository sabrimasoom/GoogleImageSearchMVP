package com.masoomsabri.googleimagesearchmvp.networking.services;

import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;

import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */
//We can implement to retrieve data from database.
public class ImageRealmAPIImp implements ImageServiceAPI {

    @Override
    public void getImages(final ImageServiceCallback<List<GoogleImageSearchResults>> callback, int page, String query)
    {

    }
    @Override
    public void getImages(final ImageServiceCallback<List<GoogleImageSearchResults>> callback, String query)
    {
        getImages(callback, 0, query);
    }
}
