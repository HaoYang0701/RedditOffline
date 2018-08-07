package redditoffline.com.redditforoffline.tabCentral;

import android.view.MenuItem;

import java.util.List;

import redditoffline.com.redditforoffline.BasePresenter;
import redditoffline.com.redditforoffline.BaseView;
import redditoffline.com.redditforoffline.Models.Post;

public class TabCentralContract {

  interface Presenter extends BasePresenter {
    void onFabClicked();
    boolean onNavigationItemClicked(MenuItem item);
  }

  interface View extends BaseView<Presenter> {
    void setString(String string);
    void updatePosts(List<Post> posts);
  }
}
