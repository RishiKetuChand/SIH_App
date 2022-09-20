package com.rkc.userarchitectsih.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rkc.userarchitectsih.JsonClasses.BuildingProject;

@Database(entities = {BuildingProject.class}, version = 1)
public abstract class DraftProjectRoomDataBase extends RoomDatabase {
    public abstract DraftProjectsDao draftProjectsDao();

    private static volatile DraftProjectRoomDataBase INSTANCE;

    public static DraftProjectRoomDataBase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (DraftProjectRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DraftProjectRoomDataBase.class, "DraftProject_Database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
