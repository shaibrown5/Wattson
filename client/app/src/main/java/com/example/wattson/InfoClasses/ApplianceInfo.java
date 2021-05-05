package com.example.wattson.InfoClasses;

import java.util.List;

public class ApplianceInfo {

    private String m_ApplianceName;
    private double m_dailyPrice;
    private List<IndividualReading> m_ReadingList;

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

    public IndividualReading getLastReading(){
        return m_ReadingList.get(m_ReadingList.size() - 1);
    }
}
