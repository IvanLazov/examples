package com.clouway.gwt.bank.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface PageServiceAsync {

  void getPages(AsyncCallback<List<String>> async);
}
