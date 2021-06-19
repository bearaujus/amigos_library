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
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "anggota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Anggota.findAll", query = "SELECT a FROM Anggota a")
    , @NamedQuery(name = "Anggota.findById", query = "SELECT a FROM Anggota a WHERE a.id = :id")
    , @NamedQuery(name = "Anggota.findByUsername", query = "SELECT a FROM Anggota a WHERE a.username = :username")
    , @NamedQuery(name = "Anggota.findByPassword", query = "SELECT a FROM Anggota a WHERE a.password = :password")
    , @NamedQuery(name = "Anggota.findByNama", query = "SELECT a FROM Anggota a WHERE a.nama = :nama")
    , @NamedQuery(name = "Anggota.findByNoHp", query = "SELECT a FROM Anggota a WHERE a.noHp = :noHp")
    , @NamedQuery(name = "Anggota.findByTanggalLahir", query = "SELECT a FROM Anggota a WHERE a.tanggalLahir = :tanggalLahir")
    , @NamedQuery(name = "Anggota.findByTanggalBergabung", query = "SELECT a FROM Anggota a WHERE a.tanggalBergabung = :tanggalBergabung")
    , @NamedQuery(name = "Anggota.findByIsBanned", query = "SELECT a FROM Anggota a WHERE a.isBanned = :isBanned")})
public class Anggota implements Serializable {

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
    @Size(max = 45)
    @Column(name = "no_hp")
    private String noHp;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Column(name = "tanggal_bergabung")
    @Temporal(TemporalType.DATE)
    private Date tanggalBergabung;
    @Column(name = "is_banned")
    private Boolean isBanned;
    @OneToMany(mappedBy = "idAnggota")
    private Collection<Peminjaman> peminjamanCollection;

    public Anggota() {
    }

    public Anggota(String username, String password, String nama, String noHp, String tanggalLahir, String tanggalBergabung, Boolean isBanned) throws ParseException {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.noHp = noHp;
        this.tanggalLahir = new SimpleDateFormat("dd/MM/yyyy").parse(tanggalLahir);
        this.tanggalBergabung = new SimpleDateFormat("dd/MM/yyyy").parse(tanggalBergabung);
        this.isBanned = isBanned;
    }

    public Anggota(Integer id) {
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

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public Date getTanggalBergabung() {
        return tanggalBergabung;
    }

    public void setTanggalBergabung(Date tanggalBergabung) {
        this.tanggalBergabung = tanggalBergabung;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean isBanned) {
        this.isBanned = isBanned;
    }

    public String getTanggalLahirFormatted() {
        return Utill.getDateFormatted(tanggalLahir);
    }

    public String getTanggalBergabungFormatted() {
        return Utill.getDateFormatted(tanggalBergabung);
    }

    public String getIdAliiasFormatted() {
        return "AG - " + getId();
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
        if (!(object instanceof Anggota)) {
            return false;
        }
        Anggota other = (Anggota) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Anggota[ id=" + id + " ]";
    }

}
