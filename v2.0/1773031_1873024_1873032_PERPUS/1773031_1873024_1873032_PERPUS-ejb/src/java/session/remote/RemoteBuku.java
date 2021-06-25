/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.List;
import javax.ejb.Remote;
import model.Buku;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Remote
public interface RemoteBuku {

    public List<Buku> getAllData();

    public void insert(Buku param);

    public void update(Buku param);

    public void delete(Buku param);
    
    public List<Buku> getAllDataAvailable();
    
    public List<Buku> getAllDataUnvailable();
    
    public List<Buku> getByKategoriID(Integer param);

}
