/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import java.util.Collections;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.Anggota;
import model.PeminjamanDetails;
import session.remote.RemotePeminjamanDetails;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Stateless
@LocalBean
public class EjbPeminjamanDetails implements RemotePeminjamanDetails {

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<PeminjamanDetails> getAllData() {
        Query query = em.createNamedQuery("PeminjamanDetails.findAll");
        return query.getResultList();
    }

    @Override
    public List<PeminjamanDetails> getAllDataPeminjamanAktif() {
        Query q = em.createQuery("SELECT x FROM PeminjamanDetails x WHERE x.tanggalPengembalian IS NULL AND x.statusKonfirmasi = :statusKonfirmasi");
        q.setParameter("statusKonfirmasi", true);
        return q.getResultList();
    }

    @Override
    public List<PeminjamanDetails> getAllDataHistoryPeminjamanAktif() {
        Query q = em.createQuery("SELECT x FROM PeminjamanDetails x WHERE NOT x.tanggalPengembalian IS NULL AND x.statusKonfirmasi = :statusKonfirmasi");
        q.setParameter("statusKonfirmasi", true);
        return q.getResultList();
    }

    @Override
    public void insert(PeminjamanDetails param) {
        em.persist(param);
    }

    @Override
    public void update(PeminjamanDetails param) {
        em.merge(param);
    }

    @Override
    public void delete(PeminjamanDetails param) {
        em.remove(em.merge(param));
    }

    @Override
    public List<PeminjamanDetails> getListByIDPeminjaman(Integer param) {
        Query q = em.createQuery("SELECT x FROM PeminjamanDetails x WHERE x.idPeminjaman.id = :idPeminjaman");
        q.setParameter("idPeminjaman", param);
        List<PeminjamanDetails> model = q.getResultList();
        Collections.reverse(model);
        return model;
    }

    @Override
    public List<PeminjamanDetails> getListActivePeminjaman(Anggota param) {
        Query q = em.createQuery("SELECT x FROM PeminjamanDetails x WHERE x.idPeminjaman.idAnggota.id = :idAnggota AND x.tanggalPengembalian IS NULL AND x.statusKonfirmasi = :statusKonfirmasi");
        q.setParameter("idAnggota", param.getId());
        q.setParameter("statusKonfirmasi", true);
        return q.getResultList();
    }

    @Override
    public List<PeminjamanDetails> getListAnggotaPeminjaman(Anggota param) {
        Query q = em.createQuery("SELECT x FROM PeminjamanDetails x WHERE x.idPeminjaman.idAnggota.id = :idAnggota");
        q.setParameter("idAnggota", param.getId());
        List<PeminjamanDetails> model = q.getResultList();
        Collections.reverse(model);
        return model;
    }

}
