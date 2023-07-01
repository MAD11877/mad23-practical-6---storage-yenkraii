package sg.edu.np.mad.madpractical;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    public String title = "DATABASE";

    public static String DATABASE_NAME = "accountDB.db";
    public static int DATABASE_VERSION = 1;
    public static String ACCOUNTS = "user";
    public static String COLUMN_UN = "name";
    public static String COLUMN_DE = "description";
    public static String COLUMN_ID = "id";
    public static String COLUMN_FL = "followed";



    public DBHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i(title,"DB constructed");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_COMMAND = "CREATE TABLE " + ACCOUNTS +" ( " +
                COLUMN_UN + " TEXT, " +
                COLUMN_DE + " TEXT, " +
                COLUMN_ID + "INT INCREMENT,"+
                COLUMN_FL + "BOOLEAN"
                +");"
                ;

        Log.i(title, CREATE_TABLE_COMMAND);

        db.execSQL(CREATE_TABLE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int NewVersion){
        // upgrading it whenever Android needs it to
        db.execSQL("DROP TABLE IF EXISTS " + ACCOUNTS);
        Log.i(title, "Create new DB");
        onCreate(db);
    }

    // CRUD

    public void addUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO " + ACCOUNTS +" VALUES( \"" + u.name + "\", \"" + u.description + "\", + \"" + u.id + "\", + \"" + u.followed + "\")");
        db.close();
    }

    public ArrayList<User> getUsers()
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<User> list = new ArrayList<User>();

        Cursor cursor = db.rawQuery("SELECT * FROM "  + ACCOUNTS, null);
        while(cursor.moveToNext())
        {
            User newUser = new User();
            newUser.name = cursor.getString(0);
            newUser.description = cursor.getString(1);
            newUser.id = cursor.getInt(2);
            newUser.followed = Boolean.parseBoolean(cursor.getString(3));
            list.add(newUser);
        }

        return list;
    }

    public void updateUser(User u)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + ACCOUNTS +" SET " + COLUMN_FL +" = \""+ u.followed +"\" " +  " WHERE " + COLUMN_ID +" = \""+ u.id +"\"");
        db.close();
    }

    public int Count()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + ACCOUNTS, null);
        return cursor.getCount();       //Returns no. of rows
    }
}

