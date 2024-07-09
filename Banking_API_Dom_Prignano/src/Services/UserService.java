package Services;

import Daos.UserDao;

public class UserService {

    // Class setup
    UserDao userDao;
    public UserService(UserDao userDao) { this.userDao = userDao; }

    public User getUserByUsername(String username) throws SQLException {

    }

}
