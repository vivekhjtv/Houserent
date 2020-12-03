package com.example.houserent;

import java.io.Serializable;

public class UploadDetails implements Serializable {
    private String mTitle;
    private String mImageUrl;
    private String mAddress;
    private String mArea;
    private String mPrice;
    private String mFloorNo;
    private String mBedroom;
    private String mBathroom;
    private String mBalcony;
    private String mFurnishing;
    private String mBachelorsAllow;
    private String mMaitenance;
    private String mTotalFloor;
    private String mCarParking;
    private String mFacing;
    private String mListed;
    private String mCity;
    private String mId;
    private String mavailable;
    private String sellerID;

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public UploadDetails(){
        //Empty Constructor
    }
    public UploadDetails(String title,String address,String area,String price,String imageUrl,String floorNo,String bedroom,String bathroom,
                         String balcony,String furnishing,String bachelorsAllow,String maintenance,String totalFloor,String carParking,
                         String facing,String listed,String city,String id,String available,String sellerID){
        mTitle=title;
        mArea=area;
        mAddress=address;
        mImageUrl=imageUrl;
        mPrice=price;
        mFloorNo=floorNo;
        mBedroom=bedroom;
        mBathroom=bathroom;
        mBalcony=balcony;
        mFurnishing=furnishing;
        mBachelorsAllow=bachelorsAllow;
        mMaitenance=maintenance;
        mTotalFloor=totalFloor;
        mCarParking=carParking;
        mFacing=facing;
        mListed=listed;
        mCity=city;
        mId=id;
        mavailable=available;
        this.sellerID = sellerID;
    }


    public String getAvailable(){ return mavailable;}
    public void setAvailable(String available) { mavailable=available; }


    public String getTitle(){
        return mTitle;
    }
    public void setTitle(String title){
        mTitle=title;
    }


    public String getArea(){
        return mArea;
    }
    public void setArea(String area){
        mArea=area;
    }


    public String getPrice(){
        return mPrice;
    }
    public void setPrice(String price){
        mPrice=price;
    }


    public String getAddress(){
        return mAddress;
    }
    public void setAddress(String address){
        mAddress=address;
    }

    public String getmImageUrl(){
        return mImageUrl;
    }
    public void setImageUrl(String imageUrl){
        mImageUrl=imageUrl;
    }


    public String getFloorNo(){
        return mFloorNo;
    }
    public void setFloorNo(String floorNo){
        mFloorNo=floorNo;
    }

    public String getBedroom(){
        return mBedroom;
    }
    public void setBedroom(String bedroom){
        mBedroom=bedroom;
    }

    public String getBathroom(){
        return mBathroom;
    }
    public void setBathroom(String bathroom){
        mBathroom=bathroom;
    }

    public String getBalcony(){
        return mBalcony;
    }
    public void setBalcony(String balcony){
        mBalcony=balcony;
    }

    public String getFurnishing(){
        return mFurnishing;
    }
    public void setFurnishing(String furnishing){
        mFurnishing=furnishing;
    }

    public String getBachelorsAllow(){
        return mBachelorsAllow;
    }
    public void setBachelorsAllow(String bachelorsAllow){
        mBachelorsAllow=bachelorsAllow;
    }

    public String getMaitenance(){
        return mMaitenance;
    }
    public void setMaitenance(String maintenance){
        mMaitenance=maintenance;
    }

    public String getTotalFloor(){
        return mTotalFloor;
    }
    public void setTotalFloor(String totalFloor){
        mTotalFloor=totalFloor;
    }

    public String getCarParking(){
        return mCarParking;
    }
    public void setCarParking(String carParking){
        mCarParking=carParking;
    }

    public String getFacing(){
        return mFacing;
    }
    public void setFacing(String facing){
        mFacing=facing;
    }

    public String getListed(){
        return mListed;
    }
    public void setListed(String listed){
        mListed=listed;
    }


    public String getCity(){
        return mCity;
    }
    public void setCity(String city){
        mCity=city;
    }


    public String getId() { return mId; }
    public void setId(String id) {mId=id;}
}

