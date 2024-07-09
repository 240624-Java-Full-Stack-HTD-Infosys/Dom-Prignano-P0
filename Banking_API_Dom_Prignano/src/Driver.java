
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {
        Javalin app = Javalin.create().start();
        User user = new User("Dom", "Prignano", "username", "password");
        UserController userController = new UserController(new UserService(new UserDao(ConnectionUtil.getConnection())));

        app.post("/user",
                (ctx) -> {
                ctx.status(201);
                userController.postNewUser(ctx.bodyAsClass(User.class));
            }
        );

        app.get("/user/{username}",
                (ctx) -> {
                    ctx.json(user);
                    ctx.json(userController.getUserByUsername(ctx.pathParam("username")));
                }
        );

        app.get("/headers-test",
                (ctx)->{
                    String contentType = ctx.header("Content-Type");
                    Map<String, String> headers = ctx.headerMap();
                    for (String key : headers.keySet()) {
                        System.out.println("[" + key + "]: " + headers.get(key));
                    }
                }
        )

    }
}