package edu.qc.seclass.glm.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.Item;

public class SearchResultFoundActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;

    TextView searchText;

    ListView listView;

    int id;
    Button cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search_results_founded);
        id = getIntent().getIntExtra("GROCERY_ID", 0);

        cancel = findViewById(R.id.button16);
        searchText = findViewById(R.id.textView25);

        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            searchText.setText("\""+ bundle.getString("search_input")+"\"");
            searchText.setTextColor(Color.GRAY);
        }else{
            System.out.println("not found");
        }
        List<String> results_item = new ArrayList<>();
        ArrayList<Item> bundle_items = (ArrayList<Item>) getIntent().getSerializableExtra("search_items");;
        for(int i = 0; i < bundle_items.size(); i++){
                results_item.add(bundle_items.get(i).getItemName());
            }

        listView = findViewById(R.id.results);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, results_item);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(view.getContext(), AddItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("item_type" , bundle_items.get(i).getItemType().getTypeName());
                bundle.putString("item_name", bundle_items.get(i).getItemName());
                bundle.putInt("GROCERY_ID", id);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
