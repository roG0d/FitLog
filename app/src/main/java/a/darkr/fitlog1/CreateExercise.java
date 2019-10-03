package a.darkr.fitlog1;

import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CreateExercise extends AppCompatActivity {

    DatabaseHelper mDataBaseHelper;
    private Button btnFinish;
    private EditText name, reps, series;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_exercise);

        name= (EditText) findViewById(R.id.textName);
        reps= (EditText) findViewById(R.id.textReps);
        series= (EditText) findViewById(R.id.textSeries);
        mDataBaseHelper=new DatabaseHelper(this);




        btnFinish =(Button) findViewById(R.id.btnFinish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nm=name.getText().toString();
                Integer rps=Integer.parseInt(reps.getText().toString());
                Integer srs=Integer.parseInt(series.getText().toString());
                if(nm.length()==0){
                    toastMessage("You have to put a name");
                }else{
                    addData(nm,rps,srs);
                }
                Intent success=new Intent(CreateExercise.this, Exercises.class);
                startActivity(success);
            }
        });
    }
    public void addData(String name, Integer reps, Integer series){
        boolean insertData=mDataBaseHelper.addData(name, reps, series, 0.0);
        if(insertData){
       Toast.makeText(this, "Exercise succesfully added!", Toast.LENGTH_SHORT ).show();
        }
        else{
            Toast.makeText(this, "Ups! Fail adding the exercise", Toast.LENGTH_SHORT ).show();
        }
    }
    public void toastMessage(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
