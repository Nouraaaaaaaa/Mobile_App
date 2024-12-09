package com.example.roomify;

public class NoteModel {
    String title, des;
    int Id;

    public NoteModel(int id, String des, String title) {
        Id = id;
        this.des = des;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return Id;
    }

    public String getDes() {
        return des;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setDes(String des) {
        this.des = des;
    }
}
