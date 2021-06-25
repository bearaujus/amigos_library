/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Buku;
import session.remote.RemoteBuku;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Stateless
@LocalBean
public class EjbBuku implements RemoteBuku {

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<Buku> getAllData() {
        Query query = em.createNamedQuery("Buku.findAll");
        return query.getResultList();
    }

    @Override
    public void insert(Buku param) {
        em.persist(param);
    }

    @Override
    public void update(Buku param) {
        em.merge(param);
    }

    @Override
    public void delete(Buku param) {
        em.remove(em.merge(param));
    }

    @Override
    public List<Buku> getAllDataAvailable() {
        Query q = em.createQuery("SELECT b FROM Buku b WHERE NOT b.stok = :stok");
        q.setParameter("stok", 0);
        return q.getResultList();
    }

    @Override
    public List<Buku> getAllDataUnvailable() {
        Query q = em.createQuery("SELECT b FROM Buku b WHERE b.stok = :stok");
        q.setParameter("stok", 0);
        return q.getResultList();
    }

    @Override
    public List<Buku> getByKategoriID(Integer param) {
        Query q = em.createQuery("SELECT b FROM Buku b WHERE b.idKategori.id = :idKategori");
        q.setParameter("idKategori", param);
        return q.getResultList();
    }

}
