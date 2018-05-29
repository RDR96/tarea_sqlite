package com.rdr.rodrigocorvera.personas.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.rdr.rodrigocorvera.personas.Entidades.Category;
import com.rdr.rodrigocorvera.personas.Entidades.Estudiante;
import com.rdr.rodrigocorvera.personas.Entidades.Note;
import com.rdr.rodrigocorvera.personas.R;

import java.util.ArrayList;

/**
 * Created by Rodrigo Corvera on 19/5/2018.
 */

public class DbHelper extends SQLiteOpenHelper {

    public static DbHelper myDb = null;
    Context context;
    SQLiteDatabase db;

    public DbHelper (Context context) {

        super(context,constants.DB_NAME,null,1);
        context.deleteDatabase(constants.DB_NAME);
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
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT name FROM Alumno WHERE id=?", new String[]{carnet});
            cursor.moveToNext();
            name = cursor.getString(0);
        } catch (SQLException ex) {

        }

        return name;
    }

    public int checkCategoryIfExist () {
        int resultNumber = 0;
        try{
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Categoria WHERE alumno_id=?", new String[]{checkUserLog()});
            cursor.moveToNext();
            resultNumber = cursor.getCount();
            cursor.close();
        } catch (SQLException ex) {

        }

        return resultNumber;

    }

    public int checkCategoryName (String categoryName, boolean checkName) {
        int resultNumber = 0;
        try{
            if ( checkName ){
                Cursor cursor = db.rawQuery("SELECT * FROM Categoria WHERE alumno_id=? AND name=?", new String[]{checkUserLog(), categoryName.toLowerCase()});
                cursor.moveToNext();
                resultNumber = cursor.getCount();
                cursor.close();
            } else {
                Cursor cursor = db.rawQuery("SELECT * FROM Categoria WHERE alumno_id=?", new String[]{checkUserLog()});
                cursor.moveToNext();
                resultNumber = cursor.getCount();
                cursor.close();
            }


        } catch (SQLException ex) {

        }

        return resultNumber;

    }

    public ArrayList<String> getCategoriesById() {

        ArrayList<String> categories = null;

        try{
            Cursor cursor = db.rawQuery("SELECT name FROM Categoria WHERE alumno_id=?", new String[]{checkUserLog()});
            categories = new ArrayList<String>();
            while( cursor.moveToNext() ){
                categories.add(cursor.getString(cursor.getColumnIndex("name")));

            }

            cursor.close();
        }catch (SQLException sqlE) {

        }
        return categories;
    }

    public String checkUserLog () {
        String id = "";
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Alumno WHERE isLog=?", new String[]{"1"});
            if ( cursor != null  && cursor.moveToFirst()) {
                id = cursor.getString(cursor.getColumnIndex(constants.ID_STUDENT));
                cursor.close();
            }

        } catch (SQLException ex) {

        }
        return id;
    }

    public void setActualUser(String carnet) {
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(constants.ISLOG_STUDENT, 1);
            db.update(constants.TB_NAME_ALUMNO, contentValues, constants.ID_STUDENT +"=?", new String[]{carnet});

        } catch (SQLException sqlE) {

        }
    }

    public void addNote (String categoryName, String noteContent) {
        try{

            ContentValues values = new ContentValues();
            values.put(constants.CATEGORY_ID_STUDENT, checkUserLog());
            values.put(constants.NOTE_VALUE, noteContent);
            values.put(constants.NOTA_CATEGORY_ID, getIdFromCategory(categoryName.toLowerCase()));
            db.insert(constants.TB_NAME_NOTA, null, values);



        } catch (SQLException sqlException) {

        }
    }

    public void addCategory (String categoryName) {

        try{
            ContentValues values = new ContentValues();
            values.put(constants.CATEGORY_ID_STUDENT, checkUserLog());
            values.put(constants.NAME_CATEGORY, categoryName.toLowerCase());
            db.insert(constants.TB_NAME_CATEGORIA, null, values);
        } catch (SQLException sqlException) {

        }

    }

    public String getIdFromCategory (String categoryName) {
        String categoryId = "";
        try {
            Cursor cursor = db.rawQuery("SELECT * FROM Categoria WHERE name=? AND alumno_id=?", new String[]{categoryName, checkUserLog()});
            cursor.moveToNext();
            categoryId = cursor.getString(cursor.getColumnIndex(constants.ID_STUDENT));
            cursor.close();

        }catch (SQLException sqlException) {

        }
        return categoryId;
    }

    public void closeSession () {
        try{
            db = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(constants.ISLOG_STUDENT, 0);
            db.update(constants.TB_NAME_ALUMNO, contentValues, constants.ID_STUDENT +"=?", new String[]{checkUserLog()});

        }catch (SQLException sqlException) {

        }
    }

    public ArrayList<Note> retrieveNotes () {
        ArrayList<Note> arrayNotes = new ArrayList<Note>();

        try{
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT cat.name, note.nota FROM Categoria AS cat INNER JOIN Nota AS note" +
                    " ON cat.id = note.categoria_id WHERE note.alumno_id=?", new String[]{checkUserLog()});

            while (cursor.moveToNext()) {
                arrayNotes.add(new Note(cursor.getString(0),cursor.getString(1)));
            }

        }catch (SQLException sqlException) {

        }

        return arrayNotes;
    }


    //MODIFICAR!!!

    public ArrayList<Category> retrieveCategoriesInfo () {
        ArrayList<Category> arrayCategories = new ArrayList<Category>();

        try{
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Categoria WHERE alumno_id=?", new String[]{checkUserLog()});

            while (cursor.moveToNext()) {
                arrayCategories.add(new Category(cursor.getString(cursor.getColumnIndex("name")), cursor.getString(cursor.getColumnIndex("id"))));
            }

        }catch (SQLException sqlException) {

        }

        return arrayCategories;
    }

    public ArrayList<Note> getNotesByCategory(String categoryId) {
        ArrayList<Note> arrayCategories = new ArrayList<Note>();

        try{
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Nota WHERE alumno_id=? AND categoria_id=?", new String[]{checkUserLog(), categoryId});

            while (cursor.moveToNext()) {
                arrayCategories.add(new Note(cursor.getString(cursor.getColumnIndex("id")), cursor.getString(cursor.getColumnIndex("nota"))));
            }

        }catch (SQLException sqlException) {

        }

        return arrayCategories;

    }




}
