package de.hda.plusrep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {
    private final ForumRecViewInterface forumRecViewInterface;
    Context context;
    ArrayList<ForumPostModel> forumPostModels;
    public ForumAdapter(Context context, ArrayList<ForumPostModel> forumPostModels, ForumRecViewInterface forumRecViewInterface){
        this.context = context;
        this.forumPostModels = forumPostModels;
        this.forumRecViewInterface = forumRecViewInterface;
    }

    @NonNull
    @Override
    public ForumViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.forum_recycler_view_row, parent, false);
        return new ForumViewHolder(view, forumRecViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ForumViewHolder holder, int position) {
        holder.title.setText(forumPostModels.get(position).getTitle());
        holder.userName.setText(forumPostModels.get(position).getUserName());
    }

    @Override
    public int getItemCount() {
        return forumPostModels.size();
    }

    public static class ForumViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView userName;

        public ForumViewHolder(@NonNull View itemView, ForumRecViewInterface forumRecViewInterface) {
            super(itemView);
            title = itemView.findViewById(R.id.forumPostTitle);
            userName = itemView.findViewById(R.id.forumPostUsername);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (forumRecViewInterface != null && pos != RecyclerView.NO_POSITION ) {
                        forumRecViewInterface.onItemClick(pos);
                    }
                }
            });
        }
    }

}
