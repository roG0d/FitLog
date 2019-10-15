package a.darkr.fitlog1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Button;

import java.util.ArrayList;

public class Routines extends AppCompatActivity{

    private ListView mListView;
    private Button addRoutine;
    DatabaseRoutines mDatabaseRoutines;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        BottomNavigationView bottomNav=(BottomNavigationView) findViewById(R.id.BottomBar);
        Menu menu=bottomNav.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar:
                        Intent intentCalendar=new Intent(Routines.this,Timetable.class);
                        startActivity(intentCalendar);
                        break;

                    case R.id.navigation_exercises:
                        Intent intentExercise=new Intent(Routines.this,Exercises.class);
                        startActivity(intentExercise);
                        break;

                    case R.id.navigation_routines:
                        Intent intentRoutines=new Intent(Routines.this,Routines.class);
                        startActivity(intentRoutines);
                        break;
                }
                return false;
            }
        });

        mListView = findViewById(R.id.list_routine);
        addRoutine = findViewById(R.id.add_routine);
        mDatabaseRoutines = new DatabaseRoutines(this);

        Cursor data= mDatabaseRoutines.getAllData();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);



        addRoutine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBtn=new Intent(Routines.this, CreateRoutine.class);
                startActivity(intentBtn);
            }
        });
    }
}
