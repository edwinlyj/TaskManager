package com.example.a16022895.taskmanager;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Task> al;
    ListView lv;
    ArrayAdapter aa;
    Button btAddTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView)findViewById(R.id.lvTask);
        al = new ArrayList<Task>();
        btAddTask =  (Button)findViewById(R.id.btnAddTask);

        btAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToEditActivity = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(moveToEditActivity, 9);
            }
        });

        DBHelper db = new DBHelper(MainActivity.this);
        al.clear();
        al.addAll(db.getAllSongs());
        db.close();

        aa = new ArrayTaskAdapter(MainActivity.this, R.layout.row, al);
        lv.setAdapter(aa);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9){
            lv = (ListView)findViewById(R.id.lvTask);
            al = new ArrayList<Task>();
            DBHelper dbh = new DBHelper(MainActivity.this);
            al.clear();
            al.addAll(dbh.getAllSongs());
            dbh.close();
            aa = new ArrayTaskAdapter(this, R.layout.row, al);

            lv.setAdapter(aa);
        }
    }
}
