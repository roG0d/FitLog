package a.darkr.fitlog1;

import android.content.Intent;
import android.provider.Contacts;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRoutine extends AppCompatActivity {
    private static final String TAG="CreateRoutine";

    DatabaseRoutines mDatabaseRoutines;
    private EditText name;
    private Button addExercises;
    private Button finish;
    private Integer idEx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_routine);

        name = findViewById(R.id.routine_name);
        addExercises = findViewById(R.id.btn_add_exercises);
        finish = findViewById(R.id.btn_finish_routine);

        addExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent listExs = new Intent(CreateRoutine.this, ListExercises.class);
                startActivityForResult(listExs,1);
            }
        });


    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_CANCELED) {

        } else {

            idEx = data.getExtras().getInt("id",-1);
            Log.d(TAG, "id of the exercises: "+ idEx);

            //Conseguido: pasar el id del ejercicio de la vista ListtExercises
            //TO DO cojer el ejercicio con id X y añadirlo a la rutina y demas


            }
        }
}
