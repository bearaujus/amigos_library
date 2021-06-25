/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Remote;
import model.Kategori;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Remote
public interface RemoteKategori {

    public List<Kategori> getAllData();

    public void insert(Kategori param);

    public void update(Kategori param);

    public void delete(Kategori param);
    
    public HashMap<String, Integer> getSelectOneMenuModel();

}
