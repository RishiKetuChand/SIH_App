package com.rkc.userarchitectsih.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.rkc.userarchitectsih.JsonClasses.BuildingProject;

import java.util.List;

@Dao
public interface DraftProjectsDao {

    @Insert
    void insertDao(BuildingProject buildingProject);

    @Query("SELECT * FROM building_project_table")
    List<BuildingProject> getAllBuildingProjects();

    @Query("SELECT * FROM building_project_table WHERE projectID LIKE :projectID")
    BuildingProject findProjectById(String projectID);

    @Update
    void updateProject(BuildingProject buildingProject);

    @Delete
    void deleteProject(BuildingProject buildingProject);
}
