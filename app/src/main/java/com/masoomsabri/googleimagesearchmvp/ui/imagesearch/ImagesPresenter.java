package com.masoomsabri.googleimagesearchmvp.ui.imagesearch;

import android.support.annotation.NonNull;
import android.util.Log;

import com.masoomsabri.googleimagesearchmvp.data.ImageRepository;
import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;

import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImagesPresenter implements ImagesContractor.ImagesPresenter<ImagesContractor.ImagesView> {
    private ImagesContractor.ImagesView mImagesView;
    private ImageRepository mImagesRepository;

    public ImagesPresenter(@NonNull ImageRepository imageRepository){
        mImagesRepository = imageRepository;
    }

    @Override
    public void setQuery(String query) {
        if (!query.isEmpty()) {
            //this.query = query;
            try {
                    loadImages(true, false, query);
            }
            catch(Exception e)
            {
                Log.d("setQuery", e.getStackTrace().toString());
            }
        }
    }

    @Override
    public void loadImages(boolean loadMore, boolean forceUpdate, String query) {
        mImagesView.showImagesLoading(true);
        mImagesRepository.getImages(new ImageRepository.LoadImagesCallback() {
            @Override
            public void onImagesLoaded(List<GoogleImageSearchResults> images) {
                mImagesView.showImagesLoading(false);
                mImagesView.showImages(images);
            }
        }, loadMore, forceUpdate, query);
    }

    @Override
    public void openImagesDetails(@NonNull GoogleImageSearchResults imageClicked) {
        //Some validations
        mImagesView.showImageDetailUi(imageClicked);
    }

    @Override
    public void attachView(ImagesContractor.ImagesView mImagesView) {
        this.mImagesView = mImagesView;
    }

    @Override
    public void detachView() {
        mImagesView = null;
    }

}
