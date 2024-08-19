package controller;

import dao.ClaimDAO;
import dao.DeviceDAO;
import dao.impl.ClaimDAOImpl;
import dao.impl.DeviceDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteProduct")
public class DeleteProductServlet extends HttpServlet {
    private DeviceDAO registeredProductDAO = new DeviceDAOImpl();
    private ClaimDAO claimDAO = new ClaimDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regIdParam = request.getParameter("regId");
        if (regIdParam != null) {
            int regId = Integer.parseInt(regIdParam);

            if (claimDAO.getClaimsByRegId(regId).isEmpty()) {
                registeredProductDAO.deleteRegisteredProduct(regId);
                request.getSession().setAttribute("success", "Product deleted successfully.");
            } else {
                request.getSession().setAttribute("error", "Cannot delete product with existing claims.");
            }

            response.sendRedirect("dashboard");
        } else {
            response.sendRedirect("dashboard"); 
        }
    }
}
