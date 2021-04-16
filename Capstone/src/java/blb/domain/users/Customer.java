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
public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String address;
    private String postalCode;
    private String email;
    private String password;
    private long phoneNumber;
    private String community;
    private char status;
    private char customerType;
    
    public Customer() {
    }

    public Customer(int customerId, String firstName, String lastName, String address, String postalCode, String email, String password, long phoneNumber, String community, char status, char customerType) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postalCode = postalCode;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.community = community;
        this.status = status;
        this.customerType = customerType;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public char getCustomerType() {
        return customerType;
    }

    public void setCustomerType(char customerType) {
        this.customerType = customerType;
    }

    
}
