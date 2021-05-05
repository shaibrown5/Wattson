package com.example.wattson.InfoClasses;

public class UtilCard {

    private String m_label;
    private String m_price;

    public UtilCard(String i_label, String i_price){
      m_label = i_label;
      m_price = i_price;
    }

    //getter
    public String getLabel() {return m_label;}
    public String getPrice() {return m_price;}

    //setter
    public void setLabel(String i_label) {m_label = i_label;}
    public void setPrice(String i_price) {m_label = i_price;}
}
