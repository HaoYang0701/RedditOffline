package redditoffline.com.redditforoffline.data.remote;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import redditoffline.com.redditforoffline.Models.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
  private static String BASE_URL = "https://oauth.reddit.com/r/";

  public static ApiEndpoints retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build().create(ApiEndpoints.class);


  public static Observable<Response> getPosts(String auth, String after){
    return retrofit.getPosts(auth, after)
        .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
        .observeOn(AndroidSchedulers.mainThread());
  }
}
