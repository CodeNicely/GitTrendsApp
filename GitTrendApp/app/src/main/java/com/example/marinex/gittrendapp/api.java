package com.example.marinex.gittrendapp;

/**
 * Created by marinex on 27/2/17.
 */
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

import static android.R.attr.path;

public interface api {
    @GET("/search/")
    Call<List<dataModel>> popularRepo(
            @Query("url") String url


    );
}

