package com.masoomsabri.googleimagesearchmvp.ui.imagesearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masoomsabri.googleimagesearchmvp.Injection;
import com.masoomsabri.googleimagesearchmvp.R;
import com.masoomsabri.googleimagesearchmvp.adapter.ImagesAdapter;
import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;
import com.masoomsabri.googleimagesearchmvp.ui.BaseFragment;
import com.masoomsabri.googleimagesearchmvp.ui.custom.EndlessRecyclerOnScrollListener;
import com.masoomsabri.googleimagesearchmvp.ui.imagedetail.ImageDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImagesFragment extends BaseFragment<ImagesContractor.ImagesPresenter>
        implements ImagesContractor.ImagesView, ImagesAdapter.ImageItemListener {

    private RecyclerView rvImages;
    private SwipeRefreshLayout swlMain;
    private ImagesAdapter mImageAdapter;
    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener;
    private String query;

    public static ImagesFragment newInstance() {
        return new ImagesFragment();
    }

    // Setters
    public void setQuery(String query) {
        if (!query.isEmpty()) {
            this.query = query;
            try {
                if (mPresenter == null) {
                    mPresenter = new ImagesPresenter(Injection.provideImagesRepository());
                }
                mPresenter.setQuery(query);
            }
            catch(Exception e)
            {
                Log.d("setQuery", e.getStackTrace().toString());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = null;
        try {
        rootView = inflater.inflate(R.layout.fragment_image_search, container, false);

            rvImages = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            rvImages = (RecyclerView) rootView.findViewById(R.id.recyclerView);
            swlMain = (SwipeRefreshLayout) rootView.findViewById(R.id.srl_main);

            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
            rvImages.setHasFixedSize(true);
            rvImages.setLayoutManager(linearLayoutManager);
            mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener(linearLayoutManager) {
                @Override
                public void onLoadMore(int current_page) {
                    if (!mImageAdapter.isLoading()) {
                        mPresenter.loadImages(true, false, query);
                    }
                }
            };
            rvImages.addOnScrollListener(mEndlessRecyclerOnScrollListener);
            swlMain.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    mEndlessRecyclerOnScrollListener.reset();
                    mPresenter.loadImages(false, true, query);
                }
            });
        }
        catch(Exception e)
        {
            Log.d("rootView", e.getStackTrace().toString());
        }

        return rootView;
    }

   @Override
    public void showImagesLoading(boolean loading) {
        if (mImageAdapter != null) mImageAdapter.setLoading(loading);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.loadImages(false, false, query);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //To be implement for testing
        //mPresenter = new ImagesPresenter(Injection.provideImagesRepository());//calling Mocking repository for testing
        //mPresenter = new ImagesPresenter(Injection.provideImagesRepository());
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

   @Override
    public void showImages(List<GoogleImageSearchResults> images) {
        swlMain.setRefreshing(false);
        if (mImageAdapter == null) {
            mImageAdapter = new ImagesAdapter(new ArrayList<>(images), this);
            rvImages.setAdapter(mImageAdapter);
        }
        mImageAdapter.setLoading(false);
        mImageAdapter.replaceData(new ArrayList<GoogleImageSearchResults>(images));
    }

    @Override
    public void showImageDetailUi(GoogleImageSearchResults imageSearhResult) {
        Intent intent = new Intent(getActivity(), ImageDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onImageClick(GoogleImageSearchResults clickedImage) {
        mPresenter.openImagesDetails(clickedImage);
    }



}
