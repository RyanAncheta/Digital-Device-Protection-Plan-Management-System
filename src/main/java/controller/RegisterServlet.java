package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import model.User;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public RegisterServlet() {
        this.userDAO = new UserDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String email = request.getParameter("email");
        String cellphone = request.getParameter("cellphone");
        String address = request.getParameter("address");

        boolean hasError = false;

        // Validate username
        if (username == null || username.isEmpty() || username.length() < 4 || username.length() > 20) {
            request.setAttribute("usernameError", "Username must be between 4 and 20 characters.");
            hasError = true;
        } else if (userDAO.isUsernameTaken(username)) {
            request.setAttribute("usernameError", "Username is already taken.");
            hasError = true;
        }

        // Validate password
        if (password == null || password.isEmpty() || password.length() < 8) {
            request.setAttribute("passwordError", "Password must be at least 8 characters long.");
            hasError = true;
        }

        // Validate first name
        if (firstName == null || firstName.isEmpty()) {
            request.setAttribute("firstNameError", "First name is required.");
            hasError = true;
        }

        // Validate last name
        if (lastName == null || lastName.isEmpty()) {
            request.setAttribute("lastNameError", "Last name is required.");
            hasError = true;
        }

        // Validate email
        if (email == null || !email.matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")) {
            request.setAttribute("emailError", "Please enter a valid email address.");
            hasError = true;
        } else if (userDAO.isEmailTaken(email)) {
            request.setAttribute("emailError", "Email is already in use.");
            hasError = true;
        }

        // Validate cellphone
        if (cellphone == null || !cellphone.matches("[0-9]{10}")) {
            request.setAttribute("cellphoneError", "Please enter a valid 10-digit phone number.");
            hasError = true;
        }

        // Validate address
        if (address == null || address.isEmpty()) {
            request.setAttribute("addressError", "Address is required.");
            hasError = true;
        }

        if (hasError) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("register.jsp");
            dispatcher.forward(request, response);
        } else {
            User newUser = new User(username, password, firstName, lastName, email, cellphone, address);
            userDAO.registerUser(newUser);
            request.getSession().setAttribute("successNotification", "Registration successful! Please log in.");
            response.sendRedirect("login.jsp");
        }
    }
}
