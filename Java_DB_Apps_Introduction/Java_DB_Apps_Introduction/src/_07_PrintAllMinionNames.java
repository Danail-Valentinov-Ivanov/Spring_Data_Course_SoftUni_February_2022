import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class _07_PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );
        PreparedStatement selectStatement = connection.prepareStatement(
                "SELECT name FROM minions LIMIT 50"
        );
        ResultSet minionSet = selectStatement.executeQuery();
        List<String> minions = new ArrayList<>();
        while (minionSet.next()) {
            String minionName = minionSet.getString("name");
            minions.add(minionName);
        }
        for (int i = 0; i < minions.size() / 2; i++) {
            System.out.println(minions.get(i));
            System.out.println(minions.get(minions.size() - 1 - i));
        }
        if (minions.size() % 2 == 1) {
            System.out.println(minions.get(minions.size() / 2));
        }
        connection.close();
    }
}
