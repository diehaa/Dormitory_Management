/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author phangiabao
 */
public class News {
    private int newsId;
    private String title;
    private String content;
    private Admin adminId;
    private Date timeCreate;

    public News() {
    }

    public News(int newsId, String title, String content, Admin adminId, Date timeCreate) {
        this.newsId = newsId;
        this.title = title;
        this.content = content;
        this.adminId = adminId;
        this.timeCreate = timeCreate;
    }
    

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Admin getAdminId() {
        return adminId;
    }

    public void setAdminId(Admin adminId) {
        this.adminId = adminId;
    }

    public Date getTimeCreate() {
        return timeCreate;
    }

    public void setTimeCreate(Date timeCreate) {
        this.timeCreate = timeCreate;
    }
    
    
}
