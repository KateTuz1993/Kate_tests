package ru.tests;


import org.testng.annotations.Test;
import ru.models.GroupData;
import ru.models.Groups;

import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select group_id, group_name, group_header, group_footer  from group_list");
            Groups groups = new Groups();
            while (resultSet.next()){
                groups.add(new GroupData().withId(resultSet.getInt("group_id")).withName(resultSet.getString("group_name"))
                        .withHeader(resultSet.getString("group_header")).withFooter(resultSet.getString("group_footer")));

            }
            resultSet.close();
            st.close();
            conn.close();
            System.out.println(groups);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}