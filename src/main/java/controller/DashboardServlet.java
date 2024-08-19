package controller;

import dao.DeviceDAO;
import dao.ClaimDAO;
import dao.impl.DeviceDAOImpl;
import dao.impl.ClaimDAOImpl;
import model.Device;
import model.Claim;
import dao.NotificationDAO;
import dao.impl.NotificationDAOImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
    private DeviceDAO deviceDAO = new DeviceDAOImpl();
    private ClaimDAO claimDAO = new ClaimDAOImpl();
    private NotificationDAO notificationDAO = new NotificationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Get the list of devices
        List<Device> devices = deviceDAO.getDevicesByUserId(userId);
        session.setAttribute("devices", devices); 

        // Populate the claims map
        Map<Integer, List<Claim>> claimsMap = new HashMap<>();
        Map<Integer, Integer> claimCountMap = new HashMap<>();

        for (Device device : devices) {
            List<Claim> claims = claimDAO.getClaimsByRegId(device.getRegId());
            claimsMap.put(device.getRegId(), claims);

            // Store the number of claims made for each device
            int claimCount = claimDAO.getClaimCountByRegId(device.getRegId());
            claimCountMap.put(device.getRegId(), claimCount);
        }

        session.setAttribute("claimsMap", claimsMap);
        session.setAttribute("claimCountMap", claimCountMap);
        
        // Handle notifications
        int unreadCount = notificationDAO.getUnreadNotificationCount(userId);
        request.setAttribute("unreadCount", unreadCount);

        // Forward to dashboard.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
