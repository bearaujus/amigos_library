/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import java.util.HashMap;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Kategori;
import session.remote.RemoteKategori;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Stateless
@LocalBean
public class EjbKategori implements RemoteKategori {

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<Kategori> getAllData() {
        Query query = em.createNamedQuery("Kategori.findAll");
        return query.getResultList();
    }

    @Override
    public void insert(Kategori param) {
        em.persist(param);
    }

    @Override
    public void update(Kategori param) {
        em.merge(param);
    }

    @Override
    public void delete(Kategori param) {
        em.remove(em.merge(param));
    }

    @Override
    public HashMap<String, Integer> getSelectOneMenuModel() {
        HashMap<String, Integer> o = new HashMap<>();
        getAllData().forEach((e) -> {
            o.put(e.getNama(), e.getId());
        });
        return o;
    }

}
