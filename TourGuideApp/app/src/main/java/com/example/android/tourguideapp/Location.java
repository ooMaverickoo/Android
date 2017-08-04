package com.example.android.tourguideapp;

/**
 * Created by Christian on 25.05.2017.
 */

public class Location {

    private final static int NO_IMAGE_PROVIDED = -1;
    private final static int NO_ITEM_PROVIDED = -1;
    private int mNameId;
    private int mDescriptionId;
    private int mAddressId;
    private int mPostCodeId;
    private int mEmailId = NO_ITEM_PROVIDED;
    private int mWebsiteId = NO_ITEM_PROVIDED;
    private int mTelephoneNumberId = NO_ITEM_PROVIDED;
    private int mLocationImageId = NO_IMAGE_PROVIDED;


    public Location(int name, int address, int postCode,
                    int description, int imageId) {
        this.mNameId = name;
        this.mDescriptionId = description;
        this.mAddressId = address;
        this.mPostCodeId = postCode;
        this.mLocationImageId = imageId;

    }

    public Location(int name, int address, int postCode,
                    int description) {
        this.mNameId = name;
        this.mDescriptionId = description;
        this.mAddressId = address;
        this.mPostCodeId = postCode;

    }

    public Location(int name, int address, int postCode, int telephoneNumber,
                    int description, int imageId) {
        this.mNameId = name;
        this.mDescriptionId = description;
        this.mLocationImageId = imageId;
        this.mAddressId = address;
        this.mPostCodeId = postCode;
        this.mTelephoneNumberId = telephoneNumber;

    }

    public int getmEmail() {
        return mEmailId;
    }

    public void setmEmail(int mEmail) {
        this.mEmailId = mEmail;
    }

    public int getmWebsite() {
        return mWebsiteId;
    }

    public void setmWebsite(int mWebsite) {
        this.mWebsiteId = mWebsite;
    }

    public int getName() {
        return mNameId;
    }

    public void setName(int mName) {
        this.mNameId = mName;
    }

    public int getDescription() {
        return mDescriptionId;
    }

    public void setDescription(int mDescription) {
        this.mDescriptionId = mDescription;
    }

    public int getLocationImageId() {
        return mLocationImageId;
    }

    public void setLocationImageId(int mLocationImageId) {
        this.mLocationImageId = mLocationImageId;
    }

    public boolean hasImage() {
        return this.getLocationImageId() != NO_IMAGE_PROVIDED;
    }

    public int getAddress() {
        return mAddressId;
    }

    public void setAddress(int mAddress) {
        this.mAddressId = mAddress;
    }

    public int getPostCode() {
        return mPostCodeId;
    }

    public void setPostCode(int mPostCode) {
        this.mPostCodeId = mPostCode;
    }

    public int getTelephoneNumber() {
        return mTelephoneNumberId;
    }

    public void setTelephoneNumber(int mTelephoneNumber) {
        this.mTelephoneNumberId = mTelephoneNumber;
    }
}
