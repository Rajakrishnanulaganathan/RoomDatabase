package com.imdb.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class},version = 1)
public abstract class AppDataBase extends RoomDatabase {

    public static AppDataBase mAppDatabaseInstance;

    public static synchronized AppDataBase  getDatabaseInstance(Context context){
        if(mAppDatabaseInstance==null){
            mAppDatabaseInstance= Room.databaseBuilder(context,AppDataBase.class,"user.db").build();
        }
        return mAppDatabaseInstance;
    }

    public void destroy(){
        mAppDatabaseInstance=null;
    }

    public abstract UserDao userDao();
}
