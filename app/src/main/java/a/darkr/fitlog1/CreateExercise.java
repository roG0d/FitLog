package a.darkr.fitlog1;

import android.support.annotation.Nullable;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateExercise extends AppCompatActivity {

    DatabaseExercises mDataBaseExercises;
    private Button btnFinish;
    private EditText name, reps, series;
    private Integer idEdit;
    private TextView title;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        title = findViewById(R.id.title_create);
        name= (EditText) findViewById(R.id.textName);
        reps= (EditText) findViewById(R.id.textReps);
        series= (EditText) findViewById(R.id.textSeries);
        mDataBaseExercises =new DatabaseExercises(this);

        Intent receivedIntent =getIntent();
        idEdit = receivedIntent.getIntExtra("id",-1);
        if(idEdit == -1){
        title.setText("Creating an exercise");
        name.setText("");
        reps.setText("");
        series.setText("");
        }else {
            String nameEdit=receivedIntent.getStringExtra("name");
            Integer repsEdit = receivedIntent.getIntExtra("reps",0);
            Integer seriesEdit = receivedIntent.getIntExtra("series",0);
            title.setText("Updating an exercise");
            name.setText(nameEdit);
            reps.setText(repsEdit.toString());
            series.setText(seriesEdit.toString());
        }



        btnFinish =(Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //First id is checked to see if there's an exercise to be updated
                //if not is a new exercise
                if(idEdit == -1) {
                    String nm = name.getText().toString();
                    Integer rps = Integer.parseInt(reps.getText().toString());
                    Integer srs = Integer.parseInt(series.getText().toString());
                    if (nm.length() == 0) {
                        toastMessage("You have to put a name");
                    } else {
                        addData(nm, rps, srs);
                        Intent success = new Intent(CreateExercise.this, Exercises.class);
                        startActivity(success);
                    }
                }else{
                    String nm = name.getText().toString();
                    Integer rps = Integer.parseInt(reps.getText().toString());
                    Integer srs = Integer.parseInt(series.getText().toString());
                    if (nm.length() == 0) {
                        toastMessage("You have to put a name");
                    } else {
                        updateData(idEdit, nm, rps, srs);
                        Intent success = new Intent(CreateExercise.this, Exercises.class);
                        startActivity(success);

                        //TO DO: updateData: https://www.youtube.com/watch?v=nY2bYJyGty8
                    }
                }
            }
        });
    }
    public void addData(String name, Integer reps, Integer series){
        boolean insertData= mDataBaseExercises.addData(name, reps, series, 0.0);
        if(insertData){
       Toast.makeText(this, "Exercise successfully added!", Toast.LENGTH_SHORT ).show();
        }
        else{
            Toast.makeText(this, "Ups! Fail adding the exercise", Toast.LENGTH_SHORT ).show();
        }
    }

    //FOR DOING TOAST MESSAGES
    public void toastMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void updateData(Integer id, String name, Integer reps, Integer series){
        boolean updateData= mDataBaseExercises.updateData(id, name, reps, series);
        if(updateData){
            Toast.makeText(this, "Exercise successfully updated!", Toast.LENGTH_SHORT ).show();
        }
        else{
            Toast.makeText(this, "Ups! Fail updating the exercise", Toast.LENGTH_SHORT ).show();
        }



        //TO DO: updateData: https://www.youtube.com/watch?v=nY2bYJyGty8

    }
}
