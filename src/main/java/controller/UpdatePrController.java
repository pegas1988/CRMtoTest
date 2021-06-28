package controller;

import entity.User;
import entity.roles;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdatePrController implements Controller {
    UserService userService = new UserService();

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
        String userRole = req.getParameter("userRole");
        String password = req.getParameter("psw");
        User user = new User();
        user.setFirstName(userName);
        user.setLastName(userLastName);
        user.setPassword(password);
        user.setUserRole(roles.valueOf(userRole));
        userService.updateUser(user);
        req.setAttribute("user", user);
        return new ControllerResultDto("login");
    }
}
