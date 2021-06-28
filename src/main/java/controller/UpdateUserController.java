package controller;

import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    static String path = "";
    UserService userService = new UserService();

    @Override
    public void setString(String line) {
        String url = "?email=" + line;
        this.path = url;
    }

    @Override
    public String getString() {
        return path;
    }

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String email = req.getParameter("email");
        setString(email);
        User userFind = new User(email);
        User user = userService.findByEmail(userFind);
        //req.setAttribute("user", user);
        return new ControllerResultDto("updateUserProceed");
    }
}
