package de.hda.plusrep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FriendRequestsAdapter extends RecyclerView.Adapter<FriendRequestsAdapter.FriendRequestsHolder> {

    Context context;
    ArrayList<FriendRequestsModel> friendRequestsModels;

    public FriendRequestsAdapter(Context context, ArrayList<FriendRequestsModel> friendRequestsModels) {
        this.context = context;
        this.friendRequestsModels = friendRequestsModels;
    }

    @NonNull
    @Override
    public FriendRequestsAdapter.FriendRequestsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.friend_requests_recycler_view_row, parent, false);
        return new FriendRequestsAdapter.FriendRequestsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendRequestsAdapter.FriendRequestsHolder holder, int position) {

        holder.personaName.setText(friendRequestsModels.get(position).getPersonaName());
        Glide.with(context).load(friendRequestsModels.get(position).getAvatarURL())
                .placeholder(R.drawable.ic_fail_image)
                .error(R.drawable.ic_fail_image)
                .override(64, 64)
                .centerCrop()
                .into(holder.avatar);

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long steamID = friendRequestsModels.get(holder.getAdapterPosition()).getSteamID(); //steamID of person who requested friendship
            }
        });

        holder.declineButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Long steamID = friendRequestsModels.get(holder.getAdapterPosition()).getSteamID();
            }
        });

    }

    @Override
    public int getItemCount() {
        return friendRequestsModels.size();
    }

    public static class FriendRequestsHolder extends RecyclerView.ViewHolder {

        ImageView avatar;
        TextView personaName;
        ImageView acceptButton;
        ImageView declineButton;

        public FriendRequestsHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.fReqProfilePicImage);
            personaName = itemView.findViewById(R.id.fReqUserNameText);
            acceptButton = itemView.findViewById(R.id.acceptButton);
            declineButton = itemView.findViewById(R.id.declineButton);
        }
    }

}


