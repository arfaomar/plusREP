package de.hda.plusrep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {
    Context context;
    ArrayList<ContactModel> contactModels;

    public ContactsAdapter(Context context, ArrayList<ContactModel> contactModels) {
        this.context = context;
        this.contactModels = contactModels;
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contacts_recycler_view_row, parent,false);

        return new ContactsAdapter.ContactsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ContactsViewHolder holder, int position) {
        holder.usrName.setText(contactModels.get(position).getName());
        holder.rpScore.setText(contactModels.get(position).getRepScore());
        holder.image.setImageResource(contactModels.get(position).profilePic);
    }

    @Override
    public int getItemCount() {

        return contactModels.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView usrName;
        TextView rpScore;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profilePicImage);
            usrName = itemView.findViewById(R.id.userNameText);
            rpScore = itemView.findViewById(R.id.userRepscoreText);
        }
    }

}
