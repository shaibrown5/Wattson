package com.example.wattson.InfoClasses;

public class IndividualReading {

    private String m_TimeStamp;
    private String m_reading;


    public String getReading() {return m_reading;}
    public void setReading(String m_reading) { this.m_reading = m_reading;}


    public String getTimeStamp() { return m_TimeStamp; }
    public void setTimeStamp(String m_TimeStamp) { this.m_TimeStamp = m_TimeStamp; }

    public IndividualReading(String i_timeStamp, String i_reading){
        m_reading = i_reading;
        m_TimeStamp = i_timeStamp;
    }


}
