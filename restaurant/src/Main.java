import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


        /*
        String dbURL = "jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2277_restaurant";
        String dbName = "std_2277_restaurant";
        String dbPasswd = "restaurant2052";

        MeasurementUnitDAO mDAO = new MeasurementUnitDAO(dbURL, dbName, dbPasswd);
        List<MeasurementUnit> list = mDAO.listAllUnits();

        for (MeasurementUnit mUnit: list) {
            System.out.println(mUnit.getUnit());
        } */

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //"jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2277_restaurant"
            Connection connection = DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_2277_restaurant",
                    "std_2277_restaurant", "restaurant2052");

            Statement statement = connection.createStatement();
            String query = "SELECT * FROM measurementUnits";
            ResultSet result = statement.executeQuery(query);

            while (result.next()) {
                int id = result.getInt("id");
                String unit = result.getString("unit");

                System.out.println(id +  " " + unit);

            }

            connection.close();

        }
        catch (Exception e) {
            System.out.println(e);
        }




    }
}