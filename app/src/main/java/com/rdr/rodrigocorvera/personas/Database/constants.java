package com.rdr.rodrigocorvera.personas.Database;

/**
 * Created by Rodrigo Corvera on 19/5/2018.
 */

public class constants {


    //Columnas

    public static final String ID_STUDENT = "id";
    public static final String NAME_STUDENT = "name";
    public static final String PASSWORD_STUDENT = "password";

    public static final String ID_CATEGORY = "id";
    public static final String NAME_CATEGORY = "name";
    //public static final String NAME_CATEGORY = "name";

    public static final String NOTA_STUDENT_ID = "alumno_id";
    public static final String NOTA_CATEGORY_ID = "categoria_id";
    public static final String ID_NOTE = "id";
    public static final String NOTE_VALUE = "nota";



    // Propiedades base de datos

    public static  final String DB_NAME = "Control_Notas";
    public static  final String TB_NAME_ALUMNO = "Alumno";
    public static  final String TB_NAME_CATEGORIA = "Categoria";
    public static  final String TB_NAME_NOTA = "Nota";


    public static  final int DB_VERSION = 1;

    // Creacion de tablas

    public static final String CREATE_TABLE_ALUMNO = "CREATE TABLE Alumno(id INTEGER PRIMARY KEY,"
            + " name TEXT NOT NULL, password TEXT NOT NULL);";

    public static final String CREATE_TABLE_CATEGORIA = "CREATE TABLE Categoria(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " name TEXT NOT NULL);";

    public static final String CREATE_TABLE_NOTA = "CREATE TABLE Nota(id INTEGER PRIMARY KEY AUTOINCREMENT,"
            + " nota number NOT NULL, alumno_id INTEGER NOT NULL, categoria_id INTEGER NOT NULL, FOREIGN KEY (alumno_id) REFERENCES Alumno (id),"
            + " FOREIGN KEY(categoria_id) REFERENCES Categoria(id));";





    // Eliminar una tabla;

    public static final String DROP_TABLE_STUDENT = "DROP TABLE IF EXIST" + TB_NAME_ALUMNO;
    public static final String DROP_TABLE_CATEGORY = "DROP TABLE IF EXIST" + TB_NAME_CATEGORIA;
    public static final String DROP_TABLE_GRADE = "DROP TABLE IF EXIST" + TB_NAME_NOTA;

}
