import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {



        /*
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

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
        }*

         */
    }
}