package com.masoomsabri.googleimagesearchmvp.ui.imagesearch;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.masoomsabri.googleimagesearchmvp.R;

/**
 * Created by masoomsabri on 6/3/17.
 */

public class ImageSearchActivity extends AppCompatActivity {
    MenuItem searchBar;
    TextView searchHint;
    private ImagesFragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        fragment = ImagesFragment.newInstance();
        FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment,fragment);
        fragmentTransaction.commit();

        handleIntent(getIntent());

    }
   @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }


    private void handleIntent(Intent intent) {
        try {
            Log.d("handleIntent", intent.getAction());

            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
                if (isNetworkAvailable()) {

                    do_search(intent.getStringExtra(SearchManager.QUERY));


                    searchBar.collapseActionView();
                } else {
                    Toast.makeText(this, "No internet connection available.", Toast.LENGTH_SHORT).show();
                }
            }
        }
        catch(Exception e)
        {
            Log.d("handleIntent", e.getStackTrace().toString());
        }
    }

    private void do_search() {
        do_search("");
    }

    private void do_search(String query) {
        try {
            if (!query.isEmpty()) {
                setTitle(query);
                fragment.setQuery(query);
            }
        }
        catch(Exception e)
        {
            Log.d("do_search", e.getStackTrace().toString());
        }

    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        searchBar = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_search, menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        return super.onOptionsItemSelected(item);
    }

    private Boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
