package org.example.controllers.query.account;


import org.query.account.api.AccountDetailsViewQuery;
import org.query.account.model.AccountDetailsViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AccountDetailsViewController {

  private final AccountDetailsViewQuery viewQuery;
  public AccountDetailsViewController(AccountDetailsViewQuery viewQuery) {
    this.viewQuery = viewQuery;
  }

  @GetMapping("/account/{userId}")
  public AccountDetailsViewModel accountDetailsViewModel(@PathVariable String userId) {
    try{
      return viewQuery.query(userId);
    }catch (Exception e) {
      throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
    }
  }


}
