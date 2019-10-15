package a.darkr.fitlog1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseRoutines extends SQLiteOpenHelper {



    private static final String TAG="DatabaseRoutines";
    private static final  String TABLE_NAME="routine_table";
    private static final String COL0="ID";
    private static final String COL1="name";
    private static final String COL2="exercises";


    public DatabaseRoutines(Context context) {
        super(context, TABLE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 +" TEXT, " + COL2 +" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, Integer exercises){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String listExs="";
        listExs.concat(exercises.toString());
        contentValues.put(COL1, name);
        contentValues.put(COL2, exercises);

        Log.d(TAG, "addData: adding "+ name + " to " + TABLE_NAME);

        long result=db.insert(TABLE_NAME, null, contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateData(Integer id, String name, String exercises){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, exercises);
        long result = db.update(TABLE_NAME, contentValues,"ID = '"+id+"'",null);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean deleteData(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME,"ID = '"+id+"'", null);
        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT * FROM "+ TABLE_NAME;
        Cursor data=db.rawQuery(query,null);
        return data;

    }

    public Cursor getItemID(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String query= "SELECT ID FROM "+TABLE_NAME + " WHERE " + COL1 +" = '"+ name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getDataFromID(Integer id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT name, exercises FROM " + TABLE_NAME + " WHERE ID = " + id;
        Cursor data = db.rawQuery(query, null);
        return data;
    }


    public booelan addExs(Integer idR, String idExs){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor routineData = getDataFromID(idR);
        String exsRoutine="";
        while(routineData.moveToNext()){
            exsRoutine = routineData.getString(1);
        }
        if(exsRoutine.equals("")){
            exsRoutine.concat(idE.toString());
        }else{
            exsRoutine.concat(","+idE);
        }
        contentValues.put(COL2, exsRoutine);

        Log.d(TAG, "addEx: adding the exercise"+ exsRoutine);

        long result = db.update(TABLE_NAME, contentValues,"ID = '"+idR+"'",null);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }


    }
    }
    public boolean addEx(Integer idR, Integer idE){             //Exercises format: 1,2,3,4,5,6,etc... where int is the idEx
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor routineData = getDataFromID(idR);
        String exsRoutine="";
        while(routineData.moveToNext()){
            exsRoutine = routineData.getString(1);
        }
        if(exsRoutine.equals("")){
            exsRoutine.concat(idE.toString());
        }else{
            exsRoutine.concat(","+idE);
        }
        contentValues.put(COL2, exsRoutine);

        Log.d(TAG, "addEx: adding the exercise"+ exsRoutine);

        long result = db.update(TABLE_NAME, contentValues,"ID = '"+idR+"'",null);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }
}
