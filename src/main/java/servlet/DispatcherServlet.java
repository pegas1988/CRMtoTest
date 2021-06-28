package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.ControllerResultDto;
import controller.Controller;
import controller.ControllerFactory;
import controller.LoginController;

@WebServlet("/CRM_war/*")
public class DispatcherServlet extends HttpServlet {

    private ControllerFactory controllerFactory;
    String path ="";

    @Override
    public void init() throws ServletException {
        controllerFactory = new ControllerFactory();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = controllerFactory.getController(req);
        try {
            ControllerResultDto result = controller.execute(req, resp);
            path = controller.getString();
            doForwardOrRedirect(result, req, resp);
        } catch (Exception e) {
            throw new ServletException("Cannot execute action", e);
        }
    }

    /*private void doForwardOrRedirect(ControllerResultDto result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
        req.getRequestDispatcher(path).forward(req, resp);
    }*/
    private void doForwardOrRedirect(ControllerResultDto result, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if (result.isRedirect()) {
            resp.sendRedirect("http://localhost:8080/CRM_war/CRM_war/" + result.getView() + ".jsp");
        } else {
            String path = String.format("/WEB-INF/jsp/" + result.getView() + ".jsp");
            req.getRequestDispatcher(path).forward(req, resp);
        }
    }
}
