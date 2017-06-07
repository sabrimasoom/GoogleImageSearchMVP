package com.masoomsabri.googleimagesearchmvp.networking.services;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.masoomsabri.googleimagesearchmvp.model.GoogleImageSearchResults;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;
/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImageServiceAPIImp implements ImageServiceAPI {

    // Constant
    private static final String BASE_URL = "https://www.googleapis.com/customsearch/v1";
    private static final String API_KEY = "AIzaSyCQD8pRbdSoCRKV3tRDJGIulrfvRdrfBjU";
    private static final String API_CX = "011036218402620194086:h7fletre3oq";

    // Private Vars
    private String query;
    // Setters
    public void setQuery(String query) {
        this.query = query;
    }

    // Member Functions
    private String generateQueryString() {
        return generateQueryString(0);
    }

    private String generateQueryString(int page) {
        String uri = "";
        String start = "";
        //String safe = "medium";

        if (page > 0) {
            start = String.format("&start=%d", page * 10); // TODO: Make this use the nextpage value from the json
        }

        try {
            uri = String.format("%s?key=%s&cx=%s&searchType=image&q=%s%s",
                    BASE_URL,
                    API_KEY,
                    API_CX,
                    URLEncoder.encode(query, "UTF-8"),
                    start
            );
        } catch (UnsupportedEncodingException exception) {
            // TODO: Handle this
        }

        return uri;
    }
    @Override
    public void getImages(final ImageServiceCallback<List<GoogleImageSearchResults>> callback, int page, String query)
    {
        AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        this.setQuery(query);
        String url = generateQueryString(page);
        Log.d("google search url: ", url);
        asyncHttpClient.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    //Gson gson = new Gson();
                    List<GoogleImageSearchResults> temporaryList = new ArrayList<GoogleImageSearchResults>();
                    temporaryList.addAll(GoogleImageSearchResults.parseJSON(response));
                    //temporaryList = gson.fromJson(response.getString("item"), new TypeToken<List<GoogleImageSearchResults>>() {
                    //}.getType());
                    callback.onLoaded(temporaryList);

                } catch (JSONException ex) {
                    Log.d("GIS JSONException", "JSON Exception: " + ex.getMessage());
                    ex.printStackTrace();
                }
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                //TODO: Handle This
                Log.d("onFailure", "Failed  Status: " + statusCode);
                if (errorResponse != JSONObject.NULL)
                    Log.d("blah", "Obj: " + errorResponse.toString());
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("onFailure", "Failed  Status: " + statusCode);
                super.onFailure(statusCode, headers, responseString, throwable);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("onFailure", "Failed  Status: " + statusCode);
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    public void getImages(final ImageServiceCallback<List<GoogleImageSearchResults>> callback, String query)
    {
        getImages(callback, 0, query);
    }
}
