package com.clouway.gwt.requestfactory.contacts.shared;

import com.clouway.gwt.requestfactory.contacts.server.domain.MyServiceLocator;
import com.clouway.gwt.requestfactory.contacts.server.domain.PersonService;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Service(value = PersonService.class, locator = MyServiceLocator.class)
public interface PersonRequest extends RequestContext {

  Request<Void> save(PersonProxy personProxy);

  Request<PersonProxy> findPerson(Long id);

  Request<List<PersonProxy>> findAll();

  Request<Void> delete(Long id);
}
