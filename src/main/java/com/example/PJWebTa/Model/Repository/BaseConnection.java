package com.example.PJWebTa.Model.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

@Repository
public class BaseConnection {
    static String url = "jdbc:mysql://localhost:3306/dbwebta";
    static String username = "root";
    static String password = "241003";
    static String nameClass = "com.mysql.cj.jdbc.Driver";

    public static void main(String[] args) {
    String url = "jdbc:mysql://localhost:3306/DBWebTA";
    String username = "root";
    String password = "241003";
    try (
    Connection connection = DriverManager.getConnection(url, username, password))
    {
    if (connection != null) {
    System.out.println("Kết nối thành công!");
    } else {
    System.out.println("Kết nối thất bại!");
    }
    } catch (SQLException e) {
    System.out.println("Lỗi kết nối: " + e.getMessage());
    }
    }
    
}
