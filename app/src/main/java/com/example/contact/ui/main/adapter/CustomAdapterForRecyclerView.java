package com.example.contact.ui.main.adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contact.Contact;
import com.example.contact.R;
import com.example.contact.ui.main.database.DBHelper;

import java.util.ArrayList;

public class CustomAdapterForRecyclerView extends RecyclerView.Adapter<CustomAdapterForRecyclerView.ListHolder> {

    private ArrayList<Contact> contactArrayList = new ArrayList<Contact>();
    private Context context;
    private static LayoutInflater layoutInflater = null;
    private DBHelper dbHelper;

    public CustomAdapterForRecyclerView(ArrayList<Contact> contacts, Context context) {
        this.contactArrayList = contacts;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    public class ListHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        TextView phoneTextView;
        ImageView favImageView;
        ImageView callImageView;

        public ListHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.tv_contact_name);
            phoneTextView = itemView.findViewById(R.id.tv_phone);
            favImageView = itemView.findViewById(R.id.img_fav);
            callImageView = itemView.findViewById(R.id.img_call);
        }
    }

    @Override
    public ListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lay_contact_item, parent, false);
        return new ListHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ListHolder holder, int position) {
        final Contact contact = contactArrayList.get(position);
        holder.nameTextView.setText(contact.getFirstName());
        holder.phoneTextView.setText(contact.getPhone());
        if (contact.isFavourite() == 1) {
            holder.favImageView.setImageResource(R.drawable.ic_favorite_green_24dp);
        } else {
            holder.favImageView.setImageResource(R.drawable.ic_favorite_border_green_24dp);
        }

        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setRecent(1); //1 is true
                boolean isUpdated = dbHelper.updateContact(contact);

                String uri = "tel:" + contact.getPhone().trim();
                Intent intent = new Intent(Intent.ACTION_CALL);
                Intent dialPadIntent = new Intent(Intent.ACTION_VIEW);
                dialPadIntent.setData(Uri.parse(uri));
                intent.setData(Uri.parse(uri));
                if (isUpdated) {
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                        context.startActivity(intent);
                    }
                    else {
                        context.startActivity(dialPadIntent);
                    }
                }
            }
        });

        holder.favImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contact.setFavourite(1); //1 is true
                holder.favImageView.setImageResource(R.drawable.ic_favorite_green_24dp);
                dbHelper.updateContact(contact);
                Toast.makeText(context, "Fav Added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactArrayList.size();
    }


}
