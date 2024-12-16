import model.CRUDOperations;
import model.EmployesService;

import java.io.*;

//implement part 1
public class Main implements CRUDOperations, EmployesService {
    static int arraySize = 0;
    static String[] id = null;
    static String[] full_name = null;
    static String[] email = null;
    static String[] user_name = null;
    static String[] mac = null;


    public static void main(String[] args) {

        Main service = new Main();
        String filePath = args[0];


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
        //  System.out.println(full_name[service.findById("67778132")]);
        //  System.out.println(mac[service.findById("67778132")]);
        //  System.out.println(service.changeMacAddress(mac[service.findById("67778132")]));
        //  service.changeAllMacAddress(mac);
         service.delete("67778132"); //When you Run the code test it with CTRL+f=67778132
        // service.findById("67778132");
         service.readAll();

    }

    @Override
    public void create(String id, String fullName, String email, String userName, String mac) {

    }

    @Override
    public void readAll() {
        for (int i = 0; i < arraySize; i++) {
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
    public void delete(String id_) {

        int newArraySize = arraySize - 1;
        String[] newId = new String[newArraySize];
        String[] newFull_name = new String[newArraySize];
        String[] newEmail = new String[newArraySize];
        String[] newUser_name = new String[newArraySize];
        String[] newMac = new String[newArraySize];


        int index = findById(id_);
        int newIndex = 0;

        for (int i = 0; i < arraySize; i++) {
            if (i != index) {
                //Problem: newId[newIndex++] = id[i]; következő indexre kerül ,ezért lépi túl a tömböt
                newId[newIndex] = id[i];
                newUser_name[newIndex] = user_name[i];
                newEmail[newIndex] = email[i];
                newFull_name[newIndex] = full_name[i];
                newMac[newIndex] = mac[i];
                newIndex++; //My solution
            }
        }
        id = newId;
        user_name = newUser_name;
        email = newEmail;
        full_name = newFull_name;
        mac = newMac;
        arraySize = newArraySize;

    }


    @Override
    public int findById(String id_) {

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
        int low = 0;
        int high = arraySize - 1;

        while (low <= high) {
            int middle = low + (high - low) / 2;
            int comparison = id[middle].compareTo(id_);
            if (comparison == 0) {
                return middle;
            } else if (comparison < 0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        // Target not found
        return -1;
    }

    @Override
    public String changeMacAddress(String mac) {

        //mac.toCharArray()
        char[] newMac = new char[mac.length()];
        for (int i = 0; i < mac.length(); i++) {
            if (mac.charAt(i) == '-') {
                newMac[i] = ':';

            } else {
                newMac[i] = mac.charAt(i);
            }
        }
        return new String(newMac);
    }

    @Override
    public void changeAllMacAddress(String[] addresses) {

        for (int i = 0; i < addresses.length; i++) {

            addresses[i] = changeMacAddress(addresses[i]);

        }

    }
}
