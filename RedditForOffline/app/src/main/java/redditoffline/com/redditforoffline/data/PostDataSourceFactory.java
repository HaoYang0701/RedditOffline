package redditoffline.com.redditforoffline.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

import redditoffline.com.redditforoffline.Models.Post;
import redditoffline.com.redditforoffline.data.remote.RetrofitClient;

public class PostDataSourceFactory extends PostDataSource.Factory<String, Post> {
  MutableLiveData<PostDataSource> s  = new MutableLiveData<>();
  String auth;

  public PostDataSourceFactory(String auth) {
    super();
    this.auth = auth;
  }

  @Override
  public DataSource<String, Post> create() {
    PostDataSource postDataSource = new PostDataSource(auth);
    s.postValue(postDataSource);
    return postDataSource;
  }
}
