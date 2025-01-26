package com.raistmere.notetakingwebapp.model;

public class NoteModel {

    private int id;

    private Long userId;
    private String title;
    private String note;

    public NoteModel() {}

    public NoteModel(int id, Long userId, String note) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
