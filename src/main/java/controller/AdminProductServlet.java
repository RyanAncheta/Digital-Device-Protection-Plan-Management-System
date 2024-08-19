package controller;

import dao.ProductDAO;
import dao.impl.ProductDAOImpl;
import model.Product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin_product")
public class AdminProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 if (!isAdminLoggedIn(request)) {
             response.sendRedirect("admin_login.jsp");
             return;
         }
        String action = request.getParameter("action");
        if ("edit".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = productDAO.getProductById(productId);
            request.setAttribute("product", product);
            RequestDispatcher dispatcher = request.getRequestDispatcher("product_form.jsp");
            dispatcher.forward(request, response);
        } else if ("delete".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            productDAO.deleteProduct(productId);
            response.sendRedirect("admin_dashboard?action=manageProducts");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String productName = request.getParameter("productName");
        String model = request.getParameter("model");
        String description = request.getParameter("description");

        if ("insert".equals(action)) {
            Product product = new Product();
            product.setProductName(productName);
            product.setModel(model);
            product.setDescription(description);
            productDAO.addProduct(product);
        } else if ("update".equals(action)) {
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = new Product();
            product.setProductId(productId);
            product.setProductName(productName);
            product.setModel(model);
            product.setDescription(description);
            productDAO.updateProduct(product);
        }
        response.sendRedirect("admin_dashboard?action=manageProducts");
    }
    private boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session != null && session.getAttribute("admin") != null;
    }
}