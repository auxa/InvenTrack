package com.example.eoin.swengmenu;

/**
 * Created by Joey on 01-Dec-16.
 */

/**
 * Created by Joey on 16-Nov-16.
 */

public class IndividualFunc {
    int id;
    String name;

    public IndividualFunc(){

    }

    public IndividualFunc(String name){
        this.name=name;
    }
    //setters pls
    public void setInd_id(int ind_id) {
        this.id = ind_id;
    }
    public void setName(String name){
        this.name=name;
    }
    //Getters
    public int getInd_id(){
        return id;
    }
    public String getName(){
        return name;
    }


    @Override
    public String toString(){
        return "ID: " + getInd_id() + "\nName: "+ getName()+ "\n";
    }

}