package com.example.mohamed_araby.inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mohamed_araby.inventory.data.ProductContract;
import com.example.mohamed_araby.inventory.data.ProductDbHelper;

public class MainActivity extends AppCompatActivity {
private ProductDbHelper productDbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        productDbHelper=new ProductDbHelper(getApplicationContext());
        insertPet(getApplicationContext());
        displayDatabaseInfo(getApplicationContext());
    }

    private void insertPet(Context context) {
        // Gets the database in write mode
        SQLiteDatabase db = productDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and Toto's pet attributes are the values.
        ContentValues values = new ContentValues();
        values.put(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME, "Toto");
        values.put(ProductContract.ProductEntry.COLUMN_QUANTITY, 10);
        values.put(ProductContract.ProductEntry.COLUMN_PRICE ,555555);
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,"MOHAMED");
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER,"01099057109");
        values.put(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL,"mohamedaraby1296@gmail.com");


        // Insert a new row for Toto in the database, returning the ID of that new row.
        // The first argument for db.insert() is the pets table name.
        // The second argument provides the name of a column in which the framework
        // can insert NULL in the event that the ContentValues is empty (if
        // this is set to "null", then the framework will not insert a row when
        // there are no values).
        // The third argument is the ContentValues object containing the info for Toto.
        long newRowId = db.insert(ProductContract.ProductEntry.TABLE_NAME, null, values);
    }






    private void displayDatabaseInfo(Context context) {
        // Create and/or open a database to read from it
        SQLiteDatabase db = productDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                ProductContract.ProductEntry._ID,
                ProductContract.ProductEntry.COLUMN_PRODUCT_NAME,
                ProductContract.ProductEntry.COLUMN_PRICE,
                ProductContract.ProductEntry.COLUMN_QUANTITY,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER,
                ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                ProductContract.ProductEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            Toast.makeText(this, "The pets table contains " + cursor.getCount() + " pets.\n\n", Toast.LENGTH_SHORT).show();

            Toast.makeText(this, ProductContract.ProductEntry._ID +
                    " - " + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME+
                    " - "+ ProductContract.ProductEntry.COLUMN_QUANTITY+
                    " - "+ ProductContract.ProductEntry.COLUMN_PRICE+
                    " - "+ ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL+
                    " - "+ ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME
                    ,Toast.LENGTH_SHORT).show();
System.out.println(ProductContract.ProductEntry._ID +
        " - " + ProductContract.ProductEntry.COLUMN_PRODUCT_NAME+
        " - "+ ProductContract.ProductEntry.COLUMN_QUANTITY+
        " - "+ ProductContract.ProductEntry.COLUMN_PRICE+
        " - "+ ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL+
        " - "+ ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME);

            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(ProductContract.ProductEntry._ID);
            int pName = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRODUCT_NAME);
            int pPrice = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_PRICE);
            int pQuantity = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_QUANTITY);
            int sName = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_NAME);
            int sPhone= cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);
            int sEmail = cursor.getColumnIndex(ProductContract.ProductEntry.COLUMN_SUPPLIER_EMAIL);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                String ProductName = cursor.getString(pName);
                int ProductPrice = cursor.getInt(pPrice);
                int productQuanityt = cursor.getInt(pQuantity);
                String  supplierName = cursor.getString(sName);
                String supplierPhone=cursor.getString(sPhone);
                String supplierEmail=cursor.getString(sEmail);
                // Display the values from each column of the current row in the cursor in the TextView
                Toast.makeText(this, currentID+
                                " - " + ProductName+
                                " - "+ ProductPrice+
                                " - "+ productQuanityt+
                                " - "+ supplierName+
                                " - "+ supplierPhone+
                        " - "+supplierEmail
                        ,Toast.LENGTH_SHORT).show();
                System.out.println(currentID+
                        " - " + ProductName+
                        " - "+ ProductPrice+
                        " - "+ productQuanityt+
                        " - "+ supplierName+
                        " - "+ supplierPhone+
                        " - "+supplierEmail);
            }
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

}
