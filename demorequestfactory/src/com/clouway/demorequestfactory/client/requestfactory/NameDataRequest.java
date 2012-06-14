package com.clouway.demorequestfactory.client.requestfactory;

import com.clouway.demorequestfactory.server.domain.NameData;
import com.google.web.bindery.requestfactory.shared.InstanceRequest;
import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.Service;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Service(NameData.class)
public interface NameDataRequest extends RequestContext {

  Request<Long> count();

  Request<NameDataProxy> findNameData(Long id);

  Request<List<NameDataProxy>> query();

  Request<List<NameDataProxy>> query(int firstResult, int maxResult);

  InstanceRequest<NameDataProxy, NameDataProxy> persist();

  InstanceRequest<NameDataProxy, Void> remove();
}
