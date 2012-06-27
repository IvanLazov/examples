package com.clouway.gwt.activitiesandplaces.application.shared;

import com.clouway.gwt.activitiesandplaces.application.server.domain.Contact;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Service(Contact.class)
public interface ContactRequest extends RequestContext {

  InstanceRequest<ContactProxy, Void> persist(ContactProxy contact);
  Request<Void> generateContacts();
  Request<List<ContactProxy>> findAllContact();
  Request<ContactProxy> findContact(Long id);
}
