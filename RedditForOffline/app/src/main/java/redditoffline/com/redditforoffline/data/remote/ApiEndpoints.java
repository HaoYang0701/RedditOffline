package redditoffline.com.redditforoffline.data.remote;

import io.reactivex.Observable;
import redditoffline.com.redditforoffline.Models.Response;
import redditoffline.com.redditforoffline.Models.Token;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiEndpoints {

  @POST("access_token")
  Observable<Token> getToken (
      @Query("grant_type") String grantType,
      @Query("device_id") String deviceID,
      @Header("Authorization") String auth
      );

  @GET("funny/hot")
  Observable<Response> getPosts (
      @Header("Authorization") String auth,
      @Query("after") String after
  );
}