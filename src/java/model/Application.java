/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author phangiabao
 */
public class Application {

    private int applicationId;
    private Users userid;
    private Room roomid;
    private String title;
    private String reason;
    private String file;
    private String status;
    private String comment;

    public Application() {
    }

    //hien thi chuc nang

    public Application(int applicationId, Users userid, Room roomid, String title, String reason, String file, String status, String comment) {
        this.applicationId = applicationId;
        this.userid = userid;
        this.roomid = roomid;
        this.title = title;
        this.reason = reason;
        this.file = file;
        this.status = status;
        this.comment = comment;
    }
    

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }

    public Users getUserid() {
        return userid;
    }

    public void setUserid(Users userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Room getRoomid() {
        return roomid;
    }

    public void setRoomid(Room roomid) {
        this.roomid = roomid;
    }

}
