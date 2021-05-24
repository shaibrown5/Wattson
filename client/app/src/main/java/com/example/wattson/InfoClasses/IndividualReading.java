package com.example.wattson.InfoClasses;

import android.os.Parcel;
import android.os.Parcelable;

public class IndividualReading implements Parcelable {

    private double m_doubleReading;
    private long m_longTimeStamp;


    protected IndividualReading(Parcel in) {
        m_longTimeStamp = in.readLong();
        m_doubleReading = in.readLong();
    }

    public IndividualReading(String i_timeStamp, String i_reading){
        m_doubleReading = Double.parseDouble(i_reading);
        m_longTimeStamp = Long.parseLong(i_timeStamp);
    }

    public double getDoubleReading(){
        return m_doubleReading;
    }
    public long getLongTimeStamp() {return m_longTimeStamp;}

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
        parcel.writeDouble(m_longTimeStamp);
        parcel.writeLong(m_longTimeStamp);
    }
}
