package com.masoomsabri.googleimagesearchmvp.ui.common;

/**
 * Created by masoomsabri on 6/3/17.
 */

public interface MvpPresenter<V> {

    void attachView(V view);
    void detachView();

}
