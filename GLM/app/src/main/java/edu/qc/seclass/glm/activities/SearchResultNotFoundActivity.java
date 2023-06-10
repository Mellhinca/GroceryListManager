package edu.qc.seclass.glm.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.models.Item;

public class SearchResultNotFoundActivity  extends AppCompatActivity {
    TextView searchText;
    Button cancel, addToDatabase;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_search_results_notfound);

        id = getIntent().getIntExtra("GROCERY_ID", 0);

        addToDatabase = findViewById(R.id.button);
        cancel = findViewById(R.id.button16);
        searchText = findViewById(R.id.textView25);
        if(getIntent()!=null && getIntent().getExtras()!=null){
            Bundle bundle = getIntent().getExtras();
            searchText.setText("\""+ bundle.getString("search_input")+"\"");
            searchText.setTextColor(Color.GRAY);
        }else{
            System.out.println("not found");
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("db", "insertDatabase");
                bundle.putInt("GROCERY_ID", id);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });


    }
}
