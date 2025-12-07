import AddressBookApp.*;
import java.util.*;

public class AddressBookPOAImpl extends AddressBookPOA {
    private Map<String, Contact> contacts;
    
    public AddressBookPOAImpl() {
        contacts = new HashMap<String, Contact>();
    }
    
    @Override
    public void addContact(Contact contact) throws DuplicateContact {
        if (contacts.containsKey(contact.name)) {
            throw new DuplicateContact("Contact with name '" + contact.name + "' already exists.");
        }
        contacts.put(contact.name, contact);
        System.out.println("Contact added: " + contact.name);
    }
    
    @Override
    public void removeContact(String name) throws ContactNotFound {
        if (!contacts.containsKey(name)) {
            throw new ContactNotFound("Contact with name '" + name + "' not found.");
        }
        contacts.remove(name);
        System.out.println("Contact removed: " + name);
    }
    
    @Override
    public Contact getContact(String name) throws ContactNotFound {
        if (!contacts.containsKey(name)) {
            throw new ContactNotFound("Contact with name '" + name + "' not found.");
        }
        return contacts.get(name);
    }
    
    @Override
    public Contact[] listContacts() {
        Collection<Contact> values = contacts.values();
        Contact[] contactArray = new Contact[values.size()];
        return values.toArray(contactArray);
    }
}
