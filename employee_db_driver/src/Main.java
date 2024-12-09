import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String filePath = "";

        int arraySize = 0;
        String[] id = null;
        String[] full_name = null;
        String[] email = null;
        String[] user_name = null;
        String[] mac = null;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            if ((line = br.readLine()) != null) {

                arraySize = Integer.parseInt(line.trim());

                id = new String[arraySize];
                full_name = new String[arraySize];
                email = new String[arraySize];
                user_name = new String[arraySize];
                mac = new String[arraySize];
            }

            int index = 0;
            while ((line = br.readLine()) != null && index < arraySize) {

                String[] values = line.split(",");

                if (values.length >= 6) {
                    id[index] = values[0];           // First column: ID
                    full_name[index] = values[1];   // Second column: First Name
                    email[index] = values[2];        // Fourth column: Email
                    user_name[index] = values[3];       // Fifth column: Gender
                    mac[index] = values[4];   // Sixth column: IP Address
                    index++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        // Print the arrays (for testing purposes)
        System.out.println("ID: " + Arrays.toString(id));
        System.out.println("First Name: " + Arrays.toString(full_name));
        System.out.println("Email: " + Arrays.toString(email));
        System.out.println("Gender: " + Arrays.toString(user_name));
        System.out.println("IP Address: " + Arrays.toString(mac));
    }
}
