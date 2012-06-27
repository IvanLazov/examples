package com.clouway.gwt.activitiesandplaces.application.server;

import com.clouway.gwt.activitiesandplaces.application.server.domain.Contact;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class ContactRepository {

  private static final Map<Long, Contact> contactsMap = new HashMap<Long, Contact>();

  private ContactRepository() {
  }

  public static void save(Contact contact) {
    if (!contactsMap.containsKey(contact.getId())) {
      contactsMap.put(contact.getId(), contact);
    }
  }

  public static Integer countContacts() {
    return contactsMap.size();
  }

  public static List<Contact> findAll() {

    List<Contact> contacts = new ArrayList<Contact>(contactsMap.values());
    System.out.println("Contacts: " + contacts + " size: " + contacts.size());
    return contacts;
  }

  public static Contact findContact(Long id) {

    return contactsMap.get(id);
  }
}
