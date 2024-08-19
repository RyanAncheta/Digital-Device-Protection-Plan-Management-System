package controller;

import dao.NotificationDAO;
import dao.impl.NotificationDAOImpl;
import model.Notification;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/notifications")
public class NotificationServlet extends HttpServlet {
    private NotificationDAO notificationDAO = new NotificationDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer userId = (Integer) session.getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<Notification> notifications = notificationDAO.getNotificationsByUserId(userId);
        int unreadCount = notificationDAO.getUnreadNotificationCount(userId);

        request.setAttribute("notifications", notifications);
        request.setAttribute("unreadCount", unreadCount);

        RequestDispatcher dispatcher = request.getRequestDispatcher("notifications.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int notificationId = Integer.parseInt(request.getParameter("notificationId"));
        notificationDAO.markAsRead(notificationId);

        response.sendRedirect("notifications");
    }
}
