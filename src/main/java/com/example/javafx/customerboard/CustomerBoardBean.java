package com.example.javafx.customerboard;

public class CustomerBoardBean
{
    String mobile,cname,emailid,dos,area,hawkerid,papers,prices;

    public CustomerBoardBean(String mobile, String cname, String emailid, String dos, String area, String hawkerid, String papers, String prices)
    {
        this.mobile = mobile;
        this.cname = cname;
        this.emailid = emailid;
        this.dos = dos;
        this.area = area;
        this.hawkerid = hawkerid;
        this.papers = papers;
        this.prices = prices;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getDos() {
        return dos;
    }

    public void setDos(String dos) {
        this.dos = dos;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHawkerid() {
        return hawkerid;
    }

    public void setHawkerid(String hawkerid) {
        this.hawkerid = hawkerid;
    }

    public String getPapers() {
        return papers;
    }

    public void setPapers(String papers) {
        this.papers = papers;
    }

    public String getPrices() {
        return prices;
    }

    public void setPrices(String prices) {
        this.prices = prices;
    }
}
