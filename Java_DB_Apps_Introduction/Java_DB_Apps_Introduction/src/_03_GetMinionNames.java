import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _03_GetMinionNames {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());

        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?"
        );
        villainStatement.setInt(1, villainId);
        ResultSet villainResultSet = villainStatement.executeQuery();

        if (!villainResultSet.next()){
            System.out.printf("No villain with ID %d exists in the database.", villainId);
            return;
        }
        String villainName = villainResultSet.getString("name");
        System.out.printf("Villain: %s\n", villainName);

        PreparedStatement minianStatement = connection.prepareStatement(
                "SELECT m.name, m.age FROM villains as v\n" +
                        " JOIN minions_villains as mv ON v.id = mv.villain_id\n" +
                        " join minions as m on m.id = mv.minion_id\n" +
                        " where v.id = ?;"
        );
        minianStatement.setInt(1, villainId);
        ResultSet minianResultSet = minianStatement.executeQuery();

        int count = 1;
        while (minianResultSet.next()){
            String minianName = minianResultSet.getString("m.name");
            int minianAge = minianResultSet.getInt("m.age");
            System.out.printf("%d. %s %d\n", count, minianName, minianAge);
            count++;
        }
        connection.close();
    }
}
