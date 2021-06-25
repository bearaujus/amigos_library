/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session.ejb;

import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.AdminPerpus;
import session.remote.RemoteAdminPerpus;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Stateless
@LocalBean
public class EjbAdminPerpus implements RemoteAdminPerpus {

    private AdminPerpus loggedUser;

    @PersistenceContext(unitName = "1773031_1873024_1873032_PERPUS-ejbPU")
    private EntityManager em;

    @Override
    public List<AdminPerpus> getAllData() {
        Query query = em.createNamedQuery("AdminPerpus.findAll");
        return query.getResultList();
    }

    @Override
    public void insert(AdminPerpus param) {
        em.persist(param);
    }

    @Override
    public void update(AdminPerpus param) {
        em.merge(param);
    }

    @Override
    public void delete(AdminPerpus param) {
        em.remove(em.merge(param));
    }

    @Override
    public AdminPerpus getLoggedUser() {
        return loggedUser;
    }

    @Override
    public Boolean login(String username, String password) {
        Query q = em.createQuery("SELECT x FROM AdminPerpus x WHERE x.username = :username AND x.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List<AdminPerpus> model = q.getResultList();
        if (model.isEmpty()) {
            return false;
        }
        loggedUser = model.get(0);
        return true;
    }

    @Override
    public void logout() {
        loggedUser = null;
    }

    @Override
    public AdminPerpus forgotPassword(String username, Date birthDate) {
        Query q = em.createQuery("SELECT x FROM AdminPerpus x WHERE x.username = :username AND x.tanggalLahir = :birthDate");
        q.setParameter("username", username);
        q.setParameter("birthDate", birthDate);
        List<AdminPerpus> model = q.getResultList();
        return model.isEmpty() ? null : model.get(0);
    }

    @Override
    public Boolean checkUsernameAlreadyExist(String username) {
        Query q = em.createQuery("SELECT x FROM AdminPerpus x WHERE x.username = :username");
        q.setParameter("username", username);
        List<AdminPerpus> model = q.getResultList();
        return !model.isEmpty();
    }

}
