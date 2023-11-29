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
import model.Room;
import model.Users;

/**
 *
 * @author phangiabao
 */
public class UserDAO {

    public ArrayList<Users> getListUser() {
        ArrayList<Users> list = new ArrayList<>();
        String query = "select * from users";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(11));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                list.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), r2));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public Users getListTaiKhoanUsersById(Users t) {
        Users ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM Users WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getUsersId());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(11));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                ketQua = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), r2);
            }

        } catch (Exception e) {
        }
        return ketQua;
    }

    public int delete(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "DELETE from users  WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int insert(Users t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "INSERT INTO users (usersId, username, password, name, email, phone, parentName, parentPhone, balance, avatar)  VALUES (?,?,?,?,?,?,?,?,?,?)";
            final PreparedStatement st = con.prepareStatement(sql);

            st.setInt(1, t.getUsersId());
            st.setString(2, t.getUsername());
            st.setString(3, t.getPassword());
            st.setString(4, t.getName());
            st.setString(5, t.getEmail());
            st.setString(6, t.getPhone());
            st.setString(7, t.getParentName());
            st.setString(8, t.getParentPhone());
            st.setDouble(9, t.getBalance());
            st.setString(10, t.getAvatar());

            ketQua = st.executeUpdate();
            System.out.println("B\u1ea1n \u0111\u00e3 th\u1ef1c thi: " + sql);
            System.out.println("C\u00f3 " + ketQua + " d\u00f2ng b\u1ecb thay \u0111\u1ed5i!");
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int update(Users t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE users  SET name=?, email=?,phone=?,parentName=?,parentPhone=? WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getName());
            st.setString(2, t.getEmail());
            st.setString(3, t.getPhone());
            st.setString(4, t.getParentName());
            st.setString(5, t.getParentPhone());
            st.setInt(6, t.getUsersId());
            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public Users selectByUserAndPassword(Users t) {
        Users ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM users WHERE username=? and password=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getUsername());
            st.setString(2, t.getPassword());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(11));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                ketQua = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), r2);
            }

        } catch (Exception e) {
        }
        return ketQua;
    }

    public Users selectByEmail(Users t) {
        Users ketQua = null;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM users WHERE email=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getEmail());
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(11));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                ketQua = new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), r2);
            }

        } catch (Exception e) {
        }
        return ketQua;
    }

    public boolean kiemTraTenDangNhap(final String username) {
        boolean ketQua = false;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "SELECT * FROM users WHERE username=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, username);
            System.out.println(sql);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ketQua = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int changePassword(Users t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE users  SET  password=? WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getPassword());
            st.setInt(2, t.getUsersId());
            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public int changePasswordByEmail(Users t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE users  SET  password=? WHERE email=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, t.getPassword());
            st.setString(2, t.getEmail());
            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public ArrayList<Users> getListUserInRoom(String roomId) {
        ArrayList<Users> list = new ArrayList<>();
        String query = "select * from Users where roomId = ?";
        try {

            Connection conn = new JDBCUtil().getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, roomId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Room r1 = new Room();
                r1.setRoomId(rs.getInt(11));
                Room r2 = new database.RoomDAO().getListRoomById(r1);
                list.add(new Users(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getString(10), r2));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public int updateRoom(Users t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE Users  SET  roomId=? WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, t.getRoomId().getRoomId());

            st.setInt(2, t.getUsersId());

            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

    public int resetSemester() {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE Users SET roomId = NULL";
            final PreparedStatement st = con.prepareStatement(sql);
            System.out.println(sql);
            ketQua = st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }
    public int checkOut(String t) {
        int ketQua = 0;
        try {
            final Connection con = JDBCUtil.getConnection();
            final String sql = "UPDATE Users  SET  roomId=NULL WHERE usersId=?";
            final PreparedStatement st = con.prepareStatement(sql);
    

            st.setString(1, t);

            System.out.println(sql);
            ketQua = st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ketQua;
    }

}
