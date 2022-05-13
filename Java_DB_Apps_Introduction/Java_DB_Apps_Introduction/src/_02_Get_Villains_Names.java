import java.sql.*;
import java.util.Properties;

public class _02_Get_Villains_Names {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/minions_db", properties);

        PreparedStatement preparedStatement = connection
                .prepareStatement("SELECT v.name, count(DISTINCT mv.minion_id) as count_minians " +
                        " FROM villains as v\n" +
                        " JOIN minions_villains as mv ON v.id = mv.villain_id\n" +
                        " GROUP BY v.name\n" +
                        " HAVING count_minians > 15\n" +
                        " order by count_minians desc;");

        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getString("v.name") + " "
                    + resultSet.getInt("count_minians"));
        }
        connection.close();
    }
}
