package edu.qc.seclass.glm.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.Item;
import edu.qc.seclass.glm.models.ItemList;
import edu.qc.seclass.glm.models.ItemTypes;

public class AddItemActivity extends AppCompatActivity {

    EditText item_type, item_name, item_quantity, item_price;
    DatabaseHelper databaseHelper;

    int grocery_id;
    String grocery_name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grocerylist_add);


        grocery_id = getIntent().getIntExtra("GROCERY_ID",0);
        grocery_name = getIntent().getStringExtra("GROCERY_NAME");
        item_name = findViewById(R.id.editTextTextItemName);
        item_type = findViewById(R.id.editTextItemType);
        item_quantity = findViewById(R.id.editTextTextItemQ);
        item_price = findViewById(R.id.editTextItemPrice);
        databaseHelper = new DatabaseHelper(AddItemActivity.this);


        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            if(bundle.containsKey("item_name") && bundle.containsKey("item_type") ) {
                System.out.println("THERE IS ITEMS");
                item_name.setText(bundle.getString("item_name"));
                item_name.setEnabled(false);
                item_name.setTextColor(Color.BLACK);

                item_type.setText(bundle.getString("item_type"));
                item_type.setEnabled(false);
                item_type.setTextColor(Color.BLACK);
            }
        }else{
            System.out.println("not found");
        }

        ((findViewById(R.id.buttonSearch))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( getApplicationContext(), SeacrhItemTypeActivity.class ).putExtra("GROCERY_ID", grocery_id).putExtra("GROCERY_NAME",grocery_name);
                startActivity(intent);
                finish();
            }
        });

        ((findViewById(R.id.btn_save))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validate()) {
                    Toast.makeText(AddItemActivity.this, "Please Enter Required data", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (getIntent() != null && getIntent().getExtras() != null) {
                    Bundle bundle = getIntent().getExtras();
                    if (bundle.containsKey("db")) {
                        ItemTypes retrieval = databaseHelper.getItemType(item_type.getText().toString());
//                        System.out.println("I JUST WANT TO BE HERE");
//                        System.out.println("getting retrieval name: " + retrieval.getTypeName());
                        Item item;
                        if (retrieval != null) {
                            item = databaseHelper.insertItem(item_name.getText().toString(), retrieval);
                        } else {
                            Toast.makeText(AddItemActivity.this, "item type does not exist. Please write an existing item type", Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                }
                System.out.println("ITEM SAVEl "+item_type.getText().toString());
                int type_id = databaseHelper.checkItemType(item_type.getText().toString());

                System.out.println("type_id: "+type_id);
                if (type_id == -2) {
                    Toast.makeText(AddItemActivity.this, "error while doing operation...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (type_id == -1) {
                    //Toast.makeText(AddItemActivity.this, "not found......", Toast.LENGTH_SHORT).show();
                    insertItemTypeNew(item_type.getText().toString());
                } else {
                    //Toast.makeText(AddItemActivity.this, "found......" + type_id, Toast.LENGTH_SHORT).show();
                    insertItemInDB(type_id);
                }
            }

        });
        ((findViewById(R.id.btn_cancel))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    boolean validate() {
        if (item_type.getText().toString().isEmpty()) {
            return false;
        }
        if (item_name.getText().toString().isEmpty()) {
            return false;
        }
        if (item_price.getText().toString().isEmpty()) {
            return false;
        }
        if (item_quantity.getText().toString().isEmpty()) {
            return false;
        }
        return true;
    }

    void insertItemTypeNew(String item_type) {

        int result = databaseHelper.insertItemTypeInDB(item_type);
        if (result == -2) {
            Toast.makeText(this, "please try again", Toast.LENGTH_SHORT).show();
        }
        if (result == -1) {

        } else {
                insertItemInDB(result);
        }
    }

    void insertItemInDB(int itemTypeID) {
        String itemName = item_name.getText().toString();
        String itemType = item_type.getText().toString();
        float price = Float.parseFloat(item_price.getText().toString());
        int quantity = Integer.parseInt(item_quantity.getText().toString());
        Log.d("TAG","insertItemInDb : "+quantity);
        ItemList itemList = new ItemList(1, itemName, price, grocery_id, itemTypeID, quantity);
        databaseHelper.insertItemInDB(itemList);
        finish();
    }
}
