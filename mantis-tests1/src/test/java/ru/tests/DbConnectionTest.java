package ru.tests;


import org.testng.annotations.Test;
import ru.models.AllUsers;
import ru.models.UserData;


import java.sql.*;

public class DbConnectionTest {

    @Test
    public void testDbConnection() {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bugtracker?serverTimezone=UTC&user=root&password=");
            Statement st = conn.createStatement();
            ResultSet resultSet = st.executeQuery("select id, username, email, password from mantis_user_table");
            AllUsers users = new AllUsers();
            while (resultSet.next()){
                users.add(new UserData().withId(resultSet.getInt("id")).withUsername(resultSet.getString("username"))
                        .withEmail(resultSet.getString("email")));

            }
            resultSet.close();
            st.close();
            conn.close();

            System.out.println(users);

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

    }
}
