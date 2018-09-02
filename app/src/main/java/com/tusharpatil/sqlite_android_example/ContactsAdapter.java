package com.tusharpatil.sqlite_android_example;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by tushar on 02/09/18.
 */

public class ContactsAdapter extends BaseAdapter {
    private Context context;
    private ContactsDatabaseHelper contactsDatabaseHelper;

    public ContactsAdapter(Context context) {
        this.context = context;
        contactsDatabaseHelper = new ContactsDatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return contactsDatabaseHelper.getAllContacts().size();
    }

    @Override
    public Object getItem(int position) {
        return contactsDatabaseHelper.getAllContacts().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.contacts_row_layout, parent, false);
        }

        final ContactModel currentItem = (ContactModel) getItem(position);

        TextView txt_name = convertView.findViewById(R.id.txt_name);
        TextView txt_mobile = convertView.findViewById(R.id.txt_mobile);
        LinearLayout lay_contact = convertView.findViewById(R.id.lay_contact);
        lay_contact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDialog(currentItem);
            }
        });
        ImageView img_delete = convertView.findViewById(R.id.img_delete);
        img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactsDatabaseHelper.deleteContact(currentItem);
                notifyDataSetChanged();
            }
        });

        txt_name.setText("Name: " + currentItem.getName());
        txt_mobile.setText("Mobile: " + currentItem.getMobile());
        return convertView;
    }

    private void updateDialog(final ContactModel contact) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.show();

        final EditText edt_name = dialog.findViewById(R.id.edt_name);
        edt_name.setText(contact.getName());
        final EditText edt_mobile = dialog.findViewById(R.id.edt_mobile);
        edt_mobile.setText(contact.getMobile());
        Button btn_add = (Button) dialog.findViewById(R.id.btn_add);
        btn_add.setText("Update");
        Button btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Close dialog
                dialog.dismiss();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name, mobile;
                name = edt_name.getText().toString().trim();
                mobile = edt_mobile.getText().toString().trim();
                if (!name.isEmpty() && !mobile.isEmpty()) {
                    contactsDatabaseHelper.updateContact(new ContactModel(contact.getId(), name, mobile));
                    notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
    }
}