package controller;

import entity.User;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowProfileController implements Controller {
    @Override
    public void setString(String line) {

    }

    @Override
    public String getString() {
        return null;
    }

    private UserService userService;

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        return null;
    }
}