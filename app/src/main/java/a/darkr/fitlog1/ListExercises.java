package a.darkr.fitlog1;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListExercises extends AppCompatActivity {

    private ListView list;
    DatabaseExercises mDataBaseExercises;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        list = findViewById(R.id.list_exercises);
        mDataBaseExercises = new DatabaseExercises(this);

        Cursor data= mDataBaseExercises.getAllData();
        ArrayList<String> listData=new ArrayList<>();
        while(data.moveToNext()){
            listData.add(data.getString(1));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        list.setAdapter(adapter);
    }
}
