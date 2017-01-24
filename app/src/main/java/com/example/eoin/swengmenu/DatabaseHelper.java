package com.example.eoin.swengmenu;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.style.TtsSpan;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Joey on 16-Nov-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String LOG = "DB Helper";
    private static final int VERSION = 1;
    private static final String DB_NAME = "InventTracking";
    private static final String TABLE_PROJECT = "Projects";
    private static final String TABLE_INDIV = "Individuals";
    private static final String TABLE_OBJECT = "Object";
    private static final String TABLE_WORKING_ON = "Working_On";

    //KEY of the tables
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_BARCODE = "barcode";


    //for the projects
    private static final String KEY_END_DATE = "end_date";

    //for working on table and obect table
    private static final String KEY_IND = "ind_id";
    private static final String KEY_PRO = "project_id";
    private static final String KEY_DAMAGED = "damaged"; //stored as an integer, 1 if it is and 0 if it isn't



    private static final String CREATE_TABLE_PRO = "CREATE TABLE "
            + TABLE_PROJECT + " ( " + KEY_PRO + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME
            + " TEXT," + KEY_END_DATE + " INTEGER" + ")";

    private static final String CREATE_TABLE_IND = "CREATE TABLE "
            + TABLE_INDIV + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME
            + " TEXT "+ ")";

    private static final String CREATE_TABLE_WORKING_ON= "CREATE TABLE "
            + TABLE_WORKING_ON +"( " +
            KEY_IND + " INTEGER NOT NULL, " +
            KEY_PRO + " INTEGER NOT NULL," +
            " FOREIGN KEY ( " + KEY_IND + " ) REFERENCES " + TABLE_INDIV + " ( "+ KEY_ID + " ), FOREIGN KEY ( "
            + KEY_PRO + " ) REFERENCES " + TABLE_PROJECT + " ( " + KEY_PRO + "),"
            + " PRIMARY KEY ( " + KEY_IND + "" +", "+ KEY_PRO + " ) )";

    private static final String CREATE_OBJECT_TABLE = "CREATE TABLE " + TABLE_OBJECT + " ( " + KEY_BARCODE
            + " TEXT PRIMARY KEY NOT NULL, "  + KEY_NAME + " TEXT NOT NULL, "+ KEY_IND + " INTEGER NOT NULL, "
            + KEY_DAMAGED + " INTEGER NOT NULL, "
            + KEY_PRO + " INTEGER, "
            + "FOREIGN KEY  ( "+KEY_IND+" ) REFERENCES " + TABLE_INDIV + " ("
            + KEY_ID + " ) , " + " FOREIGN KEY ( " + KEY_PRO + " ) REFERENCES " + TABLE_PROJECT
            + " ( " + KEY_PRO +" ))";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE_IND);
        db.execSQL(CREATE_TABLE_PRO);
        db.execSQL(CREATE_OBJECT_TABLE);
        db.execSQL(CREATE_TABLE_WORKING_ON);
    }
    /*
    V dangerous method -> Only use if you know what you are doing
     */
    public void onUpgrade (SQLiteDatabase db, int temp, int temp1){
        db.execSQL("DROP TABLE " + TABLE_PROJECT);
        db.execSQL("DROP TABLE " + TABLE_OBJECT);
        db.execSQL("DROP TABLE " + TABLE_WORKING_ON);
        db.execSQL("DROP TABLE " + TABLE_INDIV);
    }


    /*
    creating the first project table class
     */

    public long createProject(ProjectFunc project){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, project.getName());
            values.put(KEY_END_DATE, project.getEnd_date());
            //insert Row
            long projectID = db.insert(TABLE_PROJECT, null, values);
            return projectID;
        }catch (SQLiteException temp){
            return 0;
        }
    }

    //Returns the project as an ProjectFunc
    public ProjectFunc getProject(String p_id){
        ProjectFunc project= new ProjectFunc();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_PROJECT + " WHERE "
                    + KEY_PRO + " = " + p_id;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!= null){
                c.moveToFirst();
            }
            project.setProject_id(c.getInt(0));
            project.setEnd_date(c.getLong(2));
            project.setName(c.getString(1));

            int temp = c.getInt(0);
            return project;

        } catch (Exception e) {
            e.printStackTrace();
            return project;
        }
    }

    public List<ProjectFunc> getProjectsFromPerson(int person_id){
        List<ProjectFunc> project= new ArrayList<ProjectFunc>();
        try{
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM " + TABLE_PROJECT + " JOIN " + TABLE_WORKING_ON
            + " ON " + TABLE_WORKING_ON + "." + KEY_PRO + " = " + TABLE_PROJECT + "." + KEY_PRO + " WHERE "
                    + TABLE_WORKING_ON + "." + KEY_IND + " = " + person_id;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!= null){
                c.moveToFirst();
                do{
                    ProjectFunc projects= new ProjectFunc();
                    projects.setProject_id(c.getInt(0));
                    projects.setEnd_date(c.getLong(2));
                    projects.setName(c.getString(1));
                    project.add(projects);
                } while(c.moveToNext());
            }

            return project;
        }catch (Exception e) {
            e.printStackTrace();
            return project;
        }
    }

    public IndividualFunc getIndividual(int p_id){
        IndividualFunc indiv= new IndividualFunc();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_INDIV + " WHERE "
                    + KEY_ID + " = " + p_id;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!= null){
                c.moveToFirst();
            }
            indiv.setInd_id(c.getInt(0));
            indiv.setName(c.getString(1));
            return indiv;
        } catch (Exception e) {
            e.printStackTrace();
            return indiv;
        }
    }
    public List<ObjectFunc> getObject(long p_id){
        List<ObjectFunc> objects= new ArrayList<ObjectFunc>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_OBJECT + " WHERE "
                    + KEY_IND + " = " + p_id;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setDamaged((c.getInt(3)==1));
                    object.setPro_id(c.getInt(4));
                    objects.add(object);
                } while(c.moveToNext());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }
    }
    /*
    if you are using this method make the retun element equal to the id of the individual
    you need to do long holder = (createIndividual(individual)); individual.setID(holder));
     */
    public long createIndiviual(IndividualFunc individual){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_NAME, individual.getName());

            //insert Row
            long indivID = db.insert(TABLE_INDIV, null, values);
            return indivID;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long assignWork(int individual, int project){
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            IndividualFunc temp = this.getIndividual(individual);
            if(temp != null){
                ContentValues values = new ContentValues();
                values.put(KEY_IND, individual);
                values.put(KEY_PRO, project);
                long assign = db.insert(TABLE_WORKING_ON, null, values);
                return assign;
            }
            else{
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public long addObject(String barcode, int individual_id, String name, int project){
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            ContentValues values = new ContentValues();
            values.put(KEY_BARCODE, barcode);
            values.put(KEY_IND, individual_id);
            values.put(KEY_NAME, name);
            values.put(KEY_DAMAGED, 0);
            if(project== -1){ values.put(KEY_PRO, (String) null);}
            else { values.put(KEY_PRO, project); }

            long assign = db.insert(TABLE_OBJECT, null, values);
            return assign;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
    public boolean damageObject(String object_barcode){
        try {

            SQLiteDatabase db = this.getReadableDatabase();

            //if(searchBarcode(object_barcode)!=null) {

                ContentValues newValues = new ContentValues();
                newValues.put(KEY_DAMAGED, 1);
<<<<<<< HEAD
                String damageUpdate = KEY_BARCODE + " = " + object_barcode;
                Toast.makeText((DatabaseHelper.this, "Project ID: " + projectId, Toast.LENGTH_LONG).show();)


=======
              // String damageUpdate123 ="UPDATE " + TABLE_OBJECT + " SET "+ KEY_DAMAGED + " = " + 1 + " WHERE " +  KEY_BARCODE + " = " + object_barcode;
               // db.execSQL(damageUpdate123, null);
            String var = KEY_BARCODE+" = "+object_barcode;
               db.update(TABLE_OBJECT, newValues, var, null);
              //  db.execSQL(damageUpdate123);
>>>>>>> 73841e9c567d84772c20333f550f9c5dffd31c18
                return true;
           /* }
            else{
                return false;
<<<<<<< HEAD

            }
=======
            }*/
>>>>>>> 73841e9c567d84772c20333f550f9c5dffd31c18
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    /*
    method to retun all of the projects in the db as of a user defined end date
     */
    public List<ProjectFunc> searchByEndDate(long end_date){
        List<ProjectFunc> temp = new ArrayList<ProjectFunc>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT  * FROM " + TABLE_PROJECT + " WHERE "
                    + KEY_END_DATE + " = " + end_date;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!= null){
                c.moveToFirst();
                do{
                    ProjectFunc project= new ProjectFunc();
                    project.setProject_id(c.getInt(c.getColumnIndex(KEY_ID)));
                    project.setEnd_date(c.getLong(c.getColumnIndex(KEY_END_DATE)));
                    project.setName(c.getString(c.getColumnIndex(KEY_NAME)));

                    temp.add(project);
                } while(c.moveToFirst());
            }

            return temp;
        } catch (Exception e) {
            e.printStackTrace();
            return temp;
        }
    }

    public ObjectFunc searchBarcode(String bar_num) {
        ObjectFunc objects = new ObjectFunc();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM " + TABLE_OBJECT + " WHERE " + TABLE_OBJECT + "." + KEY_BARCODE + " = " + bar_num;

            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);


            if (c != null) {
                c.moveToFirst();
                ObjectFunc object = new ObjectFunc();
                object.setName(c.getString(1));
                object.setBarcode(c.getString(0));
                object.setIndividual_id(c.getInt(2));
                object.setDamaged((c.getInt(3)==1));
                object.setPro_id(c.getInt(4));
                return object;

            }

        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }
        return objects;
    }

    public List<ObjectFunc> getObjectsDue(long end_date){
        List<ObjectFunc> objects = new ArrayList<ObjectFunc>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM " + TABLE_OBJECT + " JOIN " + TABLE_PROJECT +
                    " ON " + TABLE_OBJECT + "." + KEY_PRO + " = " + TABLE_PROJECT + "." + KEY_PRO +
                    " WHERE " + TABLE_PROJECT +"."+ KEY_END_DATE + " = " + end_date + " AND " +
                    KEY_DAMAGED + " = " + 0;


            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);


            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setDamaged((c.getInt(3)==1));
                    object.setPro_id(c.getInt(4));
                    objects.add(object);
                } while(c.moveToNext());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }

    }
    public List<ObjectFunc> getDamaged(int date){
        List<ObjectFunc> objects = new ArrayList<ObjectFunc>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM "+ TABLE_OBJECT + " JOIN " + TABLE_PROJECT +" ON "+ TABLE_OBJECT + "."
                    +  KEY_PRO + " = " + TABLE_PROJECT + "." + KEY_PRO +" WHERE "
                    + KEY_DAMAGED + " = " + "0 AND " + date + " = " + TABLE_PROJECT + "." + KEY_END_DATE;

            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setDamaged((c.getInt(3)==1));
                    object.setPro_id(c.getInt(4));
                    objects.add(object);
                } while(c.moveToFirst());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }
    }

    public List<ObjectFunc> getDamaged(){
        List<ObjectFunc> objects = new ArrayList<ObjectFunc>();

        try {
            SQLiteDatabase db = this.getReadableDatabase();

            String selectQuery = "SELECT * FROM "+ TABLE_OBJECT + " WHERE " + KEY_DAMAGED + " = " + 1;

            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setPro_id(c.getInt(4));
                    object.setDamaged(c.getInt(3)==1);
                    objects.add(object);
                } while(c.moveToNext());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }
    }

    public List<ObjectFunc> searchPersonAndDate(int person_id, long time){
        List<ObjectFunc> objects = new ArrayList<ObjectFunc>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_OBJECT + " LEFT JOIN " + TABLE_INDIV + " ON " + TABLE_OBJECT + "." +  KEY_IND + " = " + TABLE_INDIV + "." + KEY_ID
                + " LEFT JOIN " + TABLE_PROJECT + " ON " + TABLE_OBJECT + "." + KEY_PRO + " = " + TABLE_PROJECT +"." + KEY_PRO
                +" WHERE " + TABLE_PROJECT + "." + KEY_END_DATE + " = " + time
                + " AND " + TABLE_INDIV + "." + KEY_ID + " = " +  person_id
                + " AND " + TABLE_OBJECT + "." + KEY_DAMAGED + " = " + 0;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setDamaged((c.getInt(3)==1));
                    object.setPro_id(c.getInt(4));
                    objects.add(object);
                } while(c.moveToNext());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }

    }
    public List<ObjectFunc> searchProjectAndDate(int project_id, long time){
        List<ObjectFunc> objects = new ArrayList<ObjectFunc>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            String selectQuery = "SELECT * FROM " + TABLE_OBJECT + " LEFT JOIN " + TABLE_PROJECT + " ON " + TABLE_OBJECT + "." + KEY_PRO + " = " + TABLE_PROJECT +"." + KEY_PRO
                    +" WHERE " + TABLE_PROJECT + "." + KEY_END_DATE + " = " + time
                    + " AND " + TABLE_PROJECT + "." + KEY_PRO + " = " + project_id
                    + " AND " + TABLE_OBJECT + "." + KEY_DAMAGED + " = " + 0;
            Log.e(LOG, selectQuery);

            Cursor c = db.rawQuery(selectQuery, null);
            if(c!=null){
                c.moveToFirst();
                do{
                    ObjectFunc object = new ObjectFunc();
                    object.setName(c.getString(1));
                    object.setBarcode(c.getString(0));
                    object.setIndividual_id(c.getInt(2));
                    object.setDamaged((c.getInt(3)==1));
                    object.setPro_id(c.getInt(4));
                    objects.add(object);
                } while(c.moveToNext());
            }
            return objects;
        } catch (Exception e) {
            e.printStackTrace();
            return objects;
        }

    }
}