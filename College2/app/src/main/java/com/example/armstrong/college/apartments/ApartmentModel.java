package com.example.armstrong.college.apartments;

public class ApartmentModel {
    String apartmentName;
    int no_Of_Houses;
    String location;
    String meterNo;

    public ApartmentModel(String apartmentName, int no_Of_Houses, String location, String meterNo){
        this.apartmentName = apartmentName;
        this.no_Of_Houses = no_Of_Houses;
        this.location = location;
        this.meterNo = meterNo;
    }

    public void setApartmentName(String apartmentName) {
        this.apartmentName = apartmentName;
    }

    public void setNo_Of_Houses(int no_Of_Houses) {
        this.no_Of_Houses = no_Of_Houses;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setMeterNo(String meterNo) {
        this.meterNo = meterNo;
    }

    public String getApartmentName() {
        return apartmentName;
    }

    public int getNo_Of_Houses() {
        return no_Of_Houses;
    }

    public String getLocation() {
        return location;
    }

    public String getMeterNo() {
        return meterNo;
    }
}
