package a.darkr.fitlog1;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateRoutine extends AppCompatActivity {

    DatabaseRoutines mDatabaseRoutines;
    private EditText name;
    private Button addExercises;
    private Button finish;

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

                Intent success = new Intent(CreateRoutine.this, ListExercises.class);
                startActivity(success);
            }
        });

    }
}
