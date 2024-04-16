package org.example;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    Jdbc jdbc;
    
    public Program() throws SQLException, IOException {
        jdbc = new Jdbc();
    }

    public void startProgram() throws SQLException {
        mainMenu();
    }
    
    private void mainMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Show all contact");
            System.out.println("2. Add a new contact");
            System.out.println("3. Update a contact");
            System.out.println("4. Delete a contact");
            System.out.println("5. Exit program");
            
            String input = scanner.nextLine();
            switch (input) {
                case "1": {
                    showAllContactsMenu();
                    break;
                }
                case "2": {
                    addContactMenu();
                    break;
                }
                case "3": {
                    updateContactMenu();
                    break;
                }
                case "4": {
                    deleteContactMenu();
                    break;
                }
                case "5": {
                    System.out.println("Exiting program");
                    return;
                }
                default: {
                    System.out.println("Invalid input");
                    break;
                }
            }
        }
    }


    private void showAllContactsMenu() throws SQLException {
        ArrayList<PhoneContact> phoneContacts = jdbc.getAllContacts();
        for (int i = 0; i < phoneContacts.size(); i++) {
            System.out.println((i + 1) + ". " + phoneContacts.get(i));
        }

        System.out.println("Press enter to return");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
    
    private void addContactMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Adding new contact:");
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter number:");
        String number = scanner.nextLine();
        
        PhoneContact newContact = new PhoneContact(name, number);
        jdbc.addContact(newContact);
        System.out.println("Contact added!");
    }
    
    private void updateContactMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<PhoneContact> phoneContacts = jdbc.getAllContacts();
        for (int i = 0; i < phoneContacts.size(); i++) {
            System.out.println((i + 1) + ". " + phoneContacts);
        }
        
        System.out.println("Please select an contact to edit");
        PhoneContact editPhoneContact;
        while (true) {
            try {
                int number = scanner.nextInt();
                number--;
                scanner.nextLine();
                editPhoneContact = phoneContacts.get(number);
                break;
            } catch (Exception e){
                System.out.println("Please enter a valid number");
            }
        }
        
        System.out.println("Adding updated information");
        System.out.println("Enter name:");
        String name = scanner.nextLine();
        System.out.println("Enter number:");
        String number = scanner.nextLine();
    
        PhoneContact updatedContact = new PhoneContact(name, number);
        jdbc.updateContact(updatedContact, editPhoneContact);
        System.out.println("Contact updated!");
    }
    
    private void deleteContactMenu() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        ArrayList<PhoneContact> phoneContacts = jdbc.getAllContacts();
        for (int i = 0; i < phoneContacts.size(); i++) {
            System.out.println((i + 1) + ". " + phoneContacts);
        }

        System.out.println("Please select an contact to delete");
        PhoneContact deletedContact;
        while (true) {
            try {
                int number = scanner.nextInt();
                number--;
                scanner.nextLine();
                deletedContact = phoneContacts.get(number);
                break;
            } catch (Exception e){
                System.out.println("Please enter a valid number");
            }
        }
        jdbc.deleteContact(deletedContact);
        System.out.println("Contact deleted!");
    }

        
}
