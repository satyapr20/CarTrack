package com.assignment.satya.cartrack.room.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.assignment.satya.cartrack.room.model.RestUserModel;

import java.util.List;

@Dao
public interface RestUsersDao {

    @Query("SELECT * FROM rest_users")
    public LiveData<List<RestUserModel>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertAllUsers(List<RestUserModel> userList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertUser(RestUserModel user);

    @Query("DELETE FROM rest_users")
    public void deleteAllUsers();
}
