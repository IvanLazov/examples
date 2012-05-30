package com.clouway.gwt.bank.client.register;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class RegisterNotificationMessagesImpl implements RegisterNotificationMessages {

  public String wrongUsernameMessage() {
    return "Username must contain only letters and digits! Length (1-20) characters.";
  }

  public String wrongPasswordMessage() {
    return "Password must contain only letters and digits! Length (6-20) characters.";
  }

  public String successfulRegistrationMessage() {
    return "You have successfully registered!";
  }
}
