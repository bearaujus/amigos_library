/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import model.AdminPerpus;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Remote
public interface RemoteAdminPerpus {

    public List<AdminPerpus> getAllData();

    public void insert(AdminPerpus param);

    public void update(AdminPerpus param);

    public void delete(AdminPerpus param);
    
    public AdminPerpus getLoggedUser();
    
    public Boolean login(String username, String password);
    
    public void logout();
    
    public AdminPerpus forgotPassword(String username, Date birthDate);
    
    public Boolean checkUsernameAlreadyExist(String username);
    
}
