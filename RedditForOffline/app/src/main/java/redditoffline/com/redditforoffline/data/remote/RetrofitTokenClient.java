package redditoffline.com.redditforoffline.data.remote;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import redditoffline.com.redditforoffline.Models.Token;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTokenClient {

  private static String BASE_URL = "https://www.reddit.com/api/v1/";

  public static ApiEndpoints retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
      .build().create(ApiEndpoints.class);


  public static Observable<Token> getToken(){
    return retrofit.getToken("https://oauth.reddit.com/grants/installed_client","DO_NOT_TRACK_THIS_DEVICE","Basic YWhHVDhKN0dUNTgtcHc6")
        .subscribeOn(Schedulers.io()) // optional if you do not wish to override the default behavior
        .observeOn(AndroidSchedulers.mainThread());
  }
}
