package redditoffline.com.redditforoffline.data;
import android.arch.paging.ItemKeyedDataSource;
import android.support.annotation.NonNull;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import redditoffline.com.redditforoffline.Models.Post;
import redditoffline.com.redditforoffline.Models.Response;
import redditoffline.com.redditforoffline.data.remote.RetrofitClient;

public class PostDataSource extends ItemKeyedDataSource<String, Post> {
  RetrofitClient retrofitClient;
  String after;
  String auth;

  public PostDataSource(String auth) {
    super();
    this.auth = auth;
  }

  @Override
  public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull LoadInitialCallback<Post> callback) {
    retrofitClient.getPosts(auth, after).subscribe(new Observer<Response>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Response response) {
        after = response.getData().getAfter();
        callback.onResult(response.getData().getPosts());
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  @Override
  public void loadAfter(@NonNull LoadParams<String> params, @NonNull LoadCallback<Post> callback) {
    retrofitClient.getPosts(auth, after).subscribe(new Observer<Response>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Response response) {
        after = response.getData().getAfter();
        callback.onResult(response.getData().getPosts());
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  @Override
  public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<Post> callback) {
    retrofitClient.getPosts(auth, after).subscribe(new Observer<Response>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Response response) {
        after = response.getData().getAfter();
        callback.onResult(response.getData().getPosts());
      }

      @Override
      public void onError(Throwable e) {

      }

      @Override
      public void onComplete() {

      }
    });
  }

  @NonNull
  @Override
  public String getKey(@NonNull Post item) {
    return after;
  }
}
