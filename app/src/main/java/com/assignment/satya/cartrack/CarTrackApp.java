package com.assignment.satya.cartrack;

import android.app.Application;
import androidx.room.Room;

import com.assignment.satya.cartrack.room.db.CartrackDatabase;


public class CarTrackApp extends Application {

    public static CartrackDatabase database = null;

    @Override
    public void onCreate() {
        super.onCreate();
        database =  Room.databaseBuilder(getApplicationContext(), CartrackDatabase.class, "cartrack_db").fallbackToDestructiveMigration().build();
    }

}