import model.CRUDOperations;

import java.io.*;

//implement part 1
public class Main implements CRUDOperations {
    static int arraySize = 0;
    static String[] id = null;
    static String[] full_name = null;
    static String[] email = null;
    static String[] user_name = null;
    static String[] mac = null;


    public static void main(String[] args) {

        Main service = new Main();
        //employee_db_driver elérési útvonalát kell beleírni!
        String filePath = "";


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

                if (values.length >= 5) {
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


    }

    @Override
    public void create(String id, String fullName, String email, String userName, String mac) {

    }

    @Override
    public void readAll() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("----------------------------------");
            System.out.println("ID: " + id[i]);
            System.out.println("Teljes név: " + full_name[i]);
            System.out.println("Email: " + email[i]);
            System.out.println("Felhasználó név: " + user_name[i]);
            System.out.println("Maccím: " + mac[i]);

        }

    }

    @Override
    public void update(String id, String fullName, String email, String userName, String mac) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void findById(String id_) {

        int n = arraySize;
        for (int i = 0; i < n - 1; i++) { // TODO: n - 1 !!!!!!!!!!
            for (int j = 0; j < n - i - 1; j++) {
                if (id[j].compareTo(id[j + 1]) > 0) {
                    String temp = id[j];
                    id[j] = id[j + 1]; //Swap array[ j]and array[ j + 1]
                    id[j + 1] = temp;

                    temp = full_name[j];
                    full_name[j] = full_name[j + 1]; //Swap array[ j]and array[ j + 1]
                    full_name[j + 1] = temp;

                    temp = email[j];
                    email[j] = email[j + 1]; //Swap array[ j]and array[ j + 1]
                    email[j + 1] = temp;

                    temp = user_name[j];
                    user_name[j] = user_name[j + 1]; //Swap array[ j]and array[ j + 1]
                    user_name[j + 1] = temp;

                    temp = mac[j];
                    mac[j] = mac[j + 1]; //Swap array[ j]and array[ j + 1]
                    mac[j + 1] = temp;

                }
            }
        }
    }
}
