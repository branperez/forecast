package me.brandonmichael.forcast.models;

import me.brandonmichael.forcast.models.Security.Password;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {


    @Id
    @GeneratedValue
    private int id;

    private String username;

    private String email;

    @OneToOne
    private Password password;

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Location> locations = new ArrayList<>();

    public User() {

    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void addLocation(Location item) {
        locations.add(item);
    }
}
