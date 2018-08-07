package redditoffline.com.redditforoffline.tabCentral;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.view.MenuItem;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import redditoffline.com.redditforoffline.Models.Post;
import redditoffline.com.redditforoffline.Models.Response;
import redditoffline.com.redditforoffline.Models.Token;
import redditoffline.com.redditforoffline.data.remote.RetrofitClient;
import redditoffline.com.redditforoffline.data.remote.RetrofitTokenClient;

public class TabCentralPresenter implements TabCentralContract.Presenter{
  private final TabCentralContract.View view;
  private String after;
  private String auth;

  public TabCentralPresenter(TabCentralContract.View view) {
    this.view = view;
    view.setPresenter(this);
  }

  @Override
  public void subscribe() {
    RetrofitTokenClient.getToken().subscribe(new Observer<Token>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Token token) {
        view.setString(token.getAccessToken());
        auth = "Bearer " + token.getAccessToken();
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
  public void unsubscribe() {

  }

  @Override
  public void onFabClicked() {
    RetrofitClient.getPosts(auth, after).subscribe(new Observer<Response>() {
      @Override
      public void onSubscribe(Disposable d) {

      }

      @Override
      public void onNext(Response response) {
        view.updatePosts(response.getData().getPosts());
        after = response.getData().getAfter();
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
  public boolean onNavigationItemClicked(MenuItem item) {
    return false;
  }
}
