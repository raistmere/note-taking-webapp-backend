package com.raistmere.notetakingwebapp.model;

public class NoteModel {

    private int id;

    private int userId;
    private String note;

    public NoteModel() {}

    public NoteModel(int id, int userId, String note) {
        this.id = id;
        this.userId = userId;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", userId=" + userId +
                ", note='" + note + '\'' +
                '}';
    }
}