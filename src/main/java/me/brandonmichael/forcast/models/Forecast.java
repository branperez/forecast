package me.brandonmichael.forcast.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Forecast {

    private String summary;
    private  String icon;

    private ArrayList<HashMap<String, String>> data = new ArrayList<>();

    public Forecast() {}

    public Forecast(String summary, String icon) {
        this.summary = summary;
        this.icon = icon;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public ArrayList<HashMap<String, String>> getData() {
        return data;
    }

    public void setData(ArrayList<HashMap<String, String>> data) {
        this.data = data;
    }


}
