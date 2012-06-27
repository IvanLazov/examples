package com.clouway.gwt.activitiesandplaces.application.server.domain;

import com.clouway.gwt.activitiesandplaces.application.server.ContactRepository;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Entity
public class Contact {

  @Id
  private Long id;

  @Version
  private Integer version;

  @NotNull
  private String name;

  private String address;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return this.id;
  }

  public Integer getVersion() {
    return this.version;
  }

  public String getName() {
    return this.name;
  }

  public String getAddress() {
    return this.address;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void persist(Contact contact) {
    ContactRepository.save(contact);
  }

  public static void generateContacts() {

    for (Integer i = 1; i < 4; i++) {
      Contact contact = new Contact();
      contact.setId(Long.valueOf(i));
      contact.setVersion(i);
      contact.setName("Name " + i.toString());
      contact.setAddress("Address " + i.toString());
      contact.persist(contact);
    }
  }

  public static Contact findContact(Long id) {
    return ContactRepository.findContact(id);
  }

  public static List<Contact> findAllContact() {
    return ContactRepository.findAll();
  }
}
