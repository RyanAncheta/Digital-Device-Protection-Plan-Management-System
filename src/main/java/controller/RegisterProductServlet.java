package controller;

import dao.DeviceDAO;
import dao.impl.DeviceDAOImpl;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Device;
import model.Product;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/RegisterProductServlet")
public class RegisterProductServlet extends HttpServlet {
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private ProductDAO productDAO = new ProductDAOImpl();
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> products = productDAO.getAllProducts();

        request.setAttribute("products", products);

        RequestDispatcher dispatcher = request.getRequestDispatcher("register_product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        System.out.println("User ID from session in RegisterProductServlet: " + userId);

        if (userId == null) {
            System.out.println("User is not logged in, redirecting to login.jsp");
            response.sendRedirect("login.jsp");
            return;
        }

        String productName = request.getParameter("productName");
        String serialNo = request.getParameter("serialNo");
        Date purchaseDate = Date.valueOf(request.getParameter("purchaseDate"));

        Device device = new Device();
        device.setUserId(userId);
        device.setProductName(productName);
        device.setSerialNo(serialNo);
        device.setPurchaseDate(purchaseDate);

        deviceDAO.registerDevice(device);

        session.setAttribute("message", "Device registered successfully");
        response.sendRedirect("dashboard");
    }
}
