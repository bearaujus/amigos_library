/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.List;
import javax.ejb.Remote;
import model.Peminjaman;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Remote
public interface RemotePeminjaman {

    public List<Peminjaman> getAllData();

    public void insert(Peminjaman param);

    public void update(Peminjaman param);

    public void delete(Peminjaman param);
    
    public Peminjaman getByID(Integer param);

}
