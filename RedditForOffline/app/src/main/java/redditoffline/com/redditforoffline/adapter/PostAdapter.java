package redditoffline.com.redditforoffline.adapter;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;
import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import redditoffline.com.redditforoffline.Models.Post;
import redditoffline.com.redditforoffline.R;

public class PostAdapter extends PagedListAdapter<Post, PostAdapter.ViewHolder> {
  public LiveData<PagedList<Post>> postList;
  private RequestManager glide;

  public PostAdapter(RequestManager glide) {
    super(DIFF_CALLBACK);
    this.glide = glide;
  }

  @NonNull
  @Override
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    Context context = parent.getContext();
    LayoutInflater layoutInflater = LayoutInflater.from(context);

    View postView = layoutInflater.inflate(R.layout.item_post, parent, false);
    ViewHolder viewHolder = new ViewHolder(postView);

    return viewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
    Post post = getItem(position);

    holder.title.setText(post.getData().getTitle());
    RequestOptions options = new RequestOptions();
    options.centerCrop();

    if (post.getData().getPostHint() == "image") {
      glide.load(post.getData().getUrl()).apply(options).into(holder.imageView);
    } else {
      glide.load(post.getData().getThumbnail()).apply(options).into(holder.imageView);
    }
  }

  public static final DiffUtil.ItemCallback<Post> DIFF_CALLBACK = new DiffUtil.ItemCallback<Post>() {
    @Override
    public boolean areItemsTheSame(Post oldItem, Post newItem) {
      return oldItem.getData().getId() == newItem.getData().getId();
    }

    @Override
    public boolean areContentsTheSame(Post oldItem, Post newItem) {
      return oldItem.getData().getTitle() == newItem.getData().getTitle();
    }
  };

  public class ViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private ImageView imageView;

    public ViewHolder(View itemView) {
      super(itemView);
      title = itemView.findViewById(R.id.post_title);
      imageView = itemView.findViewById(R.id.thumbNail);
    }
  }
}
