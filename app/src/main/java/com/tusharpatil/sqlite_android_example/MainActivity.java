package com.tusharpatil.sqlite_android_example;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView list_view;
    private FloatingActionButton fab_add;
    private ContactsAdapter contactsAdapter;
    private ContactsDatabaseHelper contactsDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contactsDatabaseHelper = new ContactsDatabaseHelper(this);
        list_view = findViewById(R.id.list_view);
        fab_add = findViewById(R.id.fab_add);
        fab_add.setOnClickListener(this);
        contactsAdapter = new ContactsAdapter(MainActivity.this);
        list_view.setAdapter(contactsAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.fab_add) {
            openDialog();
        }
    }

    private void openDialog() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.custom_dialog_layout);
        dialog.show();

        final EditText edt_name = dialog.findViewById(R.id.edt_name);
        final EditText edt_mobile = dialog.findViewById(R.id.edt_mobile);
        Button btn_add = (Button) dialog.findViewById(R.id.btn_add);
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
                    contactsDatabaseHelper.insertContact(name, mobile);
                    contactsAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            }
        });
    }
}