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
import java.io.IOException;
import java.util.List;

@WebServlet("/view_claim_status")
public class ViewClaimStatusServlet extends HttpServlet {
    private ClaimDAO claimDAO = new ClaimDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String regIdStr = request.getParameter("regId");
        if (regIdStr == null || regIdStr.isEmpty()) {
            response.sendRedirect("dashboard.jsp");
            return;
        }

        int regId = Integer.parseInt(regIdStr);
        List<Claim> claims = claimDAO.getClaimsByRegId(regId);
        request.setAttribute("claims", claims);

        System.out.println("Claims retrieved: " + claims); 

        RequestDispatcher dispatcher = request.getRequestDispatcher("view_claim_status.jsp");
        dispatcher.forward(request, response);
    }
}
