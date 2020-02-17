package com.theweatherforecast.Model;

public class FinalTemp {

    private String finalMaxTemp;
    private String finalMinTemp;

    public FinalTemp() {
    }

    public FinalTemp(String finalMaxTemp, String finalMinTemp) {
        this.finalMaxTemp = finalMaxTemp;
        this.finalMinTemp = finalMinTemp;
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
}
