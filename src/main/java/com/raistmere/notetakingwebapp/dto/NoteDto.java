package com.raistmere.notetakingwebapp.dto;

import java.util.Objects;

public class NoteDto {

    private int id;

    private Long userId;
    private String title;
    private String note;

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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        NoteDto noteDto = (NoteDto) o;
        return id == noteDto.id && Objects.equals(userId, noteDto.userId) && Objects.equals(title, noteDto.title) && Objects.equals(note, noteDto.note);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + Objects.hashCode(userId);
        result = 31 * result + Objects.hashCode(title);
        result = 31 * result + Objects.hashCode(note);
        return result;
    }

    @Override
    public String toString() {
        return "NoteDto{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}
