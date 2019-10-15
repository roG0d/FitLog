package a.darkr.fitlog1;

import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListExercises extends AppCompatActivity {

    private ListView list;
    DatabaseExercises mDataBaseExercises;
    private static final String TAG="Exercise";
    private Integer itemID;

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

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String name = parent.getItemAtPosition(position).toString();
                Cursor data = mDataBaseExercises.getItemID(name);
                itemID = -1;
                while (data.moveToNext()) {
                    itemID = data.getInt(0);
                }
                if (itemID > -1) {
                    Log.d(TAG, "OnItemClick: The id is: " + itemID);
                    Intent idExIntent = new Intent(ListExercises.this, CreateRoutine.class);
                    idExIntent.putExtra("id", itemID);
                    setResult(RESULT_OK, idExIntent);
                    finish();
                }
            }
        });
    }
}
