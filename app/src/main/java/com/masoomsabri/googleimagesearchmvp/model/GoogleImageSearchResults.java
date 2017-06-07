package com.masoomsabri.googleimagesearchmvp.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class GoogleImageSearchResults {
    public String title;
    public String snippet;
    public String contextLink;
    // Image Properties
    public String imageUrl;
    public int imageWidth;
    public int imageHeight;
    // Thumbnail Properties
    public String thumbnailUrl;
    public int thumbnailWidth;
    public int thumbnailHeight;

    public GoogleImageSearchResults()
    {

    }

    public static ArrayList<GoogleImageSearchResults> parseJSON(JSONObject response) throws JSONException {
        ArrayList<GoogleImageSearchResults> results = new ArrayList<>();

        JSONArray itemsJSON = response.optJSONArray("items");
        if (itemsJSON != null) {
            for (int i = 0; i < itemsJSON.length(); i++) {
                JSONObject imageJSON = itemsJSON.getJSONObject(i);
                GoogleImageSearchResults image = new GoogleImageSearchResults();
                image.title = imageJSON.getString("htmlTitle");
                image.snippet = imageJSON.getString("snippet");
                image.imageUrl = imageJSON.getString("link");
                image.imageWidth = imageJSON.getJSONObject("image").getInt("width");
                image.imageHeight = imageJSON.getJSONObject("image").getInt("height");
                image.contextLink = imageJSON.getJSONObject("image").getString("contextLink");
                image.thumbnailUrl = imageJSON.getJSONObject("image").getString("thumbnailLink");
                image.thumbnailWidth = imageJSON.getJSONObject("image").getInt("thumbnailWidth");
                image.thumbnailHeight = imageJSON.getJSONObject("image").getInt("thumbnailHeight");
                results.add(image);
            }
        }

        return results;
    }

    public GoogleImageSearchResults(String title, String snippet, String contextLink, String imageUrl, int imageWidth, int imageHeight, String thumbnailUrl, int thumbnailWidth, int thumbnailHeight)
    {
        this.title = title;
        this.snippet = snippet;
        this.contextLink = contextLink;
        this.imageUrl = imageUrl;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.thumbnailUrl = thumbnailUrl;
        this.thumbnailWidth = thumbnailWidth;
        this.thumbnailHeight = thumbnailHeight;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getContextLink() {
        return contextLink;
    }

    public void setContextLink(String contextLink) {
        this.contextLink = contextLink;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageWidth() {
        return imageWidth;
    }

    public void setImageWidth(int imageWidth) {
        this.imageWidth = imageWidth;
    }

    public int getImageHeight() {
        return imageHeight;
    }

    public void setImageHeight(int imageHeight) {
        this.imageHeight = imageHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }
}
