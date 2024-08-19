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

@WebServlet("/claims")
public class ViewClaim extends HttpServlet {
    private ClaimDAO claimDAO = new ClaimDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Retrieve the user's claims using the new method
        List<Claim> claims = claimDAO.getClaimsByUserId(userId);
        request.setAttribute("claims", claims);

        // Forward to the claims JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("claims.jsp");
        dispatcher.forward(request, response);
    }
}
