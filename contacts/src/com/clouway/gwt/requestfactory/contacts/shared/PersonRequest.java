package com.clouway.gwt.requestfactory.contacts.shared;

import com.clouway.gwt.requestfactory.contacts.server.domain.Person;
import com.clouway.gwt.requestfactory.contacts.server.domain.PersonLocator;
import com.clouway.gwt.requestfactory.contacts.server.domain.PersonRepository;
import com.clouway.gwt.requestfactory.contacts.server.domain.PersonRepositoryImpl;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Service(value = PersonRepository.class, locator = )
public interface PersonRequest extends RequestContext {

  Request<Void> save(PersonProxy personProxy);

  Request<PersonProxy> findPerson(Long id);
}
