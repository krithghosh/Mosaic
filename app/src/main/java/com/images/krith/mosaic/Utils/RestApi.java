package com.images.krith.mosaic.Utils;

import com.images.krith.mosaic.Models.Photo;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by krith on 30/11/16.
 */

public interface RestApi {

    @GET("/services/rest/")
    Observable<Photo> getPhotos(@Query("method") String method, @Query("api_key") String apiKey,
                                @Query("tags") String tags, @Query("format") String format,
                                @Query("nojsoncallback") int noJsonCallback);
}
