package a.darkr.fitlog1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class Timetable extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);

        CalendarView calendar = (CalendarView) findViewById(R.id.calendar);

        TextView textDay=(TextView) findViewById(R.id.actualDay);
        Date currentDate=Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("EEEE, d");
        String dayFormat=df.format(currentDate);
        textDay.setText(dayFormat);



        BottomNavigationView bottomNav=(BottomNavigationView) findViewById(R.id.BottomBar);
        Menu menu=bottomNav.getMenu();
        MenuItem menuItem=menu.getItem(0);
        menuItem.setChecked(true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar:
                        Intent intentCalendar=new Intent(Timetable.this,Timetable.class);
                        startActivity(intentCalendar);
                        break;

                    case R.id.navigation_exercises:
                        Intent intentExercise=new Intent(Timetable.this,Exercises.class);
                        startActivity(intentExercise);
                        break;

                    case R.id.navigation_routines:
                        Intent intentRoutines=new Intent(Timetable.this,Routines.class);
                        startActivity(intentRoutines);

                        break;
                }
                return false;
            }
        });

    }
}

