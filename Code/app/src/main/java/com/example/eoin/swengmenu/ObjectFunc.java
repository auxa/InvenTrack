package com.example.eoin.swengmenu;

/**
 * Created by Joey on 01-Dec-16.
 */

public class ObjectFunc {
    private String barcode;
    private long individual_id;
    private String name;
    int pro_id = -1;
    boolean damaged;

    public ObjectFunc(){

    }
    public ObjectFunc(String barcode, String name, long individual_id){
        this.barcode=barcode;
        this.name = name;
        this.individual_id = individual_id;
        this.damaged=false;
    }
    public ObjectFunc(String barcode, long individual_id, String name, int pro_id){
        this.barcode= barcode;
        this.individual_id= individual_id;
        this.name=name;
        this.pro_id=pro_id;
        this.damaged=false;
    }
    //getters

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public Integer getPro_id() {
        return pro_id;
    }

    public long getIndividual_id() {
        return individual_id;
    }

    public boolean getDamaged() { return damaged; }
    //setters

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPro_id(Integer pro_id) {
        this.pro_id = pro_id;
    }

    public void setIndividual_id(long individual_id) {
        this.individual_id = individual_id;
    }

    public void setDamaged(boolean damage) { this.damaged=damage; }

    @Override
    public String toString(){
        if(this.getPro_id() == -1) {
            return "\nBarcode: " + this.getBarcode() + " \nName: " + this.getName() + " \nPerson Responsible: " + this.getIndividual_id() + "\nIs Damaged:  " + this.getDamaged() + "\n \n";
        }
        else{
            return "\nBarcode: " + this.getBarcode() + "\n" +
                    "Name: " + this.getName() + "\n" +
                    "Person Responsible: " + this.getIndividual_id() + "\nProject ID: " + this.getPro_id()+ "\n" +
                    "Is Damaged: " + this.getDamaged() + "\n \n";
        }
    }
}