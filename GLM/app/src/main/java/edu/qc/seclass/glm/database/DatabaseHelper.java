package edu.qc.seclass.glm.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import edu.qc.seclass.glm.models.*;
import edu.qc.seclass.glm.models.GroceryList;
import edu.qc.seclass.glm.models.ItemList;
import edu.qc.seclass.glm.database.Contract.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final int DATABASE_VERSION = 1;

    private Context context = null;

    //Constructor
    public DatabaseHelper(Context context) {
        super(context, Config.DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    private static final String CREATE_ITEMLIST_TABLE = "CREATE TABLE " + Config.TABLE_ITEMS_LIST + " ("
            + Config.COLUMN_ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Config.COLUMN_ITEM_TYPE_ID_ + " INTEGER, "
            + Config.COLUMN_ITEM_TITLE + " TEXT NOT NULL, "
            + Config.COLUMN_ITEM_PRICE + " TEXT , "
            + Config.COLUMN_ITEM_QUANTITY + " INTEGER ,"
            + Config.COLUMN_ITEM_GROCERY_ID + " INTEGER  " + ")";

    private static final String CREATE_ITEM_TYPE_TABLE =
            "CREATE TABLE " + Config.TABLE_ITEM_TYPE + " (" +
                    Config.COLUMN_ITEM_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Config.COLUMN_ITEM_TYPE_TITLE + " TEXT," +
                    Config.COLUMN_ITEM_TYPE_DESCRIPTION + " TEXT)";

    private static final String CREATE_GROCERY_LIST_TABLE =
            "CREATE TABLE " + Config.TABLE_GROCERY_LIST + " (" +
                    Config.COLUMN_GROCERY_LIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    Config.COLUMN_GROCERY_LIST_TITLE + " TEXT)";


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a table
        try {
            db.execSQL(CREATE_ITEMLIST_TABLE);
            db.execSQL(CREATE_ITEM_TYPE_TABLE);
            db.execSQL(CREATE_GROCERY_LIST_TABLE);
            db.execSQL(DbItemType.QUERY_CREATE_TABLE);
            db.execSQL(DBItem.QUERY_CREATE_TABLE);



            ItemTypes bakery = initializeItemType(db, "bakery");
            ItemTypes beverages = initializeItemType(db, "beverages");
            ItemTypes cannedGoods = initializeItemType(db, "canned goods");
            ItemTypes dairy = initializeItemType(db, "dairy");
            ItemTypes dryBakingGoods =initializeItemType(db, "dry/baking goods");
            ItemTypes frozenFood = initializeItemType(db, "frozen food");
            ItemTypes meat = initializeItemType(db, "meat");
            ItemTypes produce = initializeItemType(db, "produce");
            ItemTypes cleaners = initializeItemType(db, "cleaners");
            ItemTypes paperGoods = initializeItemType(db, "paper goods");
            ItemTypes personalCare = initializeItemType(db, "personal care");
            ItemTypes other = initializeItemType(db, "other");




            initializeItem(db, "sandwich loaf", bakery);
            initializeItem(db, "dinner rolls", bakery);
            initializeItem(db, "bagels", bakery);

            initializeItem(db, "coffee", beverages);
            initializeItem(db, "tea", beverages);
            initializeItem(db, "juice", beverages);
            initializeItem(db, "soda", beverages);


            initializeItem(db, "vegetables", cannedGoods);
            initializeItem(db, "spaghetti sauce", cannedGoods);
            initializeItem(db, "ketchup", cannedGoods);

            initializeItem(db, "cheese", dairy);
            initializeItem(db, "milk", dairy);
            initializeItem(db, "yogurt", dairy);
            initializeItem(db, "butter", dairy);

            initializeItem(db, "cereals", dryBakingGoods);
            initializeItem(db, "flour", dryBakingGoods);
            initializeItem(db, "sugar",dryBakingGoods);
            initializeItem(db, "pasta",dryBakingGoods);

            initializeItem(db, "waffles", frozenFood);
            initializeItem(db, "ice cream", frozenFood);
            initializeItem(db, "toaster strudel", frozenFood);

            initializeItem(db, "apple", produce);
            initializeItem(db, "banana", produce);
            initializeItem(db, "carambola", produce);
            initializeItem(db, "parsnip", produce);

            initializeItem(db, "lunch meat", meat);
            initializeItem(db, "chicken", meat);
            initializeItem(db, "pork", meat);
            initializeItem(db, "beef", meat);

            initializeItem(db, "laundry detergent", cleaners);

            initializeItem(db, "paper towels", paperGoods);
            initializeItem(db, "toilet paper", paperGoods);

            initializeItem(db, "shampoo", personalCare);
            initializeItem(db, "soap", personalCare);

            initializeItem(db, "batteries", other);
        } catch (Exception e) {
            Log.d("TAG", "error to create table :" + e.getLocalizedMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop older table
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ITEM_TYPE);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_ITEMS_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + Config.TABLE_GROCERY_LIST);
        onCreate(db);

    }

    public void insertItemInDB(ItemList itemList) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_ITEM_TITLE, itemList.getName());
        contentValues.put(Config.COLUMN_ITEM_PRICE, itemList.getPrice());
        contentValues.put(Config.COLUMN_ITEM_GROCERY_ID, itemList.getGroceryID());
        contentValues.put(Config.COLUMN_ITEM_TYPE_ID_, itemList.getItemtpyeID());
        contentValues.put(Config.COLUMN_ITEM_QUANTITY,itemList.getQuantity());

        try {
            database.insertOrThrow(Config.TABLE_ITEMS_LIST, null, contentValues);
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            database.close();
        }
    }

    public ArrayList<ItemList> getAllItemList(int groceryListID) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        ArrayList<ItemList> arrayList = new ArrayList<>();

        /*String selectAssignment = "SELECT  * FROM " + Config.TABLE_ITEMS_LIST +" WHERE "
                + Config.COLUMN_ASSIGNMENT_COURSE_ID + " = " + course_id;*/

        String selectItemList = "SELECT  * FROM " + Config.TABLE_ITEMS_LIST + " WHERE "
                + Config.COLUMN_ITEM_GROCERY_ID + " = " + groceryListID;

        try {
            cursor = db.rawQuery(selectItemList, null);

            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int item_id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ITEM_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_ITEM_TITLE));
                        int grocery_id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ITEM_GROCERY_ID));
                        int item_type_id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ITEM_TYPE_ID_));
                        int quantity = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_ITEM_QUANTITY));
                        Log.d("TAG","database ..... "+quantity);

                        arrayList.add(new ItemList(item_id, title, 2, grocery_id, item_type_id, quantity));

                    } while (cursor.moveToNext());
                    return arrayList;
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to get assignment :" + e.getLocalizedMessage());
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return arrayList;

    }

    public int insertItemTypeInDB(String item_type) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_ITEM_TYPE_TITLE, item_type);
        contentValues.put(Config.COLUMN_ITEM_TYPE_DESCRIPTION, item_type + " description");
        try {
            database.insertOrThrow(Config.TABLE_ITEM_TYPE, null, contentValues);
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            database.close();
        }
        return this.checkItemType(item_type);
    }

    public int checkItemType(String item_type_name) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        String selectItemtype = "SELECT  * FROM " + Config.TABLE_ITEM_TYPE + " WHERE "
                + Config.COLUMN_ITEM_TYPE_TITLE + "=" + item_type_name;

        String[] projection = {
                Config.COLUMN_ITEM_TYPE_ID,
                Config.COLUMN_ITEM_TYPE_TITLE,
                Config.COLUMN_ITEM_TYPE_DESCRIPTION
        };

        String selection = Config.COLUMN_ITEM_TYPE_TITLE + " = ?";
        String[] selectionArgs = {item_type_name};

        try {
            cursor = db.query(
                    Config.TABLE_ITEM_TYPE,   // The table to query
                    projection,             // The array of columns to return (pass null to get all)
                    selection,              // The columns for the WHERE clause
                    selectionArgs,          // The values for the WHERE clause
                    null,                   // don't group the rows
                    null,                   // don't filter by row groups
                    null              // The sort order
            );

            if (cursor.getCount() > 0) {
                int index = 0;

                if (cursor.moveToFirst()) {
                    do {
                        index = cursor.getInt(0);
                        String name = cursor.getString(1);
                        Log.d("TAG", "index " + index + " ; " + name);
                    }
                    while (cursor.moveToNext());
                }
                db.close();
                return index;
            } else {
                db.close();
                return -1;
            }
        } catch (Exception e) {
            Log.d("TAG", "check items :" + e.getMessage());
        }
        return -2;
    }

    public void addGroceryListInDB(GroceryList groceryList) {

        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_GROCERY_LIST_TITLE, groceryList.getName());
        try {
            database.insertOrThrow(Config.TABLE_GROCERY_LIST, null, contentValues);
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            database.close();
        }
    }

    public ArrayList<GroceryList> getAllGroceryList() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        ArrayList<GroceryList> arrayList = new ArrayList<>();
        String selectGroceryList = "SELECT  * FROM " + Config.TABLE_GROCERY_LIST;

        try {
            cursor = db.rawQuery(selectGroceryList, null);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        int item_id = cursor.getInt(cursor.getColumnIndex(Config.COLUMN_GROCERY_LIST_ID));
                        String title = cursor.getString(cursor.getColumnIndex(Config.COLUMN_GROCERY_LIST_TITLE));
                        arrayList.add(new GroceryList(item_id, title));
                    } while (cursor.moveToNext());
                    return arrayList;
                }
            }
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to get assignment :" + e.getLocalizedMessage());
        } finally {
            if (cursor != null)
                cursor.close();
            db.close();
        }
        return arrayList;
    }

    public boolean changeGroceryListNameInDB(String newName, int grocer_id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_GROCERY_LIST_TITLE, newName);

        try {
             return  sqLiteDatabase.update(Config.TABLE_GROCERY_LIST, contentValues,
                    Config.COLUMN_GROCERY_LIST_ID + "="+grocer_id,null)>0;
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return false;

    }
    public boolean deleteRowInDB(int grocer_id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        try {
            if(deleteItemInDB(grocer_id,sqLiteDatabase)) {
            }
            return sqLiteDatabase.delete(Config.TABLE_GROCERY_LIST,
                    Config.COLUMN_GROCERY_LIST_ID + "=" + grocer_id, null) > 0;

        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return false;

    }

    public boolean deleteItemInDBwithInstance(int groceryID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        boolean status = deleteItemInDB(groceryID,sqLiteDatabase);
        sqLiteDatabase.close();
        return status;
    }
    public boolean deleteItemInDB(int groceryID, SQLiteDatabase sqLiteDatabase){

        try {
            return sqLiteDatabase.delete(Config.TABLE_ITEMS_LIST,
                    Config.COLUMN_ITEM_GROCERY_ID + "="+groceryID,null)>0;
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
        }
        return false;
    }

    public boolean updateItemInDB(int itemID, int newQty){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Config.COLUMN_ITEM_QUANTITY, newQty);

        try {
            return  sqLiteDatabase.update(Config.TABLE_ITEMS_LIST, contentValues,
                    Config.COLUMN_ITEM_ID + "="+itemID,null)>0;
        } catch (Exception e) {
            Toast.makeText(context, "Operation Failed: " + e.getMessage(), Toast.LENGTH_LONG).show();
            Log.d("TAG", "error to set assignment :" + e.getLocalizedMessage());
        } finally {
            sqLiteDatabase.close();
        }
        return false;
    }

    /*** item table ***/

    /**
     * gets an item from the database based on similar item name
     *
     * @param   name the name of the item searched for
     * @return  the a list of similar item names
     */
    public List<Item> getItemsLike(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT *"
                + " FROM " + DBItem.TABLE_NAME
                + " WHERE " + DBItem.COLUMN_NAME_UNIQUE_NAME + " LIKE '%" + name + "%'"
                + " ORDER BY " + DBItem.COLUMN_NAME_ITEM_TYPE_ID + ", " +DBItem.COLUMN_NAME_UNIQUE_NAME;

        Cursor c = db.rawQuery(selectQuery, null);

        List<Item> itemsLike = new ArrayList<>();

        if(!c.moveToFirst()) {
            c.close(); db.close();
            return itemsLike;
        }

        do {
            itemsLike.add(new Item(c.getInt(c.getColumnIndex(DBItem._ID)),
                    c.getString(c.getColumnIndex(DBItem.COLUMN_NAME_UNIQUE_NAME)),
                    getItemType(c.getInt(c.getColumnIndex(DBItem.COLUMN_NAME_ITEM_TYPE_ID)))));
        } while (c.moveToNext());

        c.close(); db.close();

        return itemsLike;

    }


    /**
     * inserting items to database
     * @param name item name
     * @param itemType item type
     * @return the newly created item
     */
    public Item insertItem(String name, ItemTypes itemType){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DBItem.COLUMN_NAME_UNIQUE_NAME, name);
        values.put(DBItem.COLUMN_NAME_ITEM_TYPE_ID, itemType.getId());

        int id = (int) db.insert(DBItem.TABLE_NAME, null, values);

        db.close();

        return id > 0 ? new Item(id, name, itemType) : null;

    }

    /**
     * getting all items from database in the order of ascending item type and item name
     * @return a list of all items
     */
    public List<Item> getAllItems(){
        List<Item> items = new ArrayList<>();

        String selectQuery = "SELECT * "
                + " FROM " + DBItem.TABLE_NAME
                + " ORDER BY " + DBItem.COLUMN_NAME_UNIQUE_NAME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                items.add(new Item(
                        c.getInt(c.getColumnIndex(DBItem._ID)),
                        c.getString(c.getColumnIndex(DBItem.COLUMN_NAME_UNIQUE_NAME)),
                        getItemType(c.getInt(c.getColumnIndex(DBItem.COLUMN_NAME_ITEM_TYPE_ID)))
                ));
            } while (c.moveToNext());
        }

        c.close(); db.close();

        return items;
    }

    /**
     * getting all items from database based on type
     * @param itemType item type wanted
     * @return list of items based on the specified item type
     */

    public List<Item> getAllItems(ItemTypes itemType) {

        List<Item> items = new ArrayList<>();

        String selectQuery = "SELECT * "
                + " FROM " + DBItem.TABLE_NAME
                + " WHERE " + DBItem.COLUMN_NAME_ITEM_TYPE_ID + " = " + itemType.getId()
                + " ORDER BY " + DBItem.COLUMN_NAME_UNIQUE_NAME + " ASC";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                items.add(new Item(
                        c.getInt(c.getColumnIndex(DBItem._ID)),
                        c.getString(c.getColumnIndex(DBItem.COLUMN_NAME_UNIQUE_NAME)),
                        itemType
                ));
            } while (c.moveToNext());
        }

        c.close(); db.close();

        return items;

    }

    /**
     * gets a list of all Items of a given ItemTypes from the database.
     *
     * @param   itemTypeId the id for the type of item wanted
     * @return  a list of all items of a given ItemType's id
     */
    public List<Item> getAllItems(int itemTypeId) {

        return getAllItems(getItemType(itemTypeId));

    }

    /**
     * gets the number of items in database
     *
     * @return  the total number of items
     */
    public int getItemsCount() {

        String selectQuery = "SELECT * FROM " + DBItem.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        int count = c.getCount();

        c.close(); db.close();

        return count;

    }






    /*** item types table ***/

    /**
     * gets an item type from the database based off of item type's id
     *
     * @param   id the id of the desired ItemTypes
     * @return  the ItemTypes for the given id
     */
    public ItemTypes getItemType(int id) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT *"
                + " FROM " + DbItemType.TABLE_NAME
                + " WHERE " + DbItemType._ID + " = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if(!c.moveToFirst()) {
            c.close(); db.close();
            return null;
        }

        ItemTypes itemType = new ItemTypes(
                id,
                c.getString(c.getColumnIndex(DbItemType.COLUMN_NAME_NAME))
        );

        c.close(); db.close();

        return itemType;

    }

    /**
     * gets a list of all ItemTypes in the database.
     *
     * @return  a list of all ItemTypes
     */
    public List<ItemTypes> getAllItemTypesInDB() {
//        System.out.println("invoking  getAllItemTypesInDB");
//        Log.d(TAG, "getAllItemTypesInDB: invoked");
        List<ItemTypes> itemTypes = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + DbItemType.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
//        System.out.println("getAllItemTypesInDBt: size of cursor"+ c.getCount());
        if (c.moveToFirst()) {
            System.out.println("getAllItemTypesInDB: first cursor");
            do {
//                System.out.println("getAllItemTypesInDB: while cursor");
//                System.out.println("getAllItemTypesInDB: itemType: "+ c.getString(c.getColumnIndex(DbItemType.COLUMN_NAME_NAME)));
                itemTypes.add(new ItemTypes(
                        c.getInt(c.getColumnIndex(DbItemType._ID)),
                        c.getString(c.getColumnIndex(DbItemType.COLUMN_NAME_NAME))
                ));

            } while (c.moveToNext());
        }
//        Log.d(TAG, "getAllItemTypesInDB() returned: " + itemTypes);
        c.close(); db.close();
//        System.out.println(itemTypes.size());
        return itemTypes;

    }

    /**
     * gets an ItemTypes from the database.
     *
     * @param   name the name of the item type
     * @return  the ItemTypes with the given name
     */
    public ItemTypes getItemType(String name) {

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT *"
                + " FROM " + DbItemType.TABLE_NAME
                + " WHERE " + DbItemType.COLUMN_NAME_NAME + " = '" + name + "'";

        Cursor c = db.rawQuery(selectQuery, null);

        if(!c.moveToFirst()) {
            db.close();
            return null;
        }

        ItemTypes itemType = new ItemTypes(
                c.getInt(c.getColumnIndex(DbItemType._ID)),
                name
        );

        c.close(); db.close();

        return itemType;

    }

    /**
     * get count of all item types
     * @return count of all item types
     */

    public int getItemTypesCount() {

        String selectQuery = "SELECT * FROM " + DbItemType.TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        int count = c.getCount();

        c.close(); db.close();

        return count;

    }

    /*** for initial population in the database ***/
    private ItemTypes initializeItemType(SQLiteDatabase db, String name) {

        ContentValues values = new ContentValues();
        values.put(DbItemType.COLUMN_NAME_NAME, name);

        int id = (int) db.insert(DbItemType.TABLE_NAME, null, values);

        return id > 0 ? new ItemTypes(id, name) : null;

    }


    private Item initializeItem(SQLiteDatabase db, String name, ItemTypes itemType) {

        ContentValues values = new ContentValues();
        values.put(DBItem.COLUMN_NAME_UNIQUE_NAME, name);
        values.put(DBItem.COLUMN_NAME_ITEM_TYPE_ID, itemType.getId());

        int id = (int) db.insert(DBItem.TABLE_NAME, null, values);

        return id > 0 ? new Item(id, name, itemType) : null;

    }




}