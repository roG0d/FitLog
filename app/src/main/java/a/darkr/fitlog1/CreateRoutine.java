package a.darkr.fitlog1;

import android.content.Intent;
import android.database.Cursor;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class CreateRoutine extends AppCompatActivity {
    private static final String TAG="CreateRoutine";

    DatabaseRoutines mDatabaseRoutines;
    DatabaseExercises mDatabaseExercises;
    private EditText name;
    private Button addExercises;
    private Button finish;
    private Integer idEx;
    private ArrayList<String> listData;
    private ListView listExs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        name = findViewById(R.id.routine_name);
        addExercises = findViewById(R.id.btn_add_exercises);
        finish = findViewById(R.id.btn_finish_routine);
        listExs = findViewById(R.id.list_routine_exs);
        mDatabaseRoutines = new DatabaseRoutines(this);
        mDatabaseExercises = new DatabaseExercises(this);
        listData = new ArrayList<>();

        addExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listExs = new Intent(CreateRoutine.this, ListExercises.class);
                startActivityForResult(listExs,1);
            }
        });



        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.length()==0){
                    toastMessage("You have to put a name");
                }else{
                    String nm = name.getText().toString();

                    for(String ex:listData){
                        ex.concat()
                    }
                    mDatabaseRoutines.addData(nm,idEx);
                }



            }
        });*/
    }




    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {

        } else {

            idEx = data.getExtras().getInt("id",-1);
            Log.d(TAG, "id of the exercises: "+ idEx);

            //Conseguido: pasar el id del ejercicio de la vista ListtExercises
            //TO DO coger el ejercicio con id X y añadirlo a la rutina y demas


            }


        Cursor data1= mDatabaseExercises.getDataFromID(idEx);
        while(data1.moveToNext()){
            listData.add(data1.getString(0));
        }
        ListAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,listData);
        listExs.setAdapter(adapter);
        }

    public void toastMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
