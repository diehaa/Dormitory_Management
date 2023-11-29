/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phangiabao
 */
public class Admin {
    private int AdminId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String role;
    private String phone;
    private String avatar;

    public Admin() {
    }

    public Admin(int AdminId, String username, String password, String name, String email, String role, String phone) {
        this.AdminId = AdminId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }
    
    public Admin(int AdminId, String name, String email, String role, String phone) {
        this.AdminId = AdminId;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
    }

    public Admin(int AdminId, String username, String password, String name, String email, String role, String phone, String avatar) {
        this.AdminId = AdminId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
        this.phone = phone;
        this.avatar = avatar;
    }

    public Admin(int AdminId, String password) {
        this.AdminId = AdminId;
        this.password = password;
    }

    public int getAdminId() {
        return AdminId;
    }

    public void setAdminId(int AdminId) {
        this.AdminId = AdminId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    
    
}
