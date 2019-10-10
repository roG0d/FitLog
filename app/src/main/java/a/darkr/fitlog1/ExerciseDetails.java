package a.darkr.fitlog1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ExerciseDetails extends AppCompatActivity {

    DatabaseHelper mDataBaseHelper;

    private TextView  exName;
    private TextView  exReps;
    private TextView  exSeries;
    private String name;
    private Integer id;
    private Integer reps;
    private Integer series;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_details);

        exName = findViewById(R.id.exercise_name);
        exReps = findViewById(R.id.exercise_reps2);
        exSeries = findViewById(R.id.exercise_series2);

        mDataBaseHelper = new DatabaseHelper(this);

        Intent receivedIntent =getIntent();
        id=receivedIntent.getIntExtra("id", -1);
        name= receivedIntent.getStringExtra("name");
        reps=receivedIntent.getIntExtra("reps", 0);
        series=receivedIntent.getIntExtra("series", 0);

        exName.setText(name);
        exReps.setText(reps.toString());
        exSeries.setText(series.toString());

    }
}
