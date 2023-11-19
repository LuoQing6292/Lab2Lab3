package demo05;


import java.util.ArrayList;
import java.util.List;

public class ContactDatabase {
    private static List<Contact> contacts = new ArrayList<>();

    public static void addContact(Contact contact) {
        contacts.add(contact);
    }

    public static List<Contact> getAllContacts() {
        return contacts;
    }
}

