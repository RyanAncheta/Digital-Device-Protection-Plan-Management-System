package dao;

import model.Admin;

public interface AdminDAO {
    boolean createAdmin(Admin admin);
    Admin getAdminByUsername(String username);
    Admin getAdminByEmail(String email);  
    Admin validateAdmin(String username, String password);
}
