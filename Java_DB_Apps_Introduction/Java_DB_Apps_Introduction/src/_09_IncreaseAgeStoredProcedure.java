import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _09_IncreaseAgeStoredProcedure {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );

        Scanner scanner = new Scanner(System.in);
        int id = Integer.parseInt(scanner.nextLine());
        CallableStatement updateAge = connection.prepareCall(
                "CALL usp_get_older(?)"
        );
        updateAge.setInt(1, id);
        updateAge.executeQuery();

        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT name, age FROM minions WHERE id = ?"
        );
        selectStatement.setInt(1, id);
        ResultSet minionSet = selectStatement.executeQuery();
        minionSet.next();
        String name = minionSet.getString("name");
        int age = minionSet.getInt("age");
        System.out.println(name + " " + age);
    }
}
