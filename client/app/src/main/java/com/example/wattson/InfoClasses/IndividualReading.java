package com.example.wattson.InfoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class IndividualReading implements Parcelable {

    private String m_TimeStamp;
    private String m_reading;
    private double m_doubleReading;


    protected IndividualReading(Parcel in) {
        m_TimeStamp = in.readString();
        m_reading = in.readString();
    }

    public String getReading() {return m_reading;}
    public void setReading(String m_reading) { this.m_reading = m_reading;}


    public String getTimeStamp() { return m_TimeStamp; }
    public void setTimeStamp(String m_TimeStamp) { this.m_TimeStamp = m_TimeStamp; }

    public IndividualReading(String i_timeStamp, String i_reading){
        m_reading = i_reading;
        m_TimeStamp = i_timeStamp;
        m_doubleReading = Double.parseDouble(m_reading);
    }

    public double getDoubleReading(){
        return m_doubleReading;
    }

    public static final Creator<IndividualReading> CREATOR = new Creator<IndividualReading>() {
        @Override
        public IndividualReading createFromParcel(Parcel in) {
            return new IndividualReading(in);
        }

        @Override
        public IndividualReading[] newArray(int size) {
            return new IndividualReading[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_TimeStamp);
        parcel.writeString(m_reading);
    }
}
