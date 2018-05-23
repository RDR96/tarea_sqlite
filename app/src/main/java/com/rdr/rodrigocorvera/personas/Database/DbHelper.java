package com.rdr.rodrigocorvera.personas.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Entidades.Estudiante;
import com.rdr.rodrigocorvera.personas.R;

/**
 * Created by Rodrigo Corvera on 19/5/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static DbHelper myDb = null;
    Context context;
    SQLiteDatabase db;

    public DbHelper (Context context) {

        super(context,constants.DB_NAME,null,1);
        //context.deleteDatabase(constants.DB_NAME);
        this.context = context;

    }

    public static DbHelper getIntanceState(Context context) {
        if ( myDb == null ) {
            myDb = new DbHelper(context.getApplicationContext());
        }
        return myDb;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(constants.CREATE_TABLE_ALUMNO);
            db.execSQL(constants.CREATE_TABLE_CATEGORIA);
            db.execSQL(constants.CREATE_TABLE_NOTA);
            ContentValues values = new ContentValues();
            values.put(constants.NAME_CATEGORY,"Corto");
            db.insert(constants.TB_NAME_CATEGORIA, null, values);
            values.put(constants.NAME_CATEGORY,"Parcial");
            db.insert(constants.TB_NAME_CATEGORIA, null, values);
        } catch (SQLException e){

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(constants.DROP_TABLE_STUDENT);
        db.execSQL(constants.DROP_TABLE_CATEGORY);
        db.execSQL(constants.DROP_TABLE_GRADE);
        onCreate(db);
    }

    public Estudiante findStudent (String carnet) {
        Estudiante estudiante;

        String valueToFilter[] = {carnet};
        String column [] = {constants.ID_STUDENT};

        try{
            Cursor cursor = db.query(constants.TB_NAME_ALUMNO, column, constants.ID_STUDENT + "= ?", valueToFilter,null,null,null,null);

            cursor.moveToFirst();
            estudiante = new Estudiante(carnet, cursor.getString(1));

        }catch (SQLException e){
            e.printStackTrace();
            estudiante = null;
        }

        return estudiante;
    }

    public void add (String carnet, String name, String password) {
        db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(constants.ID_STUDENT, carnet);
        contentValues.put(constants.NAME_STUDENT, name);
        contentValues.put(constants.PASSWORD_STUDENT, password);

        db.insert(constants.TB_NAME_ALUMNO,null, contentValues);
        Toast.makeText(context, R.string.successful_insert, Toast.LENGTH_SHORT).show();

    }

    public int checkSingUp (String carnet, String password) {

        int numberOfRows = 0;

        try {
            db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT name FROM Alumno WHERE (" + constants.ID_STUDENT + "= ? AND " + constants.PASSWORD_STUDENT + "=?);", new String[]{carnet,password});
            cursor.moveToNext();
            numberOfRows = cursor.getCount();
            cursor.close();
        } catch (SQLException e) {

        }

        return numberOfRows;


    }

    public int checkNumberOfRows () {
        int numberOfRows = 0;
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Alumno", null);
            cursor.moveToNext();
            numberOfRows = cursor.getCount();
            cursor.close();
        } catch (SQLException e) {
            numberOfRows = 2;
        }
        return numberOfRows;
    }

    public String getNameFromCarnet (String carnet) {
        String name = "";
        try{
            Cursor cursor = db.rawQuery("SELECT name FROM Alumno WHERE id=?", new String[]{carnet});
            cursor.moveToNext();
            name = cursor.getString(0);
        } catch (SQLException ex) {

        }

        return name;
    }

}
