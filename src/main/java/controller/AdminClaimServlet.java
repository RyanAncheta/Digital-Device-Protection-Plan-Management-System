package controller;

import dao.ClaimDAO;
import dao.impl.ClaimDAOImpl;
import model.Claim;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin_claims")
public class AdminClaimServlet extends HttpServlet {
    private ClaimDAO claimDAO = new ClaimDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!isAdminLoggedIn(request)) {
            response.sendRedirect("admin_login.jsp");
            return;
        }

        HttpSession session = request.getSession(false);  

        List<Claim> claims = claimDAO.getPendingClaims(); 
        request.setAttribute("claims", claims);
        RequestDispatcher dispatcher = request.getRequestDispatcher("admin_claims.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);  
        if (!isAdminLoggedIn(request)) {
            response.sendRedirect("admin_login.jsp");
            return;
        }

        // Debug: Check session before updating claim
        if (session != null) {
            System.out.println("Admin logged in with session ID: " + session.getId());
        } else {
            System.out.println("Session is null. Redirecting to login.");
        }

        int claimId = Integer.parseInt(request.getParameter("claimId"));
        String status = request.getParameter("status");
        claimDAO.updateClaimStatus(claimId, status);
        
        // Refresh the page after updating the status
        response.sendRedirect("admin_claims");
    }

    private boolean isAdminLoggedIn(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // get existing session, don't create a new one
        return session != null && session.getAttribute("admin") != null;
    }
}
