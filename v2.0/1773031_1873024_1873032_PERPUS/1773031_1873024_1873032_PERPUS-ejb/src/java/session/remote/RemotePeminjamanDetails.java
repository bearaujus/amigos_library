/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.List;
import javax.ejb.Remote;
import model.Anggota;
import model.PeminjamanDetails;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Remote
public interface RemotePeminjamanDetails {

    public List<PeminjamanDetails> getAllData();
    
    public List<PeminjamanDetails> getAllDataPeminjamanAktif();
    
    public List<PeminjamanDetails> getAllDataHistoryPeminjamanAktif();

    public void insert(PeminjamanDetails param);

    public void update(PeminjamanDetails param);

    public void delete(PeminjamanDetails param);
    
    public List<PeminjamanDetails> getListByIDPeminjaman(Integer param);
    
    public List<PeminjamanDetails> getListActivePeminjaman(Anggota param);
    
    public List<PeminjamanDetails> getListAnggotaPeminjaman(Anggota param);

}
