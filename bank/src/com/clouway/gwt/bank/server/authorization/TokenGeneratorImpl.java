package com.clouway.gwt.bank.server.authorization;

import com.google.gwt.user.client.rpc.XsrfToken;
import com.google.gwt.user.server.rpc.XsrfTokenServiceServlet;

import java.util.UUID;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class TokenGeneratorImpl extends XsrfTokenServiceServlet implements TokenGenerator {

  public String generateKey() {
    return UUID.randomUUID().toString();
  }

  @Override
  public XsrfToken getNewXsrfToken() {
    return new XsrfToken(generateKey());
  }
}
