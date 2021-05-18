package com.assignment.satya.cartrack.room.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.assignment.satya.cartrack.room.model.UserDatabaseModel;

import java.util.List;


@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    public LiveData<List<UserDatabaseModel>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllUsers(List<UserDatabaseModel> userList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(UserDatabaseModel user);

    @Query("DELETE FROM users")
    public void deleteAllUsers();
}
