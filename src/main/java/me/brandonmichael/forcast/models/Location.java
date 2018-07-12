package me.brandonmichael.forcast.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Location {

    @Id
    @GeneratedValue
    private int id;

    private String city;

    private String administrative_area;

    private String country;

    private double latitude;

    private double longitude;

    private String latString;

    private String lngString;

    @ManyToOne
    private User user;

    public void latToString() {
        if(this.latitude!=0.0) {
            this.latString = Double.toString(this.latitude);
        }
    }

    public void lngToString() {
        if(this.longitude!=0.0) {
            this.lngString = Double.toString(this.longitude);
        }
    }

    public void latToDoulbe() {
        if(!this.latString.isEmpty()) {
            this.latitude = Double.parseDouble(this.latString);
        }
    }

    public void lngToDouble() {
        if(!this.lngString.isEmpty()) {
            this.longitude = Double.parseDouble(this.lngString);
        }
    }



    public Location() {}

    public Location(String latString, String lngString, String city, String administrative_area, String country) {
        this.city = city;
        this.administrative_area = administrative_area;
        this.country = country;
        this.latString = latString;
        this.lngString = lngString;

        latToDoulbe();
        lngToDouble();
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAdministrative_area() {
        return administrative_area;
    }

    public void setAdministrative_area(String administrative_area) {
        this.administrative_area = administrative_area;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLatString() {
        return latString;
    }

    public void setLatString(String latString) {
        this.latString = latString;
    }

    public String getLngString() {
        return lngString;
    }

    public void setLngString(String lngString) {
        this.lngString = lngString;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
