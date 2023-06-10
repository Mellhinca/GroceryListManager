package edu.qc.seclass.glm.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.activities.EditListItemActivity;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.ItemList;

public class CustomAdapter extends BaseAdapter {

    ArrayList<ItemList> itemListArrayList;
    Context context;

    public CustomAdapter(Context context, ArrayList<ItemList> itemListArrayList) {
        this.context = context;
        this.itemListArrayList = itemListArrayList;
    }

    @Override
    public int getCount() {
        return itemListArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.content_listsrow_checkbox, null);
        TextView name,qty;
        CheckBox checkBox;
        ImageView edit,delete;
        name = convertView.findViewById(R.id.nameItemTv);
        qty = convertView.findViewById(R.id.qtyItemTv);
        edit = convertView.findViewById(R.id.editBtn);
        delete = convertView.findViewById(R.id.deleteBtn);
        qty.setText("Qty: "+itemListArrayList.get(position).getQuantity());
        checkBox = convertView.findViewById(R.id.checkMarkItem);
        name.setText(itemListArrayList.get(position).getName());
        checkBox.setChecked(itemListArrayList.get(position).getChecked());

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("TAG","checked..."+isChecked);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EditListItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("TITLE",itemListArrayList.get(position).getName());
                bundle.putInt("ITEM_ID",itemListArrayList.get(position).getItemId());
                bundle.putInt("QTY",itemListArrayList.get(position).getQuantity());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }
}
