package a.darkr.fitlog1;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RoutineDetails extends AppCompatActivity {

    private static final String TAG = "RoutineDetails";
    private ListView listExs;
    private Button editBtn;
    private Button deleteBtn;
    private TextView routineName;
    private Integer idRoutine;
    private DatabaseRoutines mDatabaseRoutines;
    private String nameRoutine;
    private String exsRoutines;
    private DatabaseExercises mDatabaseExercises;
    private List<Integer> idExList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_details);

        editBtn = findViewById(R.id.btn_edit_routine);
        deleteBtn = findViewById(R.id.btn_delete_routine);
        routineName = findViewById(R.id.routine_name_details);
        listExs = findViewById(R.id.list_routine_exs_details);

        Intent Routine = getIntent();
        idRoutine = Routine.getIntExtra("id", -1);
        Log.d(TAG,"Routine id :"+idRoutine);

        mDatabaseRoutines =  new DatabaseRoutines(this);
        Cursor dataRoutine = mDatabaseRoutines.getDataFromID(idRoutine);
        while (dataRoutine.moveToNext()){
            nameRoutine = dataRoutine.getString(0);
            exsRoutines = dataRoutine.getString(1);
        }

        routineName.setText(nameRoutine);


        mDatabaseExercises = new DatabaseExercises(this);
        idExList = new ArrayList<>();
        String[] idSplits = exsRoutines.split(",");
        for(String idExStr:idSplits){
            Integer idExInt = Integer.parseInt(idExStr);
            idExList.add(idExInt);
        }

        List <String> namesEx = new ArrayList<>();
        for(Integer idExs:idExList){
            Cursor dataEx = mDatabaseExercises.getDataFromID(idExs);
            while(dataEx.moveToNext()){
                String nm = dataEx.getString(0);
                namesEx.add(nm);
            }
        }
        ListAdapter adapterNames = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,namesEx);
        listExs.setAdapter(adapterNames);


        listExs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = parent.getItemAtPosition(position).toString();
                Intent exsDetails = new Intent(RoutineDetails.this,ExerciseDetails.class);

                Cursor dataId = mDatabaseExercises.getItemID(name);
                Integer itemID = -1;
                while (dataId.moveToNext()) {
                    itemID = dataId.getInt(0);
                }

                Cursor data = mDatabaseExercises.getDataFromID(itemID);
                String name1=""; Integer reps = 0; Integer series = 0;
                while(data.moveToNext()){
                    name1 = data.getString(0);
                    reps = Integer.parseInt(data.getString(1));
                    series = Integer.parseInt(data.getString(2));
                }

                //Extras to forward the information to another Activity
                exsDetails.putExtra("id", itemID);
                exsDetails.putExtra("name", name1);
                exsDetails.putExtra("reps", reps);
                exsDetails.putExtra("series", series);
                startActivity(exsDetails);

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDatabaseRoutines.deleteData(idRoutine);
                Log.d(TAG, "onClick: routine deleted");
                Intent dlt = new Intent(RoutineDetails.this,Routines.class);
                toastMessage("Workout deleted");
                startActivity(dlt);
            }
        });






    }
    public void toastMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
