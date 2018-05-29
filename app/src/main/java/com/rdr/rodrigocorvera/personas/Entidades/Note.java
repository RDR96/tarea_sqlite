package com.rdr.rodrigocorvera.personas.Entidades;

/**
 * Created by Rodrigo Corvera on 27/5/2018.
 */

public class Note {

    private String categoryName;
    private String noteContent;

    public Note (String categoryName, String noteContent) {
        this.categoryName = categoryName;
        this.noteContent = noteContent;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
