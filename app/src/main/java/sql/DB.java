package sql;

/**
 * Created by shahria on 12-01-2016.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "timetable.db";
    public static final String CONTACTS_TABLE_NAME = "timetable";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String CONTACTS_COLUMN_NAME = "name";
    public static final String CONTACTS_COLUMN_EMAIL = "email";
    public static final String CONTACTS_COLUMN_STREET = "street";
    public static final String CONTACTS_COLUMN_CITY = "place";
    public static final String CONTACTS_COLUMN_PHONE = "phone";
    private HashMap hp;

    public DB(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table timetable " +
                        "(id integer primary key, name text)"



        );

        db.execSQL(
                "create table lessons " +
                        "(id integer primary key, tid integer,subject text,lesson tFext,starttime text,endtime text,color text,room text,teacher text,day text)"


        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertTimetable(int id, String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("id", id);

        db.insert("timetable", null, contentValues);
        return true;
    }


    public  void exportDB(){
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();
        FileChannel source=null;

        FileChannel destination=null;
        String currentDBPath = "/data/"+"com.shahria.MaterialTimetable"+"/databases/"+DATABASE_NAME;
        String backupDBPath = DATABASE_NAME;
        File currentDB = new File(data, currentDBPath);
        File backupDB = new File(sd, backupDBPath);
        try {
            source = new FileInputStream(currentDB).getChannel();
            destination = new FileOutputStream(backupDB).getChannel();
            destination.transferFrom(source, 0, source.size());
            source.close();
            destination.close();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }








    public long insertLesson  (int id,String subject,String lesson,String starttime,String endtime,String color,String room,String teacher,String day)
    {

        long insert_id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject",subject);
        contentValues.put("tid", id);
        contentValues.put("lesson",lesson);
        contentValues.put("starttime",starttime);
        contentValues.put("endtime",endtime);
        contentValues.put("color",color);
        contentValues.put("room",room);
        contentValues.put("teacher",teacher);
        contentValues.put("day",day);
        insert_id=db.insert("lessons", null, contentValues);
        return insert_id;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from contacts where id="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String name, String phone, String email, String street,String place)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("phone", phone);
        contentValues.put("email", email);
        contentValues.put("street", street);
        contentValues.put("place", place);
        db.update("contacts", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public boolean updateLesson ( int id,int tid,String subject,String lesson,String starttime,String endtime,String color,String room,String teacher,String day)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("subject",subject);
        contentValues.put("tid", tid);
        contentValues.put("lesson",lesson);
        contentValues.put("starttime",starttime);
        contentValues.put("endtime",endtime);
        contentValues.put("color",color);
        contentValues.put("room",room);
        contentValues.put("teacher",teacher);
        contentValues.put("day",day);
        db.update("lessons", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }


    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("lessons",
                "id = ? ",
                new String[] { Integer.toString(id) });
    }

    public ArrayList<String> getAllContacts()
    {
        ArrayList<String> array_list = new ArrayList<String>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from timetable", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
            res.moveToNext();
        }
        return array_list;
    }



    public ArrayList<get_set> getLessons(String day,int timetable)
    {
        ArrayList<get_set> array_list = new ArrayList<get_set>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from lessons where day='"+day+"' and tid='"+timetable+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new get_set(res.getInt(0),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)));
            res.moveToNext();
        }
        return array_list;
    }


    public Boolean  getLessons(String day,int timetable,String starttime,String endtime)
    {
        ArrayList<get_set> array_list = new ArrayList<get_set>();

        Boolean result=false;
        //hp = new HashMap();

        Log.e("k", "select * from lessons where day='" + day + "' and tid='" + timetable + "' and starttime BETWEEN '" + starttime + "'   and  '" + endtime + "'");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from lessons where day='"+day+"' and tid='"+timetable+"' and starttime BETWEEN '"+starttime+"'   and  '"+endtime+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            result=true;
            res.moveToNext();
        }
        return result;
    }


    public Boolean  getLessons(String day,int timetable,String starttime,String endtime,int id)
    {
        ArrayList<get_set> array_list = new ArrayList<get_set>();

        Boolean result=false;
        //hp = new HashMap();

        Log.e("k", "select * from lessons where id !="+id+" and day='" + day + "' and tid='" + timetable + "' and starttime BETWEEN '" + starttime + "'   and  '" + endtime + "'");
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from lessons where id !='"+id+"' and day='"+day+"' and tid='"+timetable+"' and starttime BETWEEN '"+starttime+"'   and  '"+endtime+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            result=true;
            res.moveToNext();
        }
        return result;
    }







    public ArrayList<get_set> getSubject(String day,int timetable)
    {
        ArrayList<get_set> array_list = new ArrayList<get_set>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from lessons where subject='"+day+"' and tid='"+timetable+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){
            array_list.add(new get_set(res.getInt(0),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)));
            res.moveToNext();
        }
        return array_list;
    }




    public ArrayList<get_set> getAllLessons(int timetable)
    {
        ArrayList<get_set> array_list = new ArrayList<get_set>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from lessons where tid=' "+timetable+"'", null );
        res.moveToFirst();

        while(res.isAfterLast() == false){

            Log.e("id",res.getInt(0)+"");
            array_list.add(new get_set(res.getInt(0),res.getString(2),res.getString(3),res.getString(4),res.getString(5),res.getString(6),res.getString(7),res.getString(8),res.getString(9)));
            res.moveToNext();
        }
        return array_list;
    }


}
