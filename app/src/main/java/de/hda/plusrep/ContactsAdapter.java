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

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder> {

    private final ContactsRecViewInterface contactsRecViewInterface;
    Context context;
    ArrayList<ContactModel> contactModels;

    public ContactsAdapter(Context context, ArrayList<ContactModel> contactModels, ContactsRecViewInterface contactsRecViewInterface) {
        this.context = context;
        this.contactModels = contactModels;
        this.contactsRecViewInterface = contactsRecViewInterface;
    }

    @NonNull
    @Override
    public ContactsAdapter.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.contacts_recycler_view_row, parent,false);

        return new ContactsAdapter.ContactsViewHolder(view, contactsRecViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactsAdapter.ContactsViewHolder holder, int position) {
        holder.personaNameTextView.setText(contactModels.get(position).getName());
        holder.repScoreTextView.setText(contactModels.get(position).getRepScore());
        Glide.with(context).load(contactModels.get(position).getAvatarURL())
                .placeholder(R.drawable.ic_person)
                .error(R.drawable.ic_launcher_foreground)
                .override(240, 240)
                .centerCrop()
                .into(holder.avatarImageView);


       // holder.avatarImageView.setImageResource(contactModels.get(position).getAvatarURL());
    }

    @Override
    public int getItemCount() {

        return contactModels.size();
    }

    public static class ContactsViewHolder extends RecyclerView.ViewHolder{

        ImageView avatarImageView;
        TextView personaNameTextView;
        TextView repScoreTextView;
        public ContactsViewHolder(@NonNull View itemView, ContactsRecViewInterface contactsRecViewInterface) {
            super(itemView);
            avatarImageView = itemView.findViewById(R.id.profilePicImage);
            personaNameTextView = itemView.findViewById(R.id.userNameText);
            repScoreTextView = itemView.findViewById(R.id.userRepscoreText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (contactsRecViewInterface != null && pos != RecyclerView.NO_POSITION) {

                        contactsRecViewInterface.onItemClick(pos);

                    }
                }
            });

        }
    }

}
