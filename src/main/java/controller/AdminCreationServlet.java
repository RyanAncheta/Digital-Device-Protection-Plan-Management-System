package controller;

import dao.AdminDAO;
import dao.impl.AdminDAOImpl;
import model.Admin;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminCreation")
public class AdminCreationServlet extends HttpServlet {
    private static final String SECRET_TOKEN = System.getenv("ADMIN_SECRET_TOKEN");
    private AdminDAO adminDAO = new AdminDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String token = request.getParameter("token");

        // Debug statements
        System.out.println("Debug: Provided token: " + token);
        System.out.println("Debug: Expected token: " + SECRET_TOKEN);

        
        if (SECRET_TOKEN != null && SECRET_TOKEN.equals(token)) {
            Admin existingAdminByUsername = adminDAO.getAdminByUsername(username);

            Admin existingAdminByEmail = adminDAO.getAdminByEmail(email);

            if (existingAdminByUsername != null) {
                request.setAttribute("message", "Username already exists.");
            } else if (existingAdminByEmail != null) {
                request.setAttribute("message", "Email already exists.");
            } else {
                Admin newAdmin = new Admin(0, username, password, email);
                boolean success = adminDAO.createAdmin(newAdmin);
                if (success) {
                	request.getSession().setAttribute("success", "Admin account created successfully!");
                    response.sendRedirect("adminLogin");
                    return;
                } else {
                    request.setAttribute("message", "Failed to create admin account.");
                }
            }
        } else {
            request.setAttribute("message", "Invalid token. Please provide a valid token.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_creation.jsp");
        dispatcher.forward(request, response);
    }
}
