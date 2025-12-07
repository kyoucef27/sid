import AddressBookApp.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Scanner;

public class Client {
    static AddressBook addressBookImpl;
    
    public static void main(String args[]) {
        try {
            // Initialize the ORB
            ORB orb = ORB.init(args, null);
            
            // Get the root naming context
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            
            // Resolve the Object Reference in Naming
            String name = "AddressBook";
            addressBookImpl = AddressBookHelper.narrow(ncRef.resolve_str(name));
            
            System.out.println("Obtained a handle on server object: " + addressBookImpl);
            System.out.println("===== CORBA Address Book Client =====\n");
            
            Scanner scanner = new Scanner(System.in);
            boolean running = true;
            
            while (running) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Add Contact");
                System.out.println("2. Remove Contact");
                System.out.println("3. Get Contact");
                System.out.println("4. List All Contacts");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                
                switch (choice) {
                    case 1:
                        addContact(scanner);
                        break;
                    case 2:
                        removeContact(scanner);
                        break;
                    case 3:
                        getContact(scanner);
                        break;
                    case 4:
                        listContacts();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }
            
            scanner.close();
        } catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }
    }
    
    private static void addContact(Scanner scanner) {
        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter phone: ");
            String phone = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            
            Contact contact = new Contact(name, phone, email);
            addressBookImpl.addContact(contact);
            System.out.println("Contact added successfully!");
        } catch (DuplicateContact e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error adding contact: " + e.getMessage());
        }
    }
    
    private static void removeContact(Scanner scanner) {
        try {
            System.out.print("Enter name to remove: ");
            String name = scanner.nextLine();
            
            addressBookImpl.removeContact(name);
            System.out.println("Contact removed successfully!");
        } catch (ContactNotFound e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error removing contact: " + e.getMessage());
        }
    }
    
    private static void getContact(Scanner scanner) {
        try {
            System.out.print("Enter name to search: ");
            String name = scanner.nextLine();
            
            Contact contact = addressBookImpl.getContact(name);
            System.out.println("\n--- Contact Found ---");
            System.out.println("Name: " + contact.name);
            System.out.println("Phone: " + contact.phone);
            System.out.println("Email: " + contact.email);
        } catch (ContactNotFound e) {
            System.out.println("Error: " + e.reason);
        } catch (Exception e) {
            System.out.println("Error getting contact: " + e.getMessage());
        }
    }
    
    private static void listContacts() {
        try {
            Contact[] contacts = addressBookImpl.listContacts();
            
            if (contacts.length == 0) {
                System.out.println("No contacts in the address book.");
            } else {
                System.out.println("\n--- Contact List ---");
                for (int i = 0; i < contacts.length; i++) {
                    System.out.println("\nContact " + (i + 1) + ":");
                    System.out.println("  Name: " + contacts[i].name);
                    System.out.println("  Phone: " + contacts[i].phone);
                    System.out.println("  Email: " + contacts[i].email);
                }
            }
        } catch (Exception e) {
            System.out.println("Error listing contacts: " + e.getMessage());
        }
    }
}
