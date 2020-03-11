package com.imdb.roomdatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Ignore;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;
@Dao
public interface UserDao {

    @Query("Select * from User")
    LiveData<List<User>> getListofUserContacts();

    @Query("Select * from User where mUserPhone = :phone")
    LiveData<User> getContactByPhone(String phone);

    @Insert(onConflict = IGNORE)
    void addcontact(User user);

    @Delete
    void deleteContact(User user);

    @Update
    void updateContact(User user);
}
