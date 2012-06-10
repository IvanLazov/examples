package com.clouway.gwt.bank.server.register;

import com.clouway.gwt.bank.client.RegisterService;
import com.clouway.gwt.bank.server.account.AccountRepository;
import com.clouway.gwt.bank.shared.exceptions.UsernameAlreadyTakenException;
import com.clouway.gwt.bank.server.user.UserRepository;
import com.clouway.gwt.bank.shared.User;
import com.clouway.gwt.bank.shared.exceptions.WrongPasswordException;
import com.clouway.gwt.bank.shared.exceptions.WrongUsernameException;
import com.google.gwt.user.server.rpc.XsrfProtectedServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
@Singleton
public class RegisterServiceImpl extends XsrfProtectedServiceServlet implements RegisterService {

  private final UserRepository userRepository;
  private final AccountRepository accountRepository;

  @Inject
  public RegisterServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
    this.userRepository = userRepository;
    this.accountRepository = accountRepository;
  }

  public void registerUser(User user) {

    if (!user.getUsername().matches("^[a-zA-Z0-9]{1,20}$")) {
      throw new WrongUsernameException();
    }

    if (!user.getPassword().matches("^[a-zA-Z0-9]{6,20}$")) {
      throw new WrongPasswordException();
    }

    User returnedUser = userRepository.getUser(user.getUsername());

    if (returnedUser != null) {
      if (user.getUsername().equals(returnedUser.getUsername())) {
        throw new UsernameAlreadyTakenException();
      }
    }

    long userId = userRepository.register(user);
    accountRepository.createAccount(userId);
  }
}
