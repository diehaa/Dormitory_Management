/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phangiabao
 */
public class Users {
    private int usersId;
    private String username;
    private String password;
    private String name;
    private String email;
    private String phone;
    private String parentName;
    private String parentPhone;
    private double balance;
    private String avatar;
    private Room roomId;

    public Users() {
    }

    
    //tao tai khoan
    public Users(int usersId, String username, String password, String name, String email, String phone, String parentName, String parentPhone) {
        this.usersId = usersId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }

    //hien thi thong tin

    public Users(int usersId, String username, String password, String name, String email, String phone, String parentName, String parentPhone, double balance, String avatar, Room roomId) {
        this.usersId = usersId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
        this.balance = balance;
        this.avatar = avatar;
        this.roomId = roomId;
    }
    
    
    //thay doi thong tin
    public Users(int usersId, String name, String email, String phone, String parentName, String parentPhone) {
        this.usersId = usersId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.parentName = parentName;
        this.parentPhone = parentPhone;
    }
    
    //booking
    public Users(int usersId, Room roomId) {
        this.usersId = usersId;
        this.roomId = roomId;
    }

    public Users(int usersId, String password) {
        this.usersId = usersId;
        this.password = password;
    }
    
    
    
    public int getUsersId() {
        return usersId;
    }

    public void setUsersId(int usersId) {
        this.usersId = usersId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getParentPhone() {
        return parentPhone;
    }

    public void setParentPhone(String parentPhone) {
        this.parentPhone = parentPhone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return usersId+"";
    }
    
    
}
