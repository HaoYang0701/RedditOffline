package redditoffline.com.redditforoffline.tabCentral;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import redditoffline.com.redditforoffline.Models.Post;
import redditoffline.com.redditforoffline.R;
import redditoffline.com.redditforoffline.adapter.PostAdapter;
import redditoffline.com.redditforoffline.data.PostDataSourceFactory;


public class TabCentralFragment extends Fragment implements TabCentralContract.View{
  private TabCentralContract.Presenter presenter;
  private PostAdapter adapter;
  LiveData<PagedList<Post>> posts;

  @BindView(R.id.textView) TextView textView;
  @BindView(R.id.recyclerview) RecyclerView recyclerView;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_tabcentral, container, false);
    ButterKnife.bind(this, view);

    adapter = new PostAdapter(Glide.with(this));

    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    PostDataSourceFactory postDataSourceFactory = new PostDataSourceFactory("Bearer -rgNqMq-Mo7Kf8UbJ0RmqjAaxv3g");

    posts = new LivePagedListBuilder(postDataSourceFactory, 20).build();

    posts.observe(this, posts -> adapter.submitList(posts));
    return view;
  }

  @Override
  public void setPresenter(TabCentralContract.Presenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public void onResume() {
    super.onResume();
    presenter.subscribe();
  }

  @Override
  public void onPause() {
    super.onPause();
    presenter.unsubscribe();
  }

  @Override
  public void setString(String string) {
    textView.setText(string);
  }

  @Override
  public void updatePosts(List<Post> posts) {
  }
}