package com.rkc.userarchitectsih.JsonClasses;

import android.content.Context;

import androidx.room.Ignore;

import com.rkc.userarchitectsih.R;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class LandDetails {

    private String district;
    private String typeOfLand;
    private String taluk;
    private String nameOfVillage;
    private String khataNumber;
    private String areaOfPlot;
    private String areaAsPerDrawing;
    private String surveyNumber;
    private String propertyID;
    @Ignore
    private Context context;

    public LandDetails(Context context, String district, String typeOfLand, String taluk,
                       String nameOfVillage, String khataNumber, String areaOfPlot,
                       String areaAsPerDrawing, String surveyNumber, String propertyID) {
        this.context = context;
        try {
            this.district = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),district);
            this.typeOfLand = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),typeOfLand);
            this.taluk = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),taluk);
            this.nameOfVillage = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),nameOfVillage);
            this.khataNumber = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),khataNumber);
            this.areaOfPlot = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),areaOfPlot);
            this.areaAsPerDrawing = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), areaAsPerDrawing);
            this.surveyNumber = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),surveyNumber);
            this.propertyID = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),propertyID);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public LandDetails(String district, String typeOfLand, String taluk, String nameOfVillage, String khataNumber, String areaOfPlot, String areaAsPerDrawing, String surveyNumber, String propertyID) {
        this.district = district;
        this.typeOfLand = typeOfLand;
        this.taluk = taluk;
        this.nameOfVillage = nameOfVillage;
        this.khataNumber = khataNumber;
        this.areaOfPlot = areaOfPlot;
        this.areaAsPerDrawing = areaAsPerDrawing;
        this.surveyNumber = surveyNumber;
        this.propertyID = propertyID;
    }

    public String getDistrict() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),district);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return district;
        }
    }

    public String getTypeOfLand() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),typeOfLand);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return typeOfLand;
        }
    }

    public String getTaluk() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),taluk);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return taluk;
        }
    }


    public String getNameOfVillage() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),nameOfVillage);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return nameOfVillage;
        }
    }

    public String getKhataNumber() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),khataNumber);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return khataNumber;
        }
    }

    public String getAreaOfPlot() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),areaOfPlot);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return areaOfPlot;
        }
    }

    public String getAreaAsPerDrawing() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), areaAsPerDrawing);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return areaAsPerDrawing;
        }
    }

    public String getSurveyNumber() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),surveyNumber);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return surveyNumber;
        }
    }

    public String getPropertyID() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), propertyID);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return propertyID;
        }
    }
}
