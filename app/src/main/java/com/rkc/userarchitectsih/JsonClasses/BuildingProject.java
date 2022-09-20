package com.rkc.userarchitectsih.JsonClasses;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.rkc.userarchitectsih.R;
import com.rkc.userarchitectsih.dto.ClientDataJson;
import com.rkc.userarchitectsih.dto.GramPanchayatDetails;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

@Entity(tableName = "building_project_table")
public class BuildingProject {


    @ColumnInfo(name = "uid")
    private int uid;

    @Ignore
    private Context context;

    @NonNull
    @PrimaryKey()
    @ColumnInfo(name = "projectID")
    private String projectId;
    @ColumnInfo(name = "clientEmail")
    private String clientEmail;
    @ColumnInfo(name = "developerEmail")
    private String developerEmail;
    @ColumnInfo(name = "structuralEmail")
    private String structuralEmail;
    @ColumnInfo(name = "archEmail")
    private String archEmail;

    @Embedded(prefix = "gram_")
    private GramPanchayatDetails gramPanchayatDetails;

    //@ColumnInfo(name = "clientData")
    @Embedded(prefix = "client_")
    private ClientDataJson clientDataJson;

    @Embedded(prefix = "developer_")
    //@Embedded()
    // @ColumnInfo(name = "developerData")
    private Architect developer;

     @Embedded(prefix = "structural_")
    //@ColumnInfo(name = "structuralData")
    private Architect structural;

    @Embedded
    private BuildingDetails buildingDetails;

    @Embedded
    private LandDetails landDetails;

    @ColumnInfo(name = "drawingURL")
    private String drawingURL;

    public BuildingProject(int uid, String projectId, String clientEmail, String developerEmail, String structuralEmail, String archEmail, ClientDataJson clientDataJson, com.rkc.userarchitectsih.JsonClasses.Architect developer, com.rkc.userarchitectsih.JsonClasses.Architect structural, BuildingDetails buildingDetails, com.rkc.userarchitectsih.JsonClasses.LandDetails landDetails, String drawingURL) {
        this.uid = uid;
        this.projectId = projectId;
        this.clientEmail = clientEmail;
        this.developerEmail = developerEmail;
        this.structuralEmail = structuralEmail;
        this.archEmail = archEmail;
        this.clientDataJson = clientDataJson;
        this.developer = developer;
        this.structural = structural;
        this.buildingDetails = buildingDetails;
        this.landDetails = landDetails;
        this.drawingURL = drawingURL;
    }

    public BuildingProject(Context context, String projectId, String clientEmail, String developerEmail,
                           String structuralEmail, String archEmail, ClientDataJson clientDataJson, Architect developer,
                           Architect structural, BuildingDetails buildingDetails, LandDetails landDetails, GramPanchayatDetails gramPanchayatDetails
                           ,String drawingURL) {
        this.context = context;
        try {
            this.projectId = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),projectId);
            this.clientEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),clientEmail);
            this.developerEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),developerEmail);
            this.structuralEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),structuralEmail);
            this.archEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),archEmail);
            this.clientDataJson = clientDataJson;
            this.developer = developer;
            this.structural = structural;
            this.buildingDetails = buildingDetails;
            this.landDetails = landDetails;
            this.gramPanchayatDetails = gramPanchayatDetails;
            this.drawingURL = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),drawingURL);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }

    }

    public int getUid() {
        return uid;
    }

    public String getProjectId() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),projectId);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return projectId;
        }
    }

    public String getClientEmail() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),clientEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return clientEmail;
        }
    }
    public void setGramPanchayatDetails(GramPanchayatDetails gramPanchayatDetails) {
        this.gramPanchayatDetails = gramPanchayatDetails;
    }

    public GramPanchayatDetails getGramPanchayatDetails() {
        return gramPanchayatDetails;
    }

    public String getDeveloperEmail() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),developerEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return developerEmail;
        }
    }

    public String getStructuralEmail() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),structuralEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return structuralEmail;
        }
    }

    public String getArchEmail() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),archEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return archEmail;
        }
    }

    public ClientDataJson getClientDataJson() {
        return clientDataJson;
    }

    public Architect getDeveloper() {
        return developer;
    }

    public Architect getStructural() {
        return structural;
    }

    public BuildingDetails getBuildingDetails() {
        return buildingDetails;
    }

    public LandDetails getLandDetails() {
        return landDetails;
    }

    public String getDrawingURL() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey),drawingURL);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return drawingURL;
        }
    }

    public void setDeveloper(Architect developer) {
        this.developer = developer;
    }

    public void setStructural(Architect structural) {
        this.structural = structural;
    }

    @Override
    public String toString() {
        return "BuildingProject{" +
                "projectId='" + projectId + '\'' +
                ", clientEmail='" + clientEmail + '\'' +
                ", developerEmail='" + developerEmail + '\'' +
                ", structuralEmail='" + structuralEmail + '\'' +
                ", archEmail='" + archEmail + '\'' +
                ", gramPanchayatDetails=" + gramPanchayatDetails +
                ", clientDataJson=" + clientDataJson +
                ", developer=" + developer +
                ", structural=" + structural +
                ", buildingDetails=" + buildingDetails +
                ", landDetails=" + landDetails +
                ", drawingURL='" + drawingURL + '\'' +
                '}';
    }
}
