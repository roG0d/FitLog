package a.darkr.fitlog1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Exercises extends AppCompatActivity{
    private static final String TAG="Exercise";

    DatabaseHelper mDataBaseHelper;
    private ListView mListView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercises);
        mListView=(ListView) findViewById(R.id.listEx);


        BottomNavigationView bottomNav=(BottomNavigationView) findViewById(R.id.BottomBar);
        Menu menu=bottomNav.getMenu();
        MenuItem menuItem=menu.getItem(1);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar:
                        Intent intentCalendar=new Intent(Exercises.this,Timetable.class);
                        startActivity(intentCalendar);
                        break;

                    case R.id.navigation_exercises:
                        break;

                    case R.id.navigation_routines:
                        Intent intentRoutines=new Intent(Exercises.this,Routines.class);
                        startActivity(intentRoutines);

                        break;
                }
                return false;
            }
        });




        Button addExercise=(Button) findViewById(R.id.add_exercise);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBtn=new Intent(Exercises.this, CreateExercise.class);
                startActivity(intentBtn);
            }
        });

    }


    private void exerciseList(){
        Cursor data=mDataBaseHelper.getData();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
    }



}
