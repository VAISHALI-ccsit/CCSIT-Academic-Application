package com.example.ccsitacademicapplication;

public class DataClass {
    private String imageURL, caption, url_pdf;

    public String getUrl_pdf() {
        return url_pdf;
    }

    public void setUrl_pdf(String url_pdf) {
        this.url_pdf = url_pdf;
    }

    public DataClass(){

    }



    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public DataClass(String imageURL, String caption, String url_pdf) {
        this.imageURL = imageURL;
        this.caption = caption;
        this.url_pdf=url_pdf;
    }
    public DataClass(String imageURL, String caption) {
        this.imageURL = imageURL;
        this.caption = caption;
        }
}
