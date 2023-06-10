package edu.qc.seclass.glm.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;

import edu.qc.seclass.glm.R;
import edu.qc.seclass.glm.activities.MainActivity;
import edu.qc.seclass.glm.database.DatabaseHelper;
import edu.qc.seclass.glm.models.GroceryList;

public class CustomAdapterGroceryList extends BaseAdapter {

    Context context;
    ArrayList<GroceryList> groceryListArrayList;
    private AlertDialog dialog;
    private DatabaseHelper databaseHelper;

    public CustomAdapterGroceryList(Context context, ArrayList<GroceryList> groceryListArrayList) {
        this.context = context;
        this.groceryListArrayList = groceryListArrayList;
        databaseHelper = new DatabaseHelper(context);
    }

    @Override
    public int getCount() {
        return groceryListArrayList.size();
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

        convertView = LayoutInflater.from(context).inflate(R.layout.content_listsrow, null);
        TextView name;
        ImageView editBtn,deleteBtn;
        name = convertView.findViewById(R.id.nameTv);
        editBtn = convertView.findViewById(R.id.editBtn);
        deleteBtn = convertView.findViewById(R.id.deleteBtn);

        name.setText(groceryListArrayList.get(position).getName());
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(context, EditListItemActivity.class);
               *//* intent.putExtra("GROCERY_ID",groceryListArrayList.get(position).getName());
                intent.putExtra("GROCERY_NAME",groceryListArrayList.get(position).getGroceryId());*//*
                Bundle bundle = new Bundle();
                bundle.putString("TITLE",groceryListArrayList.get(position).getName());
                bundle.putInt("ID",groceryListArrayList.get(position).getGroceryId());
                intent.putExtras(bundle);
                Log.d("TAG","id "+groceryListArrayList.get(position).getGroceryId());
                Log.d("TAG","Name "+groceryListArrayList.get(position).getName());

                context.startActivity(intent);*/

                openPopopView(groceryListArrayList.get(position).getGroceryId());
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(databaseHelper.deleteRowInDB(groceryListArrayList.get(position).getGroceryId()))
                {
                    Toast.makeText(context, "done...", Toast.LENGTH_SHORT).show();
                    /*Activity activity = (Activity) context;
                    activity.recreate();*/
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent); // start same activity
                    ((Activity) context).overridePendingTransition(0,0);


                }
                else {
                    Toast.makeText(context, "error please try again...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return convertView;
    }
    private void openPopopView(int id) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context,R.style.Theme_AppCompat_Light_Dialog_Alert);
        final View inflate = LayoutInflater.from(context).inflate(R.layout.grocerylist_name,null,true);
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

                if(databaseHelper.changeGroceryListNameInDB(listName.getText().toString(),id))
                {
                    Toast.makeText(context, "done...", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                    /*Activity activity = (Activity) context;
                    activity.recreate();*/
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent); // start same activity
                    ((Activity) context).overridePendingTransition(0,0);
                }
                else {
                    Toast.makeText(context, "error please try again...", Toast.LENGTH_SHORT).show();
                }

            }
        });
        this.dialog = builder.create();
        dialog.show();
    }
}
