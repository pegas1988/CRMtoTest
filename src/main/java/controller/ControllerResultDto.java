package controller;

import entity.User;

public class ControllerResultDto {
    private final String view;

    public ControllerResultDto(String view) {
        this.view = view;
    }

    public String getView() {
        return view;
    }

    public boolean isRedirect() {
        if (view.contains("Proceed"))
            return true;
        else
            return false;
    }
}