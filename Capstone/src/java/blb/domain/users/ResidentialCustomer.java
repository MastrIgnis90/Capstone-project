/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.domain.users;

/**
 *
 * @author Sebastian Wild
 */
public class ResidentialCustomer {
    
    private String firstname;
    private String lastname;
    private String address;
    private String postalcode;
    private String community;
    private String email;
    private String password;
    private int phonenumber;
    private char status;

    public ResidentialCustomer(String firstname, String lastname, String address, String postalcode, String community, String email, String password, int phonenumber, char status) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.postalcode = postalcode;
        this.community = community;
        this.email = email;
        this.password = password;
        this.phonenumber = phonenumber;
        this.status = status;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public String getCommunity() {
        return community;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getPhonenumber() {
        return phonenumber;
    }

    public char getStatus() {
        return status;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhonenumber(int phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setStatus(char status) {
        this.status = status;
    }
    
    
    
}
