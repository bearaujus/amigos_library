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
import model.Peminjaman;
import session.remote.RemotePeminjaman;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Stateless
@LocalBean
public class EjbPeminjaman implements RemotePeminjaman {

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<Peminjaman> getAllData() {
        Query query = em.createNamedQuery("Peminjaman.findAll");
        return query.getResultList();
    }

    @Override
    public void insert(Peminjaman param) {
        em.persist(param);
    }

    @Override
    public void update(Peminjaman param) {
        em.merge(param);
    }

    @Override
    public void delete(Peminjaman param) {
        em.remove(em.merge(param));
    }

    @Override
    public Peminjaman getByID(Integer param) {
        Query q = em.createQuery("SELECT x FROM Peminjaman x WHERE x.id = :idPeminjaman");
        q.setParameter("idPeminjaman", param);
        List<Peminjaman> model = q.getResultList();
        return model.isEmpty() ? null : model.get(0);
    }

}
