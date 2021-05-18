package com.assignment.satya.cartrack.room.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.assignment.satya.cartrack.room.model.RestUserModel;
import com.assignment.satya.cartrack.room.model.UserDatabaseModel;

@Database(entities = {UserDatabaseModel.class, RestUserModel.class}, version = 2)
public abstract class CartrackDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract RestUsersDao restUserDao();
}
