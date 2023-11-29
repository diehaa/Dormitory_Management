/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
/**
 *
 * @author phangiabao
 */
public class Payment {
    private int paymentId;
    private Users userId;
    private Room roomId;
    private String semester;
    private int total;
    private String status;

    public Payment() {
    }

    public Payment(int paymentId, Users userId, Room roomId, String semester, int total, String status) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.roomId = roomId;
        this.semester = semester;
        this.total = total;
        this.status = status;
    }

    public Payment(int paymentId, String status) {
        this.paymentId = paymentId;
        this.status = status;
    }
    

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public Users getUserId() {
        return userId;
    }

    public void setUserId(Users userId) {
        this.userId = userId;
    }

    public Room getRoomId() {
        return roomId;
    }

    public void setRoomId(Room roomId) {
        this.roomId = roomId;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    
    
    
}
