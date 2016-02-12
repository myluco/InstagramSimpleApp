package com.myluco.instagramclient;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    private ArrayList<InstagramPhoto> photos;
    private InstagramPhotoAdapter aPhotos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        //SEND out API call
        photos = new ArrayList<InstagramPhoto>();
        aPhotos = new InstagramPhotoAdapter(this,photos);

        //listview
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        lvPhotos.setAdapter(aPhotos);
        fetchPopularPhotos();
        //Create adaper for list view


    }

    private void fetchPopularPhotos() {
//        https://api.instagram.com/v1/media/popular?client_id=e05c462ebd86446ea48a5af73769b602

        String url = "https://api.instagram.com/v1/media/popular?client_id="+ CLIENT_ID;
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url,null, new JsonHttpResponseHandler() {
            //success = 200

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                //        Response:
//        { data => [x] => "type" (image or video)
//            URL:"images" => "standard resolution" => url
//            Caption: "caption" => "text"
//            Author: "user" => username
//                User Profile Picture: "user" => "profile_picture"
                // time stamp : "caption" =>  "created_time"
               // super.onSuccess(statusCode, headers, response);
                //Log.i("DEBUG-Success",response.toString());
                JSONArray photosJSON;
                try {
                    photosJSON = response.getJSONArray("data");
                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject object = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.username = object.getJSONObject("user").getString("username");
//                        JSONObject oCaption = object.getJSONObject("caption");
                        if (!object.isNull("caption")) {
                            photo.caption = object.getJSONObject("caption").getString("text");
                            photo.timestamp = object.getJSONObject("caption").getLong("created_time");
                        }else {
                            photo.caption = "no caption given";
                        }
                        JSONObject image = object.getJSONObject("images").getJSONObject("standard_resolution");
                        photo.imageUrl = image.getString("url");
                        photo.imageHeight = image.getInt("height");
                        photo.likesCount = object.getJSONObject("likes").getInt("count");
                        photo.userImageUrl = object.getJSONObject("user").getString("profile_picture");

                                photos.add(photo);

                    }
                    Log.i("DEBUG-Success", String.valueOf(photos.size()));
                    //need to inform that the data has changed
                    aPhotos.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //failure

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                //Do SOMETHING
                Log.i("DEBUG-Failure1",responseString);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                 Log.i("DEBUG-Failure2", errorResponse.toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
