package com.clouway.gwt.bank.client;

import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.rpc.XsrfProtectedService;

import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@RemoteServiceRelativePath("pageService")
public interface PageService extends XsrfProtectedService {

  List<String> getPages();
}
