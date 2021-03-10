/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blb.domain.users;

import java.io.Serializable;

/**
 *
 * @author Sebastian Wild
 */
public class Employee implements Serializable {

    private int employeeId;
    private String email;
    private String password;
    private int accessLevel;

    public Employee() {
    }

    public Employee(int employeeId, String email, String password, int accessLevel) {
        this.employeeId = employeeId;
        this.email = email;
        this.password = password;
        this.accessLevel = accessLevel;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
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

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

}
