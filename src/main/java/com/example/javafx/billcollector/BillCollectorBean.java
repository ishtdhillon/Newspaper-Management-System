package com.example.javafx.billcollector;

public class BillCollectorBean
{
    String mobile,dos,doe,days,bill,status;

    public BillCollectorBean(String mobile, String dos, String doe, String days, String bill, String status)
    {
        this.mobile = mobile;
        this.dos = dos;
        this.doe = doe;
        this.days = days;
        this.bill = bill;
        this.status = status;
    }

    public String getMobile()
    {
        return mobile;
    }

    public void setMobile(String mobile)
    {
        this.mobile = mobile;
    }

    public String getDos()
    {
        return dos;
    }

    public void setDos(String dos)
    {
        this.dos = dos;
    }

    public String getDoe()
    {
        return doe;
    }

    public void setDoe(String doe)
    {
        this.doe = doe;
    }

    public String getDays()
    {
        return days;
    }

    public void setDays(String days)
    {
        this.days = days;
    }

    public String getBill()
    {
        return bill;
    }

    public void setBill(String bill)
    {
        this.bill = bill;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}
