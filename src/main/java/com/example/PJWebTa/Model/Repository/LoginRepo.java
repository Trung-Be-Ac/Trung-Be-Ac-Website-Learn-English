package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.example.PJWebTa.Model.Entity.User;

@Repository
public class LoginRepo {
        // Check Login User
        public static User CheckLogin(String usernameA, String passwordA)
                        throws SQLException, ClassNotFoundException {
                ArrayList<User> allUsers = new ArrayList<>();
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "select * from `user` where username = ? and `password` = ?  ");
                ps.setString(1, usernameA);
                ps.setString(2, passwordA);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                        int userID = rs.getInt("user_id");
                        String userName = rs.getString("user_name");
                        String userEmail = rs.getString("user_email");
                        int userLevel = rs.getInt("user_level");
                        String userRole = rs.getString("user_role");
                        Date userDateJoined = rs.getDate("user_datejoined");
                        int userType = rs.getInt("user_type");
                        String userPicture = rs.getString("user_picture");
                        User user = new User(userID, userName, userEmail, userLevel, userDateJoined, userRole, userType,
                                        userPicture, usernameA, passwordA);
                        allUsers.add(user);
                        con.close();
                        rs.close();
                        ps.close();
                        return user;

                }
                return null;

        }

        // UPDATE PASSWORD
        public static void UpdatePassword(int userID, String passwordA)
                        throws ClassNotFoundException, SQLException {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("update `user` set `password` = ? where user_id = ?;");
                ps.setString(1, passwordA);
                ps.setInt(2, userID);
                ps.executeUpdate();
                con.close();
                ps.close();
        }
}
