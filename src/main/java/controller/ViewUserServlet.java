package controller;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/viewUser")
public class ViewUserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        int userId = Integer.parseInt(request.getParameter("userId"));

        User user = userDAO.getUserById(userId);

        request.setAttribute("user", user);

        RequestDispatcher dispatcher = request.getRequestDispatcher("view_user.jsp");
        dispatcher.forward(request, response);
    }
}
