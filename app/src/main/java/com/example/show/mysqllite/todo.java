package com.example.show.mysqllite;

import android.content.ClipData;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by show on 8/6/17.
 */

public class todo {

    int id;
    String task = "task";
    String date = "";
    String location = "";
    String detail = "";
    int priority = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}

