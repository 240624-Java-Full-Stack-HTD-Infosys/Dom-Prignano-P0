package Controllers;

import Services.UserService;

public class UserController {

    // Class setup
    UserService userService;
    public UserController(UserService userService) { this.userService = userService; }



    public User postNewUser() throws SQLException {
        return userService.registerNewUser(user);
    }

    public User login() {

    }

}
