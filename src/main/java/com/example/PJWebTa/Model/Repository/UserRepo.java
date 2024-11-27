package com.example.PJWebTa.Model.Repository;

import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.PJWebTa.Model.Entity.User;

public class UserRepo {
        // ADD
        public static void AddUsers(User user) throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "insert into `user` (user_name, user_email, user_level, user_datejoined, user_role, user_type, user_picture, username, `password`) values (?,?,?,?,?,?,?,?,?)");
                ps.setString(1, user.getUserName());
                ps.setString(2, user.getUserEmail());
                ps.setInt(3, user.getUserLevel());
                ps.setDate(4, user.getUserDateJoined());
                ps.setString(5, user.getUserRole());
                ps.setInt(6, user.getUser_type());
                ps.setString(7, user.getUserPicture());
                ps.setString(8, user.getUsernameA());
                ps.setString(9, user.getPasswordA());
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // UPDATE USER
        public static void UpdateUsers(int userID, String userName, String userEmail, int userLevel,
                        Date userDateJoined, String userRole, int userType, String userPicture)
                        throws Exception {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement(
                                "update `user` set user_name=?, user_email=?, user_level=?,user_datejoined=?,user_role=?,user_type=?, user_picture= ? where user_id=?;");
                ps.setString(1, userName);
                ps.setString(2, userEmail);
                ps.setInt(3, userLevel);
                ps.setDate(4, userDateJoined);
                ps.setString(5, userRole);
                ps.setInt(6, userType);
                ps.setString(7, userPicture);
                ps.setInt(8, userID);
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // UPDATE PASSWORD
        public static void UpdatePassword(String usernameA, String passwordA, int userID)
                        throws ClassNotFoundException, SQLException {
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("update `user` set `password` = ? where user_id = ?;");
                ps.setString(1, usernameA);
                ps.setString(2, passwordA);
                ps.setInt(3, userID);
                ps.executeUpdate();
                con.close();
                ps.close();
        }

        // SHOW ALL USER with All INFORMATION (ADMIN)
        public ArrayList<User> ShowAllUsers() throws ClassNotFoundException, SQLException {
                ArrayList<User> allUser = new ArrayList<>();
                Class.forName(BaseConnection.nameClass);
                Connection con = DriverManager.getConnection(BaseConnection.url, BaseConnection.username,
                                BaseConnection.password);
                PreparedStatement ps = con.prepareStatement("select * from `user`;");
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                        int userID = rs.getInt("user_id");
                        String userName = rs.getString("user_name");
                        String userEmail = rs.getString("user_email");
                        int userLevel = rs.getInt("user_level");
                        Date userDateJoined = rs.getDate("user_datejoined");
                        String userRole = rs.getString("user_role");
                        int userType = rs.getInt("user_type");
                        String userPicture = rs.getString("user_picture");
                        String usernameA = rs.getString("username");
                        String passwordA = rs.getString("`password`");
                        User user = new User(userID, userName, userEmail, userLevel, userDateJoined, userRole, userID,
                                        userPicture, usernameA, passwordA);
                        allUser.add(user);
                }
                con.close();
                rs.close();
                ps.close();
                return allUser;
        }

        // TÌM THEO TÊN 
        public 

}
