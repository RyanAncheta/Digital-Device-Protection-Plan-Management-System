package controller;

import dao.ClaimDAO;
import dao.impl.ClaimDAOImpl;
import model.Claim;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/submitClaim")
public class ClaimServlet extends HttpServlet {
    private ClaimDAO claimDAO = new ClaimDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int regId = Integer.parseInt(request.getParameter("regId"));
        Date dateOfClaim = Date.valueOf(request.getParameter("dateOfClaim"));
        String description = request.getParameter("description");

        int claimCount = claimDAO.getClaimCountByRegId(regId);
        if (claimCount < 3) {
            // Allow user to apply for a new claim
            Claim newClaim = new Claim();
            newClaim.setRegId(regId);
            newClaim.setDateOfClaim(dateOfClaim);
            newClaim.setDescription(description);
            newClaim.setStatus("Pending");

            claimDAO.submitClaim(newClaim);

            request.getSession().setAttribute("success", "Claim submitted successfully.");
        } else {
            // Prevent the user from applying for more claims
            request.getSession().setAttribute("error", "You have reached the maximum number of claims allowed for this device.");
        }

        response.sendRedirect("dashboard");
    }
}
