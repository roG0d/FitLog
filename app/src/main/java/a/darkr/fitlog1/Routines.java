package a.darkr.fitlog1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class Routines extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routines);

        BottomNavigationView bottomNav=(BottomNavigationView) findViewById(R.id.BottomBar);
        Menu menu=bottomNav.getMenu();
        MenuItem menuItem=menu.getItem(2);
        menuItem.setChecked(true);
        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar:
                        Intent intentCalendar=new Intent(Routines.this,Timetable.class);
                        startActivity(intentCalendar);
                        break;

                    case R.id.navigation_exercises:
                        Intent intentExercise=new Intent(Routines.this,Exercises.class);
                        startActivity(intentExercise);
                        break;

                    case R.id.navigation_routines:
                        break;
                }
                return false;
            }
        });
    }
}
