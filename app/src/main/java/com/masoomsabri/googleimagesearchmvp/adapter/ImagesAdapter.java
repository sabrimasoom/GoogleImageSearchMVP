package com.masoomsabri.googleimagesearchmvp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.masoomsabri.googleimagesearchmvp.R;
import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.masoomsabri.googleimagesearchmvp.GoogleImageSearchMVPApp.getContext;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImagesAdapter extends LoadMoreBaseAdapter<GoogleImageSearchResults, ImagesAdapter.ImageViewHolder> {

    private ImageItemListener mItemListener;
    private LayoutInflater inflater;

    public ImagesAdapter(List<GoogleImageSearchResults> images, ImageItemListener itemListener) {
        setList(images);
        mItemListener = itemListener;
    }

    private void setList(List<GoogleImageSearchResults> images) {
        this.data = images;
    }
    @Override
    public RecyclerView.ViewHolder onCreateDataViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View imageView = inflater.inflate(R.layout.list_posts, parent, false);
        return new ImageViewHolder(imageView, mItemListener);
    }

    @Override
    public void onBindDataViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == VIEW_TYPE_DATA) {
            bindPostViewHolder((ImageViewHolder)holder, position);
        }
    }

    private void bindPostViewHolder(final ImageViewHolder imageViewHolder, int position) {
        final GoogleImageSearchResults imageSearchResult = data.get(position);
        //final  ImageViewHolder imagesViewHolder;
        //imageViewHolder.mImageView.setImageResource();
        //Picasso.with(getContext()).load(String.valueOf(imageSearchResult.getImageUrl())).resize(120, 60).into(imageViewHolder.mImageView);

        // Lets load the thumbnail first.  This will show the image very quickly but at diminished quality
        Picasso.with(getContext())
                .load(imageSearchResult.thumbnailUrl)
                .placeholder(R.mipmap.ic_launcher)
                .into(imageViewHolder.mImageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        // Now we load the full image, and display it when it's ready, overwriting the thumbnail.
                        Picasso.with(getContext())
                                .load(String.valueOf(imageSearchResult.getImageUrl()))
                                .placeholder(imageViewHolder.mImageView.getDrawable())
                                .into(imageViewHolder.mImageView);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }

    public void replaceData(List<GoogleImageSearchResults> imageSearchResults) {
        data.clear();
        setList(imageSearchResults);
        notifyDataSetChanged();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageItemListener mItemListener;
        public ImageView mImageView;

        public ImageViewHolder(View itemView, ImageItemListener listener) {
            super(itemView);
            mItemListener = listener;
            mImageView = (ImageView) itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            GoogleImageSearchResults imageSearchResult = getItem(position);
            mItemListener.onImageClick(imageSearchResult);
        }
    }

    public interface ImageItemListener {

        void onImageClick(GoogleImageSearchResults clickedImage);

    }
}
