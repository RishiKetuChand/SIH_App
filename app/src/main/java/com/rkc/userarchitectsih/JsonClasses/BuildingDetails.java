package com.rkc.userarchitectsih.JsonClasses;

import android.content.Context;

import androidx.room.Ignore;

import com.rkc.userarchitectsih.R;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class BuildingDetails {
    private String buildingCategory;
    private String projectType;
    private String zone;
    private String classification;
    private String projectComponents;
    private String projectCategory;
    private String startDate;
    private String endDate;
    private String dwellingUnits;
    private String basementFloor;
    private String groundFloor;
    private String upperFloor;
    private String stilt;
    private String buildingHeight;
    @Ignore
    private Context context;

    public BuildingDetails(String buildingCategory, String projectType, String zone, String classification, String projectComponents, String projectCategory, String startDate, String endDate, String dwellingUnits, String basementFloor, String groundFloor, String upperFloor, String stilt, String buildingHeight) {
        this.buildingCategory = buildingCategory;
        this.projectType = projectType;
        this.zone = zone;
        this.classification = classification;
        this.projectComponents = projectComponents;
        this.projectCategory = projectCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dwellingUnits = dwellingUnits;
        this.basementFloor = basementFloor;
        this.groundFloor = groundFloor;
        this.upperFloor = upperFloor;
        this.stilt = stilt;
        this.buildingHeight = buildingHeight;
    }

    public BuildingDetails(Context context, String buildingCategory, String projectType, String zone, String classification,
                           String projectComponents, String projectCategory, String startDate, String endDate,
                           String dwellingUnits, String basementFloor, String groundFloor, String upperFloor,
                           String stilt, String buildingHeight) {
        this.context= context;
        try {
            this.buildingCategory = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),buildingCategory);
            this.projectType = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),projectType);
            this.zone = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),zone);
            this.classification = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),classification);
            this.projectComponents = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),projectComponents);
            this.projectCategory = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),projectCategory);
            this.startDate = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),startDate);
            this.endDate = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),endDate);
            this.dwellingUnits = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),dwellingUnits);
            this.basementFloor = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),basementFloor);
            this.groundFloor = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),groundFloor);
            this.upperFloor = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),upperFloor);
            this.stilt = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),stilt);
            this.buildingHeight = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),buildingHeight);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public Context getContext() {
        return context;
    }

    public String getBuildingCategory() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),buildingCategory);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return buildingCategory;
        }
    }


    public String getProjectType() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),projectType);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return projectType;
        }
    }


    public String getZone() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),zone);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return zone;
        }
    }

    public String getClassification() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),classification);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return classification;
        }
    }
    public String getProjectComponents() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),projectComponents);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return projectComponents;
        }
    }

    public String getProjectCategory() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),projectCategory);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return projectCategory;
        }
    }

    public String getStartDate() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),startDate);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return startDate;
        }
    }

    public String getEndDate() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),endDate);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return endDate;
        }
    }

    public String getDwellingUnits() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),dwellingUnits);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();return dwellingUnits;
        }
    }

    public String getBasementFloor() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),basementFloor);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return basementFloor;
        }
    }
    public String getGroundFloor() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),groundFloor);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return groundFloor;
        }
    }

    public String getUpperFloor() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),upperFloor);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return upperFloor;
        }
    }

    public String getStilt() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),stilt);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return stilt;
        }
    }

    public String getBuildingHeight() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),buildingHeight);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return buildingHeight;
        }
    }


}
