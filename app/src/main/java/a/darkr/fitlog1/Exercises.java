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
import android.widget.AdapterView;
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
        mListView= findViewById(R.id.listEx);
        mDataBaseHelper = new DatabaseHelper(this);


        BottomNavigationView bottomNav= findViewById(R.id.BottomBar);
        Menu menu=bottomNav.getMenu();
        MenuItem menuItem=menu.getItem(1);
        menuItem.setChecked(true);

        //Cursor with all the data from the table in order to put it onto the ListView
        Cursor data=mDataBaseHelper.getAllData();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //Retrieving the id using the name
               String name = parent.getItemAtPosition(position).toString();
               Cursor data = mDataBaseHelper.getItemID(name);
               Integer itemID = -1;
               while(data.moveToNext()){
                   itemID=data.getInt(0 );
               }
               if(itemID >-1){
                   Log.d(TAG, "OnItemClick: The id is: "+ itemID);
                   Intent editScreenIntent = new Intent(Exercises.this,ExerciseDetails.class);

                   //Cursor with all the info of the table given a ID
                   Cursor data1 = mDataBaseHelper.getDataFromID(itemID);
                   String name1=""; Integer reps=0; Integer series=0;

                   //This is necessary for the cursor to asign each value of the column to each variable
                   while(data1.moveToNext()){
                        name1 = data1.getString(0);
                        reps = Integer.parseInt(data1.getString(1));
                        series = Integer.parseInt(data1.getString(2));
                   }

                   //Extras to foward the information to another Activity
                   editScreenIntent.putExtra("id", itemID);
                   editScreenIntent.putExtra("name", name1);
                   editScreenIntent.putExtra("reps", reps);
                   editScreenIntent.putExtra("series", series);
                   startActivity(editScreenIntent);
               }else{
                   Log.d(TAG,"No ID associated with that name");
               }
            }
        });



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
        //       exerciseList();

    }

    //See the whole list of exercises
 /*   private void exerciseList(){
        Cursor data=mDataBaseHelper.getAllData();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        mListView.setAdapter(adapter);
    }*/




}
