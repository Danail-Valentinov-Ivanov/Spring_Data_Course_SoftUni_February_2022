import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _06_RemoveVillain {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );

        Scanner scanner = new Scanner(System.in);
        int villainId = Integer.parseInt(scanner.nextLine());
        PreparedStatement selectVillain = connection.prepareStatement(
                "SELECT name FROM villains WHERE id = ?"
        );
        selectVillain.setInt(1, villainId);
        ResultSet villainSet = selectVillain.executeQuery();

        if (!villainSet.next()) {
            System.out.println("No such villain was found");
            return;
        }
        String villainName = villainSet.getString("name");

        PreparedStatement countMinionStatement = connection.prepareStatement(
                "SELECT count(minion_id) as count_minion FROM minions_villains WHERE villain_id = ?"
        );
        countMinionStatement.setInt(1, villainId);
        ResultSet countMinionSet = countMinionStatement.executeQuery();
        countMinionSet.next();
        int countReleasedMinions = countMinionSet.getInt("count_minion");

        connection.setAutoCommit(false);

        try {
            PreparedStatement deleteMinionVillain = connection.prepareStatement(
                    "DELETE FROM minions_villains WHERE villain_id = ?"
            );
            deleteMinionVillain.setInt(1, villainId);
            deleteMinionVillain.executeUpdate();

            PreparedStatement deleteVillain = connection.prepareStatement(
                    "DELETE FROM villains WHERE id = ?"
            );
            deleteVillain.setInt(1, villainId);
            deleteVillain.executeUpdate();

            connection.commit();

            System.out.printf("%s was deleted\n", villainName);
            System.out.printf("%d minions released", countReleasedMinions);
        } catch (SQLException exception) {
            exception.printStackTrace();
            connection.rollback();
        }
        connection.close();
    }
}
