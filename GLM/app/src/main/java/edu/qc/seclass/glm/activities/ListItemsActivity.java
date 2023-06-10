package edu.qc.seclass.glm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.adapters.CustomAdapter;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.ItemList;

public class ListItemsActivity extends AppCompatActivity {

    String name;
    int id;
    ListView listView;
    private DatabaseHelper databaseHelper;
    ArrayList<ItemList> itemListArrayList;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = getIntent().getStringExtra("GROCERY_NAME");
        id = getIntent().getIntExtra("GROCERY_ID", 0);
        getSupportActionBar().setTitle(name);
        listView = findViewById(R.id.item_lits);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        ((findViewById(R.id.AddButton))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AddItemActivity.class).putExtra("GROCERY_ID", id).putExtra("GROCERY_NAME",name));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.checkBoxAll) {
            if (item.isChecked()) {
                item.setChecked(false);
                checkAllItems(item.isChecked());
            } else {
                item.setChecked(true);
                checkAllItems(item.isChecked());
            }
            return true;
        } else if (id == R.id.btnDeleteAll) {
            deleteAllItemInList();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        itemListArrayList = databaseHelper.getAllItemList(id);
        customAdapter = new CustomAdapter(this, itemListArrayList);
        listView.setAdapter(customAdapter);
    }

    void checkAllItems(boolean checked) {
        for (int i = 0; i < itemListArrayList.size(); i++) {
            itemListArrayList.get(i).setChecked(checked);
        }
        customAdapter.notifyDataSetChanged();
    }
    void deleteAllItemInList(){
        new AlertDialog.Builder(this)
                .setTitle("Alert")
                .setMessage("Are You sure to delete all items in this list")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(databaseHelper.deleteItemInDBwithInstance(id)) {
                            Toast.makeText(ListItemsActivity.this, "deleted....", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            itemListArrayList.clear();
                            listView.setAdapter(customAdapter);
                        }
                        else{
                            Toast.makeText(ListItemsActivity.this, "error please try again...", Toast.LENGTH_SHORT).show();
                        }
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }

}