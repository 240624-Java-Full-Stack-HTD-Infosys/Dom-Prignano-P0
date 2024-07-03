package Controllers;

import Services.UserService;

public class UserController {

    // Class setup
    UserService userService;
    public UserController(UserService userService) { this.userService = userService; }


}
