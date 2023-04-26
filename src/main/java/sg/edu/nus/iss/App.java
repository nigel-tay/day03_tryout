package sg.edu.nus.iss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws IOException {
        // Start program with welcome message
        System.out.println("Welcome to your cart thing 🥵");
        // Variables
        List<String> fruitList = new ArrayList<String>();
        Scanner scan = null;
        String commandGiven = "";
        String input = "";
        List<String> listOfFruits = null;
        int givenIndex = 0;
        String loginName = "";
        String dirName = args.length == 0 ?  "db" : args[0];
        File dirFileObj = new File(dirName);
        String dirFileName = "";
        File newDirFileName = null;

        // Write file variables
        FileWriter fw = null;
        BufferedWriter bw = null;

        // Initialise scanner
        scan = new Scanner(System.in);

        try {

            while (!(commandGiven.equals("quit"))) {
                System.out.print("> ");
                commandGiven = scan.next();
                // List
                if (commandGiven.equals("list")) {
                    // If list empty print empty message
                    if (!(fruitList.size() > 0)) {
                        System.out.println("Your cart is empty 😞");
                    }
                    // If not list all items in list
                    else {
                        for (int i = 0; i < fruitList.size(); i++) {
                            System.out.printf("%d. %s\n", i+1, fruitList.get(i));
                        }
                    }
                }

                // Add
                if (commandGiven.equals("add")) {
                    // Add everything after first index
                        input = scan.nextLine();
                        System.out.println("Input:" + input);
                        listOfFruits = new ArrayList<String>();
                        listOfFruits = Arrays.asList(input.replaceAll("\\s+","").split(","));
                        for (int i = 0; i < listOfFruits.size(); i++) {
                            // If item already exists, print exist messsage and add the rest of the items
                            // that are not in the list
                            if (fruitList.contains(listOfFruits.get(i))) {
                                System.out.println(listOfFruits.get(i) + " is already in cart");
                            } else {
                                fruitList.add(listOfFruits.get(i));
                                System.out.println(listOfFruits.get(i) + " added!");
                            }
                        }

                }

                // Delete
                if (commandGiven.equals("delete")) {
                    givenIndex = Integer.parseInt(scan.next()) - 1;
                    // If index given is greater than list size, print error message
                    if (givenIndex > fruitList.size() - 1 || givenIndex < 0) {
                        System.out.println("The given index is out of bounds, try a number within the scope of this list 😠");
                    }
                    else {
                        // Delete item at specified index
                        System.out.println(fruitList.get(givenIndex) + " deleted from cart 🛒");
                        fruitList.remove(givenIndex);
                    }
                }
                // login
                if (commandGiven.equals("login")) {
                    loginName = scan.next();
                    // Check if argument is given
                    if (dirName.isEmpty()) { // Use db directory
                        checkFileExists(dirFileObj); // Checks if directory exists, if not create the directory
                        dirFileName = dirName + File.separator + loginName;
                        newDirFileName = new File(dirFileName);
                        saveFile(newDirFileName);
                    }
                    else { // Use cartdb directory
                        checkFileExists(dirFileObj); // Checks if directory exists, if not create the directory
                        dirFileName = dirName + File.separator + loginName;
                        newDirFileName = new File(dirFileName);
                        saveFile(newDirFileName);
                    }
                }

                // Save
                if (commandGiven.equals("save")) {
                    // Check logged in user exists or not, no? Ask user to login
                    if (newDirFileName == null) {
                        System.out.println("Login first!");
                    }
                    else {
                        fw = new FileWriter(newDirFileName);
                        bw = new BufferedWriter(fw);
                        for (String fruit: fruitList) {
                            bw.write(fruit + "\n");
                            bw.flush();
                        }
                        System.out.println("Your cart has been saved!");
                    }
                }
            }

        } catch (Exception e) {
            scan.close();
            System.out.println(e);
        }
        finally {
            scan.close();
            bw.close();
            fw.close();
        }
    }

    public static void checkFileExists(File dirObj) {
        if (!(dirObj.exists())) {
            dirObj.mkdir();
        }
    }

    public static void saveFile(File fileObj) throws IOException {
        if (fileObj.exists()) {
            System.out.println("Logged in as " + fileObj);
        }
        else {
            // Create file in db folder and save content of fruitList into db/loginName
            fileObj.createNewFile();
            System.out.println("Created log in profile " + fileObj);
        }
    }
}
