package com.clouway.demorequestfactory.server.domain;

import com.clouway.demorequestfactory.server.EMF;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Entity
public class NameData {

  public static final EntityManager entityManager() {
    return EMF.get().createEntityManager();
  }

  public static long count() {
    EntityManager entityManager = entityManager();

    try {
      return ((Number) entityManager.createQuery("SELECT count(o) FROM " + NameData.class.getSimpleName() + " o").getSingleResult()).longValue();
    } finally {
      entityManager.close();
    }
  }

  public static NameData findNameData(Long id) {
    if (id == null) {
      return null;
    }

    EntityManager entityManager = entityManager();

    try {
      NameData employee = entityManager.find(NameData.class, id);
      return employee;
    } finally {
      entityManager.close();
    }
  }

  public static List<NameData> query() {
    EntityManager entityManager = entityManager();

    try {
      List<NameData> list = entityManager.createQuery("SELECT o FROM " + NameData.class.getSimpleName() + " o").getResultList();
      list.size();
      return list;
    } finally {
      entityManager.close();
    }
  }

  public static List<NameData> query(int firstResult, int maxResults) {
    EntityManager entityManager = entityManager();

    try {
      List<NameData> resultList = entityManager.createQuery("SELECT o FROM " + NameData.class.getSimpleName() + " o")
              .setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
      resultList.size();
      return resultList;
    } finally {
      entityManager.close();
    }
  }

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /**
   * User by RequestFactory to infer in an entity has changed
   */
  @Version
  @Column(name = "version")
  private Integer version;

  private String name;

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setVersion(Integer version) {
    this.version = version;
  }

  public Integer getVersion() {
    return version;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public NameData persist() {
    EntityManager entityManager = entityManager();

    try {
      entityManager.persist(this);
    } finally {
      entityManager.close();
    }

    return this;
  }

  public void remove() {
    EntityManager entityManager = entityManager();

    try {
      NameData attached = entityManager.find(NameData.class, this.id);
      entityManager.remove(attached);
    } finally {
      entityManager.close();
    }
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Id: ").append(getId()).append(", ");
    stringBuilder.append("name: ").append(getName()).append(", ");
    return stringBuilder.toString();
  }
}
