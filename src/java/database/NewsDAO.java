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
import model.News;
import model.Room;
import model.Users;

/**
 *
 * @author phangiabao
 */
public class NewsDAO {

    public ArrayList<News> getList() {
        ArrayList<News> list = new ArrayList<>();
        String query = "select * from News";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Admin a1 = new Admin();
                a1.setAdminId(rs.getInt(4));
                Admin a2 = new database.AdminDAO().getListTaiKhoanAdminById(a1);
                list.add(new News(rs.getInt(1), rs.getString(2), rs.getString(3), a2, rs.getDate(5)));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public int delete(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from News  WHERE newsId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insert(News t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO News (newsId, title, content, adminId, timeCreate)  VALUES (?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getNewsId());
            st.setString(2, t.getTitle());
            st.setString(3, t.getContent());
            st.setInt(4, t.getAdminId().getAdminId());
            st.setDate(5, t.getTimeCreate());

            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(News t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE News  SET  title=?, content=?,timeCreate=? WHERE newsId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getTitle());
            st.setString(2, t.getContent());
            st.setDate(3, t.getTimeCreate());
            st.setInt(4, t.getNewsId());

            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public News getNewsById(String t) {
        News ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM News WHERE newsId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {

                Admin a1 = new Admin();
                a1.setAdminId(rs.getInt(4));
                Admin a2 = new database.AdminDAO().getListTaiKhoanAdminById(a1);
                ketQua = new News(rs.getInt(1), rs.getString(2), rs.getString(3), a2, rs.getDate(5));
            }

        } catch (Exception e) {
        }
        return ketQua;
    }

}
