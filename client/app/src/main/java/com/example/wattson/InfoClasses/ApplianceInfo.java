package com.example.wattson.InfoClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class ApplianceInfo implements Parcelable {

    private String m_ApplianceName;
    private double m_dailyPrice;
    private List<IndividualReading> m_ReadingList;

    protected ApplianceInfo(Parcel in) {
        m_ApplianceName = in.readString();
        m_dailyPrice = in.readDouble();
        m_ReadingList = in.createTypedArrayList(IndividualReading.CREATOR);
    }

    public List<IndividualReading> getReadingList() { return m_ReadingList; }
    public String getApplianceName() { return m_ApplianceName; }
    public double getDailyPrice() { return m_dailyPrice; }

    public void setReadingList(List<IndividualReading> m_ReadingList) { this.m_ReadingList = m_ReadingList; }
    public void setApplianceName(String m_Name) { this.m_ApplianceName = m_Name; }
    public void setDailyPrice(double dailyPrice){this.m_dailyPrice = dailyPrice;}


    public ApplianceInfo(String i_name, List<IndividualReading> i_info){
        m_ApplianceName = i_name;
        m_ReadingList = i_info;
    }

    /**
     * returns the last reading in the db
     * @return
     */
    public IndividualReading getLastReading(){
        return m_ReadingList.get(m_ReadingList.size() - 1);
    }

    /**
     * This method checks weather the reding of the last entry is high enought to be classified as currently on
     * @return
     */
    public Boolean isCurrentlyOn(){
        IndividualReading last = getLastReading();

        return (last.getDoubleReading() > 100);
    }

    public static final Creator<ApplianceInfo> CREATOR = new Creator<ApplianceInfo>() {
        @Override
        public ApplianceInfo createFromParcel(Parcel in) {
            return new ApplianceInfo(in);
        }

        @Override
        public ApplianceInfo[] newArray(int size) {
            return new ApplianceInfo[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_ApplianceName);
        parcel.writeDouble(m_dailyPrice);
        parcel.writeTypedList(m_ReadingList);
    }
}
