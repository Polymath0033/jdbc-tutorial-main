package com.jetbrains.marco;

import javax.sql.DataSource;
import java.sql.*;

public  class JdbcTutorial{
    public static void main(final String[] args) {
        try {
           Connection connection = DriverManager.getConnection("jdbc:h2:mem:;INIT=RUNSCRIPT FROM 'classpath:users.sql'");
           // System.out.println("Connection.isValid(0) = " + connection.isValid(0));
            PreparedStatement ps = connection.prepareStatement("select * from USERS where name = ?");
            ps.setString(1, "Marco");
            ResultSet resultSet  = ps.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("name"));
            }
            //insert
            PreparedStatement insertPs = connection.prepareStatement("insert into USERS (name) values (?)");
            insertPs.setString(1, "John");
            int count  = insertPs.executeUpdate();
            System.out.println("insertCount ="+ count);

            //update
            PreparedStatement updatePs = connection.prepareStatement("update users set name=? where name=?");
            updatePs.setString(1, "Johnny");
            updatePs.setString(2, "John");
            int countUpdate = updatePs.executeUpdate();
            System.out.println("updateCount ="+ countUpdate);

            //delete
            PreparedStatement deletePs = connection.prepareStatement("delete from users where name=?");
            deletePs.setString(1, "Johnny");
            int countDelete = deletePs.executeUpdate();
            System.out.println("deleteCount ="+ countDelete);

            ps.close();
        }catch (final SQLException e) {
            e.printStackTrace();
        }
    }
    private static DataSource getDataSource() {
        //
    }
}