package pe.edu.tecsup.sugarormapp.model;

import android.widget.ImageView;

import com.orm.dsl.Table;

import java.util.Arrays;

@Table
public class User {
    private Long id;
    private String fullname;
    private String email;
    private String password;
    protected byte[] image;

    public User() {
    }


    public User( String fullname, String email, String password, byte[] image) {

        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.image = image;
    }

    public User(String fullname, String email, String password, ImageView imagen) {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
