package com.rdr.rodrigocorvera.personas.Entidades;

/**
 * Created by Rodrigo Corvera on 20/5/2018.
 */

public class Estudiante {

    String name;
    String grade;

    public Estudiante (String name, String grade) {
        this.name = name;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
