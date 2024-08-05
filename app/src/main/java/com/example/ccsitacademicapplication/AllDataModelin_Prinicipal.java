package com.example.ccsitacademicapplication;

public class AllDataModelin_Prinicipal {

    String userid,BCA, MCA, BTech, MTech,BSc, MSc;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getBCA() {
        return BCA;
    }

    public void setBCA(String BCA) {
        this.BCA = BCA;
    }

    public String getMCA() {
        return MCA;
    }

    public void setMCA(String MCA) {
        this.MCA = MCA;
    }

    public String getBTech() {
        return BTech;
    }

    public void setBTech(String BTech) {
        this.BTech = BTech;
    }

    public String getMTech() {
        return MTech;
    }

    public void setMTech(String MTech) {
        this.MTech = MTech;
    }

    public String getBSc() {
        return BSc;
    }

    public void setBSc(String BSc) {
        this.BSc = BSc;
    }

    public String getMSc() {
        return MSc;
    }

    public void setMSc(String MSc) {
        this.MSc = MSc;
    }


    public AllDataModelin_Prinicipal(String bca, String mca, String BTech, String MTech, String BSc, String MSc) {
       // this.userid=userid;
        this.BCA=BCA;
        this.MCA=MCA;
        this.BTech=BTech;
        this.MTech=MTech;
        this.BSc=BSc;
        this.MSc=MSc;
    }

}
