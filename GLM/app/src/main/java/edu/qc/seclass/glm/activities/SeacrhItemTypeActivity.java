package edu.qc.seclass.glm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.Item;
import edu.qc.seclass.glm.models.ItemTypes;

public class SeacrhItemTypeActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    EditText searchInput;

    ImageButton searchButton;

    ExpandableListView expandableListView;
    List<String> listItemType;
    HashMap<String, List<String>> listItem;
    HeirarchyAdapter adapter;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seacrh_item_type);


        id = getIntent().getIntExtra("GROCERY_ID", 0);

        expandableListView = findViewById(R.id.expandable_listview);
        listItemType = new ArrayList<>();
        listItem = new HashMap<>();
        adapter = new HeirarchyAdapter(this, listItemType, listItem);
        expandableListView.setAdapter(adapter);

        expandableListView.setDescendantFocusability(ViewGroup.FOCUS_AFTER_DESCENDANTS);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        initListData(databaseHelper);

//        @Override
//        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case android.R.id.home:
//                    this.finish();
//                    return true;
//            }
//            return super.onOptionsItemSelected(item);
//        }

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Intent intent = new Intent(SeacrhItemTypeActivity.this.getApplicationContext(), AddItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("item_type" , listItemType.get(i));
//                System.out.println(listItemType.get(i));
//                System.out.println(listItem.get(listItemType.get(i)));
                bundle.putString("item_name" , listItem.get(listItemType.get(i)).get(i1));
                bundle.putInt("GROCERY_ID", id);
                intent.putExtras(bundle);
                SeacrhItemTypeActivity.this.startActivity(intent);
                return false;
            }
        });


        searchInput = (EditText) findViewById(R.id.editTextTextPersonName4);

        searchButton = (ImageButton) findViewById(R.id.imageButton3);



        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = searchInput.getText().toString();
//                System.out.println(input);

                if(searchInput.getText().toString().length() == 0){
                    Toast.makeText(getApplicationContext(),"Need to input item name in search bar",Toast.LENGTH_SHORT).show();
                }else {
                    List<Item> searchItems = databaseHelper.getItemsLike(searchInput.getText().toString());
                    if (searchItems.size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putString("search_input", input);
                        bundle.putSerializable("search_items", (Serializable) searchItems);
                        Intent intent = new Intent(getApplicationContext(), SearchResultFoundActivity.class);
                        //intent.putParcelableArrayListExtra("search_items", (ArrayList<? extends Parcelable>) searchItems);
                        bundle.putInt("GROCERY_ID", id);
                        intent.putExtras(bundle);
                        //intent.putExtra("searchItems", (Serializable) searchItems);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getApplicationContext(), SearchResultNotFoundActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("GROCERY_ID", id);
                        bundle.putString("search_input", input);
                        intent.putExtras(bundle);

                        startActivity(intent);
                    }
                }
            }


        });


    }

    private void initListData(DatabaseHelper databaseHelper) {
//        System.out.println("logging");
        List<ItemTypes> itemTypes = databaseHelper.getAllItemTypesInDB();
        Log.d("TAG", "initListData: no arraylist");
        for (int i = 0; i < itemTypes.size(); i++){
            listItemType.add(itemTypes.get(i).getTypeName());
        }

        for( ItemTypes itemType : itemTypes){
            List<Item> itemByType = databaseHelper.getAllItems(itemType);
//            System.out.println("itemByType size: "+itemByType.size());
            for(Item items : itemByType){
                if(!listItem.containsKey(itemType.getTypeName())){
                    List<String> list = new ArrayList<>();
                    list.add(items.getItemName());
                    listItem.put(itemType.getTypeName(), list);
                }else{
                    List<String> maplist = listItem.get(itemType.getTypeName());
                    //System.out.println("maplist size: "+maplist.size());
                    maplist.add(items.getItemName());
                    listItem.put(itemType.getTypeName(), maplist);
                }
            }

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        listItem.clear();
        listItemType.clear();
        initListData(databaseHelper);

    }
}