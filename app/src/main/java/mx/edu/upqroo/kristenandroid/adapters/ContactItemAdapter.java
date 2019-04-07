package mx.edu.upqroo.kristenandroid.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import mx.edu.upqroo.kristenandroid.R;
import mx.edu.upqroo.kristenandroid.data.database.entities.Contact;

public class ContactItemAdapter extends RecyclerView.Adapter<ContactItemAdapter.ContactViewHolder> {

    private List<Contact> contactList;
    private LayoutInflater mInflater;
    private Context mContext;

    public ContactItemAdapter(Context context, List<Contact> contactList) {
        this.mInflater = LayoutInflater.from(context);
        mContext = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = mInflater.inflate(R.layout.item_contact, parent,false);
        return new ContactItemAdapter.ContactViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.contact = contactList.get(position);
        holder.name.setText(contactList.get(position).getName());
        holder.email.setText(contactList.get(position).getEmail());

        holder.cardView.setOnClickListener(v -> {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:" + holder.contact.getEmail()));
            mContext.startActivity(emailIntent);
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public void setData(List<Contact> data) {
        contactList = data;
        notifyDataSetChanged();
    }

    class ContactViewHolder extends RecyclerView.ViewHolder {
        Contact contact;
        MaterialCardView cardView;
        TextView name;
        TextView email;

        ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_contact_name);
            email = itemView.findViewById(R.id.text_contact_email);
            cardView = itemView.findViewById(R.id.card_contact);
        }
    }
}