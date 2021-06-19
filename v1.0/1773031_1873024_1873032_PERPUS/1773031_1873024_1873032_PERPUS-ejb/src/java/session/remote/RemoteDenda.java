/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.remote;

import java.util.List;
import javax.ejb.Remote;
import model.Denda;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Remote
public interface RemoteDenda {

    public List<Denda> getAllData();

    public void insert(Denda param);

    public void update(Denda param);

    public void delete(Denda param);
    
    public Integer getTotalKas();

}
