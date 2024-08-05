package com.example.ccsitacademicapplication;

public class model_bca {

   private String notification_bca, image_bca_model_class;

    public void setNotification_bca(String notification_bca) {
        this.notification_bca = notification_bca;
    }

    public String getNotification_bca() {
        return notification_bca;
    }
    public String getImage_bca_model_class() {
        return image_bca_model_class;
    }

    public void setImage_bca_model_class(String image_bca_model_class) {
        this.image_bca_model_class = image_bca_model_class;
    }

    public model_bca(String notification_bca, String image_bca_model_class) {
        this.image_bca_model_class = image_bca_model_class;
        this.notification_bca = notification_bca;

    }
    /*public model_bca(String notification_bca) {
        model_bca.notification_bca = notification_bca;
    }

    public static String getNotification_bca() {
        return notification_bca;
    }

    public static CharSequence setNotification_bca() {

        return model_bca.notification_bca;*/
    }



