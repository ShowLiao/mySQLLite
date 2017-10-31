package com.example.show.mysqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Item> items;
    ListView listItems;
    ArrayAdapter<Item> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = (ListView) findViewById(R.id.listItems);

        TodoItemDB db = TodoItemDB.getsInstance(this);
        //
        Item item = new Item();
        item.setDate("1/3/2018");
        item.setLocation("Golden Gate Park, SF, CA");
        item.setTask("Dinner");
//        Spinner spinner = (Spinner) findViewById(R.id.spinnerPriority);
        int priority = 2;
//        if (spinner.getContext().toString().equals("Low"))
//            priority = 2;
//        else if (spinner.getContext().toString().equals("Mid"))
//            priority = 1;
        item.setPriority(priority);
        db.addItem(item);
        //
        items = db.queryAll();
        Log.e("onCreateView", "empty?");
        for (Item it: items) {
            Log.e("task:", it.getTask());
            Log.e("date:", it.getDate());
            Log.e("location:", it.getLocation());
        }
        ItemAdapter adapter = new ItemAdapter(this, items);
//        itemAdapter = new ArrayAdapter<Item>(this, items);
        listItems.setAdapter(adapter);
    }

    public void query() {

//        TodoItemDB db = new TodoItemDB(this);


    }
    public void onAdd(View view) {

        Item item = new Item();
        item.setDate("1/1/2018");
        item.setLocation("Alamo Square Playground");
        item.setTask("Play");
        Spinner spinner = (Spinner) findViewById(R.id.spinnerPriority);
        int priority = 0;
        if (spinner.getContext().toString().equals("Low"))
            priority = 2;
        else if (spinner.getContext().toString().equals("Mid"))
            priority = 1;
        item.setPriority(priority);

        TodoItemDB db = TodoItemDB.getsInstance(this);
        db.addItem(item);
    }

    public void onUpdate(View view) {
    }

    public void onDel(View view) {
    }
}
