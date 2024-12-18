import model.CRUDOperations;
import model.EmployesService;

import java.io.*;
import java.util.Scanner;

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


        loadFiles(filePath);

        Scanner scanner = new Scanner(System.in);
//        System.out.println("Create new ID");
//        String newInputId = scanner.nextLine();
//        System.out.println("Create new Full Name");
//        String newInputFullName = scanner.nextLine();
//        System.out.println("Create new Email");
//        String newInputEmail = scanner.nextLine();
//        System.out.println("Create new User Name");
//        String newInputUserName = scanner.nextLine();
//        System.out.println("Create new Mac ");
//        String newInputMac = scanner.nextLine();
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=== CSV Data Manager ===");
            System.out.println("1. Create New Entry");
            System.out.println("2. Read All Entries");
            System.out.println("3. Update Entry");
            System.out.println("4. Delete Entry");
            System.out.println("5. Find Entry by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int options = scanner.nextInt();
            switch (options) {
                case 1:
                    System.out.println("Create new ID");
                    String newInputId = scanner.nextLine();
                    System.out.println("Create new Full Name");
                    String newInputFullName = scanner.nextLine();
                    System.out.println("Create new Email");
                    String newInputEmail = scanner.nextLine();
                    System.out.println("Create new User Name");
                    String newInputUserName = scanner.nextLine();
                    System.out.println("Create new Mac ");
                    String newInputMac = scanner.nextLine();
                    service.create(newInputId, newInputFullName, newInputEmail, newInputUserName, newInputMac);
                    System.out.println("New Entity CREATED: " + newInputId);
                    break;

                case 2:
                    service.readAll();
                    break;

                case 3:
                    System.out.print("Enter ID to update: ");
                    String updateId = scanner.nextLine();
                    System.out.print("Enter new Full Name: ");
                    String updateFullName = scanner.nextLine();
                    System.out.print("Enter new Email: ");
                    String updateEmail = scanner.nextLine();
                    System.out.print("Enter new User Name: ");
                    String updateUserName = scanner.nextLine();
                    System.out.print("Enter new MAC Address: ");
                    String updateMac = scanner.nextLine();
                    service.update(updateId, updateFullName, updateEmail, updateUserName, updateMac);
                    System.out.println("USER UPDATED: " + updateId);
                    break;

                case 4:
                    System.out.print("Enter ID to delete: ");
                    String deleteId = scanner.nextLine();
                    service.delete(deleteId);
                    System.out.println("USER DELETED: " + deleteId);
                    break;
                case 5:
                    System.out.println("Enter the you wanna looking for:");
                    scanner.nextLine(); //FIXED   Problem: scanner.next() --> the findId made into -1
                    String findId = scanner.nextLine();
                    service.printById(findId);
                    break;
                case 6:
                    System.out.println("Exit program");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
                    break;
            }

        }

    }

    private static void loadFiles(String filePath) {
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

    public void printById(String id_) {
        int index = findById(id_);
        System.out.println("-----------------UPDATED-----------------");
        System.out.println("ID: " + id[index]); //PROBLEM IN HERE!!!!!!
        System.out.println("Teljes név: " + full_name[index]);
        System.out.println("Email: " + email[index]);
        System.out.println("Felhasználó név: " + user_name[index]);
        System.out.println("Maccím: " + mac[index]);
    }

    @Override
    public void create(String id_, String fullName_, String email_, String userName_, String mac_) {
        int newArraySize = arraySize + 1;
        String[] newId = new String[newArraySize];
        String[] newFull_name = new String[newArraySize];
        String[] newEmail = new String[newArraySize];
        String[] newUser_name = new String[newArraySize];
        String[] newMac = new String[newArraySize];

        int newIndex = 0;

        for (int i = 0; i < arraySize; i++) {

            newId[newIndex] = id[i];
            newUser_name[newIndex] = user_name[i];
            newEmail[newIndex] = email[i];
            newFull_name[newIndex] = full_name[i];
            newMac[newIndex] = mac[i];
            newIndex++;
        }


        newId[arraySize] = id_;
        newUser_name[arraySize] = userName_;
        newEmail[arraySize] = email_;
        newFull_name[arraySize] = fullName_;
        newMac[arraySize] = mac_;


        id = newId;
        user_name = newUser_name;
        email = newEmail;
        full_name = newFull_name;
        mac = newMac;
        arraySize = newArraySize;


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
    public void update(String id_, String fullName_, String email_, String userName_, String mac_) {

        int index = findById(id_);

        isNull(fullName_);

        if (!isNull(fullName_)) {
            full_name[index] = fullName_;
        }
        if (!isNull(email_)) {
            email[index] = email_;
        }
        if (!isNull(userName_)) {
            user_name[index] = userName_;
        }
        if (!isNull(mac_)) {
            mac[index] = mac_;
        }
        System.out.println("-----------------UPDATED-----------------");
        System.out.println("ID: " + id[index]);
        System.out.println("Teljes név: " + full_name[index]);
        System.out.println("Email: " + email[index]);
        System.out.println("Felhasználó név: " + user_name[index]);
        System.out.println("Maccím: " + mac[index]);
    }

    private static boolean isNull(String input) {
        return (input == null || input.trim().isEmpty()); //!!!
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
                newId[newIndex] = id[i];
                newUser_name[newIndex] = user_name[i];
                newEmail[newIndex] = email[i];
                newFull_name[newIndex] = full_name[i];
                newMac[newIndex] = mac[i];
                newIndex++;
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
