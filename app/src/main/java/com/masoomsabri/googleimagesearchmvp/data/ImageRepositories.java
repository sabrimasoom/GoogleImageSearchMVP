package com.masoomsabri.googleimagesearchmvp.data;

import android.support.annotation.NonNull;

import com.masoomsabri.googleimagesearchmvp.networking.services.ImageRealmAPIImp;
import com.masoomsabri.googleimagesearchmvp.networking.services.ImageServiceAPI;
import com.masoomsabri.googleimagesearchmvp.networking.services.ImageServiceAPIImp;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImageRepositories {
    private static ImageRepository repository = null;

    private ImageRepositories() {
    }

    public synchronized static ImageRepository getImageManager(@NonNull ImageServiceAPI imageServiceAPI) {
        if (repository == null) {
            repository = new ImageManager(imageServiceAPI);
        }
        return repository;
    }

    public synchronized static void changeOffLineRepository(boolean online) {
        ((ImageManager) repository).switchAPILayer(online ? new ImageServiceAPIImp() : new ImageRealmAPIImp());
    }
}
