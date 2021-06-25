/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import assets.Utill;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author 1773031_1873024_1873032
 */
@Entity
@Table(name = "admin_perpus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminPerpus.findAll", query = "SELECT a FROM AdminPerpus a")
    , @NamedQuery(name = "AdminPerpus.findById", query = "SELECT a FROM AdminPerpus a WHERE a.id = :id")
    , @NamedQuery(name = "AdminPerpus.findByUsername", query = "SELECT a FROM AdminPerpus a WHERE a.username = :username")
    , @NamedQuery(name = "AdminPerpus.findByPassword", query = "SELECT a FROM AdminPerpus a WHERE a.password = :password")
    , @NamedQuery(name = "AdminPerpus.findByNama", query = "SELECT a FROM AdminPerpus a WHERE a.nama = :nama")
    , @NamedQuery(name = "AdminPerpus.findByTanggalLahir", query = "SELECT a FROM AdminPerpus a WHERE a.tanggalLahir = :tanggalLahir")})
public class AdminPerpus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "username")
    private String username;
    @Size(max = 45)
    @Column(name = "password")
    private String password;
    @Size(max = 45)
    @Column(name = "nama")
    private String nama;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @OneToMany(mappedBy = "idAdminPerpus")
    private Collection<Peminjaman> peminjamanCollection;

    public AdminPerpus() {
    }

    public AdminPerpus(String username, String password, String nama, String tanggalLahir) throws ParseException {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.tanggalLahir = new SimpleDateFormat("dd/MM/yyyy").parse(tanggalLahir);
    }

    public AdminPerpus(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public String getTanggalLahirFormatted() {
        return Utill.getDateFormatted(tanggalLahir);
    }

    public String getIdAliiasFormatted() {
        return "AP - " + getId();
    }

    public String getPasswordEncrypted() {
        String o = "";
        for (int i = 0; i < password.length(); i++) {
            o += "*";
        }
        return o;
    }

    @XmlTransient
    public Collection<Peminjaman> getPeminjamanCollection() {
        return peminjamanCollection;
    }

    public void setPeminjamanCollection(Collection<Peminjaman> peminjamanCollection) {
        this.peminjamanCollection = peminjamanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminPerpus)) {
            return false;
        }
        AdminPerpus other = (AdminPerpus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.AdminPerpus[ id=" + id + " ]";
    }

}
