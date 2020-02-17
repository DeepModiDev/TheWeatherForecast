package com.theweatherforecast.Model;

public class DataCallModel {

    private String finalMaxTemp;
    private String finalMinTemp;
    private String finalDate;
    private String condition;
    private String img_sun;
    private String img_cloudy;
    private String img_strom;

    public DataCallModel() {
    }

    public DataCallModel(String finalMaxTemp, String finalMinTemp, String condition) {
        this.finalMaxTemp = finalMaxTemp;
        this.finalMinTemp = finalMinTemp;
        this.condition = condition;
    }

    public String getFinalMaxTemp() {
        return finalMaxTemp;
    }

    public void setFinalMaxTemp(String finalMaxTemp) {
        this.finalMaxTemp = finalMaxTemp;
    }

    public String getFinalMinTemp() {
        return finalMinTemp;
    }

    public void setFinalMinTemp(String finalMinTemp) {
        this.finalMinTemp = finalMinTemp;
    }

    public String getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(String finalDate) {
        this.finalDate = finalDate;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getImg_sun() {
        return img_sun;
    }

    public void setImg_sun(String img_sun) {
        this.img_sun = img_sun;
    }

    public String getImg_cloudy() {
        return img_cloudy;
    }

    public void setImg_cloudy(String img_cloudy) {
        this.img_cloudy = img_cloudy;
    }

    public String getImg_strom() {
        return img_strom;
    }

    public void setImg_strom(String img_strom) {
        this.img_strom = img_strom;
    }
}
