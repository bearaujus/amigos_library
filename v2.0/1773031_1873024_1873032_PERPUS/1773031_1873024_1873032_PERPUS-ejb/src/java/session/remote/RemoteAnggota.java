/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.Date;
import java.util.List;
import javax.ejb.Remote;
import model.Anggota;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Remote
public interface RemoteAnggota {

    public List<Anggota> getAllData();

    public void insert(Anggota param);

    public void update(Anggota param);

    public void delete(Anggota param);
    
    public Anggota getLoggedUser();
    
    public Boolean login(String username, String password);
    
    public void logout();
    
    public Anggota forgotPassword(String username, Date birthDate);
    
    public Boolean checkUsernameAlreadyExist(String username);
    
}
