package com.example.armstrong.college.houses;
public class HouseModel {
    String houseNum;
    String meterNum;
    Boolean paymentStatus;

    public HouseModel(String houseNum, String meterNum, Boolean paymentStatus){
        this.houseNum = houseNum;
        this.meterNum = meterNum;
        this.paymentStatus = paymentStatus;
    }

    public void setHouseNum(String houseNum) {
        this.houseNum = houseNum;
    }

    public void setMeterNum(String meterNum) {
        this.meterNum = meterNum;
    }

    public void setPaymentStatus(Boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getHouseNum() {
        return houseNum;
    }

    public String getMeterNum() {
        return meterNum;
    }

    public Boolean getPaymentStatus() {
        return paymentStatus;
    }

}
