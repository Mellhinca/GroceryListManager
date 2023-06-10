package edu.qc.seclass.glm.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.database.DatabaseHelper;

public class EditListItemActivity extends AppCompatActivity {

    String name;
    int item_ID;
    private Dialog dialog;
    private DatabaseHelper databaseHelper;
    EditText original_qty,new_qty;
    int qty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_list);

        Toolbar toolbar = findViewById(R.id.toolbar21);
        setSupportActionBar(toolbar);
        name = getIntent().getStringExtra("TITLE");
        item_ID = getIntent().getIntExtra("ITEM_ID",0);
        qty = getIntent().getIntExtra("QTY",1);
        getSupportActionBar().setTitle(name);

        original_qty = findViewById(R.id.editTextOrignalQty);
        new_qty = findViewById(R.id.editTextNewQty);

        original_qty.setText(String.valueOf(qty));
        Bundle bundle = getIntent().getExtras();
        databaseHelper = new DatabaseHelper(this);
        ((findViewById(R.id.btn_save_item))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG","bundle : "+bundle);
                //openPopopView();
                if(new_qty.getText().toString().isEmpty())
                {
                    new_qty.setError("Enter Valid New Quantity");
                    return;
                }
                int newQty = Integer.parseInt(new_qty.getText().toString());
                if(databaseHelper.updateItemInDB(item_ID, newQty))
                {
                    Toast.makeText(EditListItemActivity.this, "updated....", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(EditListItemActivity.this, "something goes wrong please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((findViewById(R.id.btn_cancel_item))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
               /* if(databaseHelper.deleteRowInDB(item_ID))
                {
                    Toast.makeText(EditListItemActivity.this, "done...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(EditListItemActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent); // start same activity
                    finish(); // destroy older activity
                    overridePendingTransition(0, 0);
                }
                else {
                    Toast.makeText(EditListItemActivity.this, "error please try again...", Toast.LENGTH_SHORT).show();
                }*/



            }
        });
    }
    private void openPopopView() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.Theme_AppCompat_Light_Dialog_Alert);
        final View inflate = LayoutInflater.from(this).inflate(R.layout.grocerylist_name,null,true);
        final Button save = (Button)inflate.findViewById(R.id.btn_save_);
        final Button cancel = (Button)inflate.findViewById(R.id.btn_cancel_);
        final EditText listName  = inflate.findViewById(R.id.editTextNewListName);
        
        final TextView newName, addName;
        
        newName = inflate.findViewById(R.id.textnamelist);
        addName = inflate.findViewById(R.id.textaddNew);

        newName.setText("New List Name");
        addName.setText("Rename Your List");
        
        builder.setView(inflate);
        builder.setCancelable(false);
        cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View view) {
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

                if(databaseHelper.changeGroceryListNameInDB(listName.getText().toString(), item_ID))
                {
                    Toast.makeText(EditListItemActivity.this, "done...", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    Intent intent = new Intent(EditListItemActivity.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent); // start same activity
                    finish(); // destroy older activity
                    overridePendingTransition(0, 0);
                }
                else {
                    Toast.makeText(EditListItemActivity.this, "error please try again...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        this.dialog = builder.create();
        dialog.show();
    }
}