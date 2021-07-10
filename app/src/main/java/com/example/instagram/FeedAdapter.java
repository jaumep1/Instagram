package com.example.instagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.parceler.Parcels;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;



    public FeedAdapter (Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public FeedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvName;
        TextView tvDescription;
        ImageView ivPhoto;
        ImageView ivProfileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);
            itemView.setOnClickListener(this);
        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvName.setText(post.getUser().getUsername());
            ParseFile postImage = post.getImage();
            queryProfilePic(post.getUser());
            if (postImage != null) {
                Glide.with(context).load(postImage.getUrl()).into(ivPhoto);
            }
        }

        private void queryProfilePic(ParseUser user) {
            // specify what type of data we want to query - Post.class
            ParseQuery<ParseUser> query = ParseQuery.getQuery(com.parse.ParseUser.class);
            // include data referred by user key
            query.include(User.KEY_IMAGE);
            query.whereEqualTo(User.KEY_ID, user.getObjectId());
            query.findInBackground(new FindCallback<ParseUser>() {
                @Override
                public void done(List<com.parse.ParseUser> users, ParseException e) {
                    // check for errors
                    if (e != null) {
                        return;
                    }
                    String image = ((ParseFile) users.get(0).get("profileImage")).getUrl();
                    Glide.with(context).load(image).into(ivProfileImage);

                }
            });
        }

        @Override
        public void onClick(View v) {
            Log.d("FeedAdapter", "Viewing post details");

            //Get position
            int position = getAdapterPosition();
            //Validate position
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);

                //Create intent
                Intent intent = new Intent(context, PostDetailsActivity.class);
                intent.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));

                //Show activity
                context.startActivity(intent);
            }
        }
    }
}
