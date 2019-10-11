package a.darkr.fitlog1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseExercises extends SQLiteOpenHelper {

    private static final String TAG="DatabaseExercises";
    private static final  String TABLE_NAME="exercise_table";
    private static final String COL0="ID";
    private static final String COL1="name";
    private static final String COL2="reps";
    private static final String COL3="series";
    private static final String COL4="kg";


    public DatabaseExercises(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COL1 +" TEXT, " + COL2 +" INTEGER, "+ COL3 + " INTEGER, "+ COL4 +" REAL)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, Integer reps, Integer series, Double kg){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, reps);
        contentValues.put(COL3, series);
        contentValues.put(COL4, kg);

        Log.d(TAG, "addData: adding "+ name + " to " + TABLE_NAME);

        long result=db.insert(TABLE_NAME, null, contentValues);

        if(result==-1){
            return false;
        }
        else{
            return true;
        }
    }

    public boolean updateData(Integer id, String name, Integer reps, Integer series){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, reps);
        contentValues.put(COL3, series);
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

    public Cursor getDataFromID(Integer id){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT name, reps, series FROM "+ TABLE_NAME + " WHERE ID = "+id;
        Cursor data=db.rawQuery(query,null);
        return data;

    }

    public Cursor getItemID(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        String query= "SELECT ID FROM "+TABLE_NAME + " WHERE " + COL1 +" = '"+ name + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public Cursor getItemReps(Integer id){
        SQLiteDatabase db=this.getWritableDatabase();
        String query= "SELECT COL2 FROM "+TABLE_NAME + " WHERE ID = '" + id + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
}
