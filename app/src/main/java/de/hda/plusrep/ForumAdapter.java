package de.hda.plusrep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumHolder> {
    Context context;
    ArrayList<ForumPostModel> forumPostModels;

    public ForumAdapter(Context context, ArrayList<ForumPostModel> forumPostModels){
        this.context = context;
        this.forumPostModels = forumPostModels;
    }

    @NonNull
    @Override
    public ForumAdapter.ForumHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.forum_recycler_view_row, parent, false);
        return new ForumAdapter.ForumHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumAdapter.ForumHolder holder, int position) {
        holder.title.setText(forumPostModels.get(position).getTitle());
        holder.userName.setText(forumPostModels.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return forumPostModels.size();
    }

    public static class ForumHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView userName;

        public ForumHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.forumPostTitle);
            userName = itemView.findViewById(R.id.forumPostUsername);
        }
    }

}
