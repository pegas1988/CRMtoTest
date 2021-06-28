package servlet;

import entity.User;
import entity.roles;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CRM_war/deleteUser")
public class DeleteUserServlet extends HttpServlet {
    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listOfUsers = userService.findAllUsers();
        req.setAttribute("list", listOfUsers);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/jsp/deleteUser.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");

        User user = new User(name, lastName);
        userService.deleteFromUser(user);
        req.getRequestDispatcher("/WEB-INF/jsp/deleteUser.jsp").forward(req, resp);
    }
}
