import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class _04_AddMinion {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );
        Scanner scanner = new Scanner(System.in);
        String[] minionData = scanner.nextLine().split(" ");
        String minionName = minionData[1];
        int minionAge = Integer.parseInt(minionData[2]);
        String minionTown = minionData[3];
        String villainName = scanner.nextLine().split(" ")[1];

        int townId = getOrInsertTown(connection, minionTown);
        int villainId = getOrInsertVillainName(connection, villainName);

        PreparedStatement insertMinian = connection.prepareStatement(
                "INSERT INTO minions(name, age, town_id) VALUES(?, ?, ?)"
        );
        insertMinian.setString(1, minionName);
        insertMinian.setInt(2, minionAge);
        insertMinian.setInt(3, townId);
        insertMinian.executeUpdate();

        PreparedStatement lastMinianId = connection.prepareStatement(
                "SELECT id FROM minions ORDER BY id DESC LIMIT 1"
        );
        ResultSet lastIdSet = lastMinianId.executeQuery();
        lastIdSet.next();
        int minianId = lastIdSet.getInt("id");

        PreparedStatement insertMinianIdVillainId = connection.prepareStatement(
                "INSERT INTO minions_villains values(?, ?)"
        );
        insertMinianIdVillainId.setInt(1, minianId);
        insertMinianIdVillainId.setInt(2, villainId);
        insertMinianIdVillainId.executeUpdate();

        System.out.printf("Successfully added %s to be minion of %s.\n", minionName, villainName);
        connection.close();
    }

    private static int getOrInsertVillainName(Connection connection, String villainName) throws SQLException {
        PreparedStatement villainStatement = connection.prepareStatement(
                "SELECT id FROM villains WHERE name = ?"
        );
        villainStatement.setString(1, villainName);
        ResultSet villainSet = villainStatement.executeQuery();

        int villainId = 0;
        if (!villainSet.next()){
            PreparedStatement insertVillain = connection.prepareStatement(
                    "INSERT INTO villains(name, evilness_factor) VALUES(?, ?)"
            );
            insertVillain.setString(1, villainName);
            insertVillain.setString(2, "evil");
            insertVillain.executeUpdate();
            ResultSet newVillainSet = villainStatement.executeQuery();
            newVillainSet.next();
            villainId = newVillainSet.getInt("id");
            System.out.printf("Villain %s was added to the database.\n", villainName);
        } else {
            villainId = villainSet.getInt("id");
        }
        return villainId;
    }

    private static int getOrInsertTown(Connection connection, String minionTown) throws SQLException {
        PreparedStatement townStatement = connection.prepareStatement(
                "SELECT id FROM towns\n" +
                        " WHERE name = ?"
        );
        townStatement.setString(1, minionTown);
        ResultSet townSet = townStatement.executeQuery();

        int townId = 0;
        if (!townSet.next()){
            PreparedStatement insertTown = connection.prepareStatement(
                    "INSERT INTO towns(name) VALUES(?)"
            );
            insertTown.setString(1, minionTown);
            insertTown.executeUpdate();
            ResultSet insertTownSet = townStatement.executeQuery();
            insertTownSet.next();
            townId = insertTownSet.getInt("id");
            System.out.printf("Town %s was added to the database.\n", minionTown);
        } else {
            townId = townSet.getInt("id");
        }
        return townId;
    }
}
