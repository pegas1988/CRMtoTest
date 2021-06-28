package controller;

import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserController implements Controller {
    UserService userService = new UserService();

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        User user = new User(firstName, lastName);
        userService.deleteFromUser(user);
        return new ControllerResultDto("userDeleted");
    }

    @Override
    public void setString(String line) {

    }

    @Override
    public String getString() {
        return null;
    }
}
