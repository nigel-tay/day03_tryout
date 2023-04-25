package sg.edu.nus.iss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Start program with welcome message
        System.out.println("Welcome to your cart thing 🥵");
        // Variables
        List<String> fruitList = new ArrayList<String>();
        Scanner scan = null;
        String commandGiven = "";
        String input = "";
        List<String> listOfFruits = null;
        int givenIndex = 0;

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
                    if (givenIndex > fruitList.size() - 1 || givenIndex < 0) {
                        System.out.println("The given index is out of bounds, try a number within the scope of this list 😠");
                    }
                    else {
                        // Delete item at specified index
                        System.out.println(fruitList.get(givenIndex) + " deleted from list 🛒");
                        fruitList.remove(givenIndex);
                        // If index given is greater than list size, print error message
                    }
                }
                // Save
                // if (commandGiven == "save") {

                // }
            }

        } catch (Exception e) {
            scan.close();
            System.out.println(e);
        }
        finally {
            scan.close();
        }
    }
}
