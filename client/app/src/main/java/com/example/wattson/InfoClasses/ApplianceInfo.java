package com.example.wattson.InfoClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.sql.Array;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ApplianceInfo implements Parcelable {

    private String m_ApplianceName;
    private double m_dailyPrice;
    private int m_numReadingOn;
    private List<IndividualReading> m_ReadingList;
    private List<TimeData> m_timeList;

    protected ApplianceInfo(Parcel in) {
        m_ApplianceName = in.readString();
        m_dailyPrice = in.readDouble();
        m_ReadingList = in.createTypedArrayList(IndividualReading.CREATOR);
        m_timeList = in.createTypedArrayList(TimeData.CREATOR);
    }

    public List<IndividualReading> getReadingList() { return m_ReadingList; }
    public String getApplianceName() { return m_ApplianceName; }
    public double getDailyPrice() { return m_dailyPrice; }
    public int getNumActivations() {return m_timeList.size();}
    public List<TimeData> getTimeList(){return m_timeList;}

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

        return (last.getDoubleReading() > 20);
    }


    /**
     * This method calcs the daily cost
     * the equations : aum all the readings,
     * divide by 3600 (convert to watt per hour [WPH])
     * and multiply by 0.6 (price of WPH in NIS  = 1/6000
     *
     */
    public void updateCostAndActivationsNum(){
        double totalReading = 0;
        boolean isOn = startOn();
        List<TimeData> tmpList = new ArrayList<>();
        long startTime = -1;
        long endTime = -1;

        for (IndividualReading currRead: m_ReadingList) {
            totalReading += currRead.getDoubleReading();
            boolean currStatus = currRead.getDoubleReading() > 20;

            //if was off and is now on
            if(!isOn && currStatus){
                startTime = currRead.getLongTimeStamp();
            }
            // if was on and is now off
            else if(isOn && !currStatus){
                endTime = currRead.getLongTimeStamp();
                tmpList.add(new TimeData(startTime, endTime));
            }

            if(currStatus){
                m_numReadingOn ++;
            }

            isOn = currStatus;
        }

        // if is currently on
        if(isCurrentlyOn()){
            tmpList.add(new TimeData(startTime));

        }

        m_timeList = tmpList;
        m_dailyPrice = round(totalReading/6000.0, 3);
    }


    /**
     * round a double to @places behind the 0
     * @param value
     * @param places
     * @return
     */
    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    /**
     * This checks the starting state of the appliance
     * @return
     */
    private boolean startOn(){
        return m_ReadingList.get(0).getDoubleReading() > 20;
    }

    public String getTotalTimeOn(){

        int seconds = m_numReadingOn % 60;
        int hours = m_numReadingOn / 60;
        int min = hours % 60;
        hours = hours / 60;

        String secZero = seconds < 10 ? "0" : "";
        String minZero = min < 10 ? "0" : "";
        String hourZero = hours < 10 ? "0" : "";

        return String.format("%s%d:%s%d:%s%d", hourZero, hours, minZero, min, secZero, seconds);
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
        parcel.writeTypedList(m_timeList);
    }
}
