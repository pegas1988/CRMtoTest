package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowPageController implements Controller {

    private final String VIEW_NAME;

    public ShowPageController(String VIEW_NAME) {
        this.VIEW_NAME = VIEW_NAME;
    }

    @Override
    public void setString(String line) {

    }

    @Override
    public String getString() {
        return null;
    }

    @Override
    public ControllerResultDto execute(HttpServletRequest req, HttpServletResponse resp) {
        return new ControllerResultDto(VIEW_NAME);
    }
}