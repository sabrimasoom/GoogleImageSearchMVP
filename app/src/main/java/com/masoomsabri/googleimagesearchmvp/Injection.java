package com.masoomsabri.googleimagesearchmvp;

import com.masoomsabri.googleimagesearchmvp.data.ImageRepositories;
import com.masoomsabri.googleimagesearchmvp.data.ImageRepository;
import com.masoomsabri.googleimagesearchmvp.networking.services.ImageServiceAPIImp;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class Injection {

    public static ImageRepository provideImagesRepository() {
        //We can switch the repository based on online and offline.
        //return ImageRepositories.getImageManager(API.isConnected() ? new ImageServiceAPIImp() : new ImageRealmAPIImp());
        return ImageRepositories.getImageManager(new ImageServiceAPIImp());
    }

}
