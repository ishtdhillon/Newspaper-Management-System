package com.example.javafx.tableview;

public class PaperMasterBean
{
    String paper,language,price;

    public PaperMasterBean(String paper, String language, String price)
    {
        this.paper = paper;
        this.language = language;
        this.price = price;
    }

    public String getPaper()
    {
        return paper;
    }

    public void setPaper(String paper)
    {
        this.paper = paper;
    }

    public String getLanguage()
    {
        return language;
    }

    public void setLanguage(String language)
    {
        this.language = language;
    }

    public String getPrice()
    {
        return price;
    }

    public void setPrice(String price)
    {
        this.price = price;
    }
}
