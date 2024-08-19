package controller;

import dao.UserDAO;
import dao.DeviceDAO;
import dao.ProductDAO;
import dao.ClaimDAO;
import dao.impl.UserDAOImpl;
import dao.impl.DeviceDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.ClaimDAOImpl;
import model.User;
import model.Claim;
import model.Device;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/admin_dashboard")
public class AdminDashboardServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    private ClaimDAO claimDAO = new ClaimDAOImpl();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 if (!isAdminLoggedIn(request)) {
             response.sendRedirect("admin_login.jsp");
             return;
         }
        String action = request.getParameter("action");
        if ("searchUser".equals(action)) {
            searchUser(request, response);
        } else if ("searchProduct".equals(action)) {
            searchProduct(request, response);
        } else if ("manageProducts".equals(action)) {
            manageProducts(request, response);
        } else if ("generateReport".equals(action)) {
            generateReport(request, response);
        } else {
            showDashboard(request, response);
        }
    }

    private void searchUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<User> users = userDAO.searchUsers(query);
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        List<Device> devices = deviceDAO.searchDevices(query);
        request.setAttribute("devices", devices);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
        dispatcher.forward(request, response);
    }

    private void manageProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();
        request.setAttribute("products", products);
        RequestDispatcher dispatcher = request.getRequestDispatcher("manage_products.jsp");
        dispatcher.forward(request, response);
    }

    private void generateReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        Map<Integer, List<Device>> userDevices = new HashMap<>();
        Map<Integer, List<Claim>> userClaims = new HashMap<>();

        for (User user : users) {
            int userId = user.getUserId();
            userDevices.put(userId, deviceDAO.getDevicesByUserId(userId));
            for (Device device : deviceDAO.getDevicesByUserId(userId)) {
                int regId = device.getRegId();
                userClaims.put(regId, claimDAO.getClaimsByRegId(regId));
            }
        }

        request.setAttribute("users", users);
        request.setAttribute("userDevices", userDevices);
        request.setAttribute("userClaims", userClaims);

        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_report.jsp");
        dispatcher.forward(request, response);
    }

    private void showDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<User> users = userDAO.getAllUsers();
        request.setAttribute("users", users);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_dashboard.jsp");
        dispatcher.forward(request, response);
    }
    private boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session != null && session.getAttribute("admin") != null;
    }
    
   
}
