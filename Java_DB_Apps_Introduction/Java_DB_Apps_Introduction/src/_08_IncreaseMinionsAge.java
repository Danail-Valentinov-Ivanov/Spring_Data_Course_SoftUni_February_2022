import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _08_IncreaseMinionsAge {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );

        Scanner scanner = new Scanner(System.in);
        List<String> input = Arrays.stream(scanner.nextLine().split(" "))
                .collect(Collectors.toList());
        String inputIds = String.join(", ", input);
        PreparedStatement updateTable = connection.prepareStatement(
                "UPDATE minions SET age = age + 1, name = lower(name) WHERE id IN(" + inputIds + ")"
        );
        updateTable.executeUpdate();

        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT name, age FROM minions"
        );
        ResultSet minionSet = selectStatement.executeQuery();
        while (minionSet.next()) {
            String name = minionSet.getString("name");
            int age = minionSet.getInt("age");
            System.out.println(name + " " + age);
        }
        connection.close();
    }
}
