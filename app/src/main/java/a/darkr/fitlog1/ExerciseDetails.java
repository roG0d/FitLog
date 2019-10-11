package a.darkr.fitlog1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ExerciseDetails extends AppCompatActivity {

    DatabaseExercises mDataBaseExercises;

    private TextView  exName;
    private TextView  exReps;
    private TextView  exSeries;
    private String name;
    private Integer id;
    private Integer reps;
    private Integer series;
    private Button edit;
    private Button delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        exName = findViewById(R.id.exercise_name);
        exReps = findViewById(R.id.exercise_reps2);
        exSeries = findViewById(R.id.exercise_series2);
        edit = findViewById(R.id.button_edit);
        delete = findViewById(R.id.button_delete);

        mDataBaseExercises = new DatabaseExercises(this);

        Intent receivedIntent =getIntent();
        id=receivedIntent.getIntExtra("id", -1);
        name= receivedIntent.getStringExtra("name");
        reps=receivedIntent.getIntExtra("reps", 0);
        series=receivedIntent.getIntExtra("series", 0);

        exName.setText(name);
        exReps.setText(reps.toString());
        exSeries.setText(series.toString());

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBtn=new Intent(ExerciseDetails.this, CreateExercise.class);
                intentBtn.putExtra("id", id);
                intentBtn.putExtra("name", name);
                intentBtn.putExtra("reps", reps);
                intentBtn.putExtra("series", series);
                startActivity(intentBtn);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean result = mDataBaseExercises.deleteData(id);
                if(result){
                    toastMsg("Exercise successfully deleted");
                    Intent intentBtn=new Intent(ExerciseDetails.this, Exercises.class);
                    startActivity(intentBtn);
                }else{
                    toastMsg("Ups, something went bad");

                }

            }
        });

    }
    public void toastMsg(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

    }
}
