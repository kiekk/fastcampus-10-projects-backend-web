package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    public void create(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.executeUpdate(user, "INSERT INTO USERS VALUES (?, ?, ?, ?)", pstmt -> {
            pstmt.setString(1, user.getUserId());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getName());
            pstmt.setString(4, user.getEmail());
        });
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT * FROM USERS WHERE userId = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
            return user;
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {

                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (Exception e) {

                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
