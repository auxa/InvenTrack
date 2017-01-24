package com.example.eoin.swengmenu;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Joey on 01-Dec-16.
 */

public class ProjectFunc {
    int id;
    String name;
    long end_date;

    public ProjectFunc(){

    }

    public ProjectFunc(String name, long end_date){
        this.name=name;
        this.end_date=end_date;
    }
    //setters, No bad accessing of varibles please
    public void setProject_id(int id){
        this.id=id;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setEnd_date(long date){
        this.end_date=date;
    }

    public int getProject_id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getEnd_date() {
        return end_date;
    }

    @Override
    public String toString(){
        Date date = new Date(this.getEnd_date());
        return "Project ID: " + this.getProject_id() + "\n Project Name: " + this.getName() + "\n Project End Date: " + DateFormat.getDateInstance().format(date) +"\n \n";
    }
}