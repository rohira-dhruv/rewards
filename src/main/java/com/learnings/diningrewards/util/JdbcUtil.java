package com.learnings.diningrewards.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {

  public static void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
    if (rs != null) {
      try {
        rs.close();
      } catch (SQLException ignored) {
      }
    }
    if (ps != null) {
      try {
        ps.close();
      } catch (SQLException ignored) {
      }
    }
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException ignored) {
      }
    }
  }
}
