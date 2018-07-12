package me.brandonmichael.forcast.models.Security;

import me.brandonmichael.forcast.models.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Entity
public class Password {

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    private byte[] hash;

    private byte[] salt;

    @OneToOne
    private User user;

    public static final SecureRandom random = new SecureRandom();

    public Password() {}

    public Password(String password) {
        this.salt = random.generateSeed(25);


        try {
            this.hash = hashPassword(password, this.salt);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        }

    }

    /* TODO: find alternative way to check passwords */
    public Password(String password, byte[] salt) {
        this.salt = salt;


        try {
            this.hash = hashPassword(password, this.salt);
        } catch(NoSuchAlgorithmException e) {
            System.out.println(e);
        }

    }

    public byte[] hashPassword(String password, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.reset();
        md.update(salt);
        byte[] hashedBytes = md.digest(password.getBytes());
        return hashedBytes;

    }

    public int getId() {
        return id;
    }

    public byte[] getHash() {
        return hash;
    }

    public byte[] getSalt() {
        return salt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
