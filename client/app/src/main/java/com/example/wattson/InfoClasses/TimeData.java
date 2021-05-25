package com.example.wattson.InfoClasses;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;

public class TimeData implements Parcelable {
    private String m_startTime;
    private String m_endTime;

    public TimeData(Long i_startTime){
        m_startTime = convertTime(i_startTime);
        m_endTime = "still active";
    }

    public TimeData(Long i_start, Long i_end){
        m_startTime = convertTime(i_start);
        m_endTime = convertTime(i_end);
    }

    public String getEndTime() {
        return m_endTime;
    }

    public String getStartTime() {
        return m_startTime;
    }

    /**
     * this method receives a timestamp as a Long and return time of activation
     * @param i_timestamp
     * @return
     */
    private String convertTime(long i_timestamp){
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm");
        String date = sdf.format(new java.util.Date (i_timestamp*1000));

        return date;
    }

    protected TimeData(Parcel in) {
        m_startTime = in.readString();
        m_endTime = in.readString();
    }

    public static final Creator<TimeData> CREATOR = new Creator<TimeData>() {
        @Override
        public TimeData createFromParcel(Parcel in) {
            return new TimeData(in);
        }

        @Override
        public TimeData[] newArray(int size) {
            return new TimeData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(m_startTime);
        parcel.writeString(m_endTime);
    }
}
