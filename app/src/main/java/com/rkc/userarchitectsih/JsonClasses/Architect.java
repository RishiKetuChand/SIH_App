package com.rkc.userarchitectsih.JsonClasses;

import android.content.Context;

import androidx.room.Ignore;

import com.rkc.userarchitectsih.R;
import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class Architect {
    private String engID;
    private String engAadhar;
    private String engName;
    private String engAddress;
    private String engMobile;
    private String engEmail;
    private String engRole;
    private String licenseNo;
    private String validUpto;
    @Ignore
    private Context context;

    public Architect(String engID, String engAadhar, String engName, String engAddress, String engMobile, String engEmail, String engRole, String licenseNo) {
        this.engID = engID;
        this.engAadhar = engAadhar;
        this.engName = engName;
        this.engAddress = engAddress;
        this.engMobile = engMobile;
        this.engEmail = engEmail;
        this.engRole = engRole;
        this.licenseNo = licenseNo;
    }

    public Architect(Context context, String engID, String engAadhar, String engName, String engAddress, String engMobile, String engEmail, String engRole, String licenseNo, String validUpto) {
        try {
            this.context=context;
            this.engID = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engID);
            this.engAadhar = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engAadhar);
            this.engName = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engName);
            this.engAddress = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engAddress);
            this.engMobile = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engMobile);
            this.engEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engEmail);
            this.engRole = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), engRole);
            this.licenseNo = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), licenseNo);
            this.validUpto = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey), validUpto);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }


    public String getEngID() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engID);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engID;
        }
    }

    public String getEngAadhar() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engAadhar);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engAadhar;
        }
    }

    public String getEngName() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engName);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engName;
        }
    }

    public String getEngAddress() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engAddress);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engAddress;
        }
    }

    public String getEngMobile() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engMobile);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engMobile;
        }
    }

    public String getEngEmail() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engEmail;
        }
    }

    public String getEngRole() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), engRole);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return engRole;
        }
    }

    public String getLicenseNo() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), licenseNo);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return licenseNo;
        }
    }

    public String getValidUpto() {
        try {
            return AESCrypt.decrypt(context.getResources().getString(R.string.secretKey), validUpto);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            return licenseNo;
        }
    }

    public void setEngID(String engID) {
        try {
            this.engID = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engID);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngAadhar(String engAadhar) {
        try {
            this.engAadhar = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engAadhar);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngName(String engName) {
        try {
            this.engName = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engName);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngAddress(String engAddress) {
        try {
            this.engAddress = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engAddress);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngMobile(String engMobile) {
        try {
            this.engMobile = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engMobile);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngEmail(String engEmail) {
        try {
            this.engEmail = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engEmail);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setEngRole(String engRole) {
        try {
            this.engRole = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),engRole);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setLicenseNo(String licenseNo) {
        try {
            this.licenseNo = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),licenseNo);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    public void setValidUpto(String validUpto) {
        try {
            this.validUpto = AESCrypt.encrypt(context.getResources().getString(R.string.secretKey),validUpto);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
    }
}
