package edu.qc.seclass.glm.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.adapters.CustomAdapterGroceryList;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.GroceryList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    DatabaseHelper databaseHelper;
    CustomAdapterGroceryList customAdapterGroceryList;
    ArrayList<GroceryList> groceryLists;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.grocery_lists);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        ((findViewById(R.id.AddButton))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 //startActivity(new Intent(getApplicationContext(),AddItemActivity.class));
                openPopopView();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int grocery_id = groceryLists.get(position).getGroceryId();
                String name = groceryLists.get(position).getName();

                Intent intent = new Intent(getApplicationContext(), ListItemsActivity.class);
                intent.putExtra("GROCERY_ID",grocery_id);
                intent.putExtra("GROCERY_NAME",name);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        groceryLists = databaseHelper.getAllGroceryList();
        customAdapterGroceryList = new CustomAdapterGroceryList(this,groceryLists);

        listView.setAdapter(customAdapterGroceryList);
        customAdapterGroceryList.notifyDataSetChanged();
    }

    private void openPopopView() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        final View inflate = LayoutInflater.from(this).inflate(R.layout.grocerylist_name,null,true);
        final Button save = (Button)inflate.findViewById(R.id.btn_save_);
        final Button cancel = (Button)inflate.findViewById(R.id.btn_cancel_);
        final EditText listName  = inflate.findViewById(R.id.editTextNewListName);

        builder.setView(inflate);
        builder.setCancelable(false);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
                customAdapterGroceryList.notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {

                if(listName.getText().toString().isEmpty())
                {
                    listName.setError("Please enter valid name");
                    return;
                }
                GroceryList groceryList = new GroceryList(1,listName.getText().toString());
                databaseHelper.addGroceryListInDB(groceryList);
                dialog.dismiss();

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent); // start same activity
                finish(); // destroy older activity
                overridePendingTransition(0, 0);
            }
        });
        this.dialog = builder.create();
        dialog.show();
    }

}