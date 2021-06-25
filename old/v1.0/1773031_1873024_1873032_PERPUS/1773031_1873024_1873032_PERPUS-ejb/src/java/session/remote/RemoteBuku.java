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
 * @author Bear Au Jus - ジュースとくま
 */
@Remote
public interface RemoteBuku {

    public List<Buku> getAllData();

    public void insert(Buku param);

    public void update(Buku param);

    public void delete(Buku param);

}
