import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class _05_ChangeTownNamesCasing {
    public static void main(String[] args) throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "8404");

        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/minions_db", properties
        );

        Scanner scanner = new Scanner(System.in);
        String countryName = scanner.nextLine();

        PreparedStatement updateCityStatement = connection.prepareStatement(
                "UPDATE towns SET name = upper(name) WHERE country = ?"
        );
        updateCityStatement.setString(1, countryName);
        int countUpdatedCities = updateCityStatement.executeUpdate();

        if (countUpdatedCities == 0){
            System.out.println("No town names were affected.");
            return;
        }
        ResultSet citySet = getResultSet(connection, countryName);

        List<String> cityNames = new ArrayList<>();
        while (citySet.next()){
            String city = citySet.getString("name");
            cityNames.add(city);
        }
        System.out.printf("%d town names were affected.\n", cityNames.size());
        System.out.println(cityNames);
        connection.close();
    }

    private static ResultSet getResultSet(Connection connection, String countryName) throws SQLException {
        PreparedStatement getCityStatement = connection.prepareStatement(
                "SELECT name FROM towns WHERE country = ?"
        );
        getCityStatement.setString(1, countryName);
        ResultSet citySet = getCityStatement.executeQuery();
        return citySet;
    }
}
