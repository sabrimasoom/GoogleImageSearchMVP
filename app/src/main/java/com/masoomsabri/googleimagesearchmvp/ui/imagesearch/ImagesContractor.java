package com.masoomsabri.googleimagesearchmvp.ui.imagesearch;

import android.support.annotation.NonNull;

import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;
import com.masoomsabri.googleimagesearchmvp.ui.common.MvpPresenter;
import com.masoomsabri.googleimagesearchmvp.ui.common.MvpView;

import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public interface ImagesContractor {
    interface ImagesPresenter<View> extends MvpPresenter<View>
    {
        void loadImages(boolean loadMore, boolean forceUpdates, String query);
        void openImagesDetails(@NonNull GoogleImageSearchResults imageClicked) ;
        void setQuery(String query);
    }

    interface  ImagesView extends MvpView
    {
        void showImages(List<GoogleImageSearchResults> images);
        void showImagesLoading(boolean loading);
        void showImageDetailUi(GoogleImageSearchResults images);
    }
}
