package controller;

import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginController implements Controller {

    private UserService userService = new UserService();

    @Override
    public void setString(String line) {

    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String userName = req.getParameter("firstName");
        String userLastName = req.getParameter("lastName");
        String password = req.getParameter("psw");
        User userFind = new User(userName, userLastName);
        User user = userService.findByFirstAndLastName(userFind);
        if (user.getPassword().equals(password)) {
            req.setAttribute("user", user);
            switch (user.getUserRole()) {
                case DESIGNER:
                    return new ControllerResultDto("designer");
                case DOCTOR:
                    return new ControllerResultDto("doctor");
                case ADMIN:
                    return new ControllerResultDto("admin");
                default:
                    return new ControllerResultDto("error-403");
            }
        } else
            return new ControllerResultDto("error-403");
    }
}