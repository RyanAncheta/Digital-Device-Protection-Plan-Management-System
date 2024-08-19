package dao;

import model.Notification;
import java.util.List;

public interface NotificationDAO {
    void saveNotification(Notification notification);
    List<Notification> getNotificationsByUserId(int userId);
    int getUnreadNotificationCount(int userId);
    void markAsRead(int notificationId);
}
