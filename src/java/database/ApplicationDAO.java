/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Admin;
import model.Application;
import model.Room;
import model.Users;

/**
 *
 * @author phangiabao
 */
public class ApplicationDAO {

    public ArrayList<Application> getList() {
        ArrayList<Application> list = new ArrayList<>();
        String query = "select * from Application";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u1 = new Users();
                u1.setUsersId(rs.getInt(2));
                Users u2 = new database.UserDAO().getListTaiKhoanUsersById(u1);
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(3));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                list.add(new Application(rs.getInt(1), u2, r2, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) ));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Application getListById(Application t) {
        Application ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Application WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getApplicationId());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                 Users u1 = new Users();
                u1.setUsersId(rs.getInt(2));
                Users u2 = new database.UserDAO().getListTaiKhoanUsersById(u1);
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(3));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                ketQua = new Application(rs.getInt(1), u2, r2, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) );
            }

        } catch (Exception e) {
        }
        return ketQua;
    }

    public Application getListApplicationByIdString(String t) {
        Application ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Application WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Users u1 = new Users();
                u1.setUsersId(rs.getInt(2));
                Users u2 = new database.UserDAO().getListTaiKhoanUsersById(u1);
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(3));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                ketQua = new Application(rs.getInt(1), u2, r2, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) );
            }

        } catch (Exception e) {
        }
        return ketQua;
    }
    public ArrayList<Application> getListByUser(int t) {
        ArrayList<Application> list = new ArrayList<>();
        String query = "SELECT * FROM Application WHERE usersId=?";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, t);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u1 = new Users();
                u1.setUsersId(rs.getInt(2));
                Users u2 = new database.UserDAO().getListTaiKhoanUsersById(u1);
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(3));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                list.add(new Application(rs.getInt(1), u2, r2, rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8) ));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public int delete(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from Application  WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insert(Application t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO Application (applicationId, usersId, roomId, title, reason, files, statuses, comment)  VALUES (?,?,?,?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getApplicationId());
            st.setInt(2, t.getUserid().getUsersId());
            st.setInt(3, t.getRoomid().getRoomId());
            st.setString(4, t.getTitle());
            st.setString(5, t.getReason());
            st.setString(6, t.getFile());
            st.setString(7, t.getStatus());
            st.setString(8, t.getComment());

            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int updateStatus(Application t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE Application  SET   statuses=?, comment=? WHERE applicationId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getStatus());
            st.setString(2, t.getComment());
            st.setInt(3, t.getApplicationId());
            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    

}
