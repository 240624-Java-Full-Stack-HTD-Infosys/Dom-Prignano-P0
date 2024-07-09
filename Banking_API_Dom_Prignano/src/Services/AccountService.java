package Services;

import Daos.AccountDao;

public class AccountService {

    // Class setup
    AccountDao accountDao;
    public AccountService(AccountDao accountDao) { this.accountDao = accountDao; }



}
