/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import assets.Utill;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "peminjaman")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Peminjaman.findAll", query = "SELECT p FROM Peminjaman p")
    , @NamedQuery(name = "Peminjaman.findById", query = "SELECT p FROM Peminjaman p WHERE p.id = :id")
    , @NamedQuery(name = "Peminjaman.findByTanggalPengajuan", query = "SELECT p FROM Peminjaman p WHERE p.tanggalPengajuan = :tanggalPengajuan")
    , @NamedQuery(name = "Peminjaman.findByTotalBukuYangDiajukan", query = "SELECT p FROM Peminjaman p WHERE p.totalBukuYangDiajukan = :totalBukuYangDiajukan")})
public class Peminjaman implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tanggal_pengajuan")
    @Temporal(TemporalType.DATE)
    private Date tanggalPengajuan;
    @Column(name = "total_buku_yang_diajukan")
    private Integer totalBukuYangDiajukan;
    @OneToMany(mappedBy = "idPeminjaman")
    private Collection<PeminjamanDetails> peminjamanDetailsCollection;
    @JoinColumn(name = "id_admin_perpus", referencedColumnName = "id")
    @ManyToOne
    private AdminPerpus idAdminPerpus;
    @JoinColumn(name = "id_anggota", referencedColumnName = "id")
    @ManyToOne
    private Anggota idAnggota;

    public Peminjaman() {
    }

    public Peminjaman(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTanggalPengajuan() {
        return tanggalPengajuan;
    }

    public void setTanggalPengajuan(Date tanggalPengajuan) {
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public Integer getTotalBukuYangDiajukan() {
        return totalBukuYangDiajukan;
    }

    public void setTotalBukuYangDiajukan(Integer totalBukuYangDiajukan) {
        this.totalBukuYangDiajukan = totalBukuYangDiajukan;
    }

    public String getTanggalPengajuanFormatted() {
        return Utill.getDateFormatted(tanggalPengajuan);
    }

    @XmlTransient
    public Collection<PeminjamanDetails> getPeminjamanDetailsCollection() {
        return peminjamanDetailsCollection;
    }

    public void setPeminjamanDetailsCollection(Collection<PeminjamanDetails> peminjamanDetailsCollection) {
        this.peminjamanDetailsCollection = peminjamanDetailsCollection;
    }

    public AdminPerpus getIdAdminPerpus() {
        return idAdminPerpus;
    }

    public void setIdAdminPerpus(AdminPerpus idAdminPerpus) {
        this.idAdminPerpus = idAdminPerpus;
    }

    public Anggota getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(Anggota idAnggota) {
        this.idAnggota = idAnggota;
    }

    public String getIdAliiasFormatted() {
        return "PMN - " + getId();
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
        if (!(object instanceof Peminjaman)) {
            return false;
        }
        Peminjaman other = (Peminjaman) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Peminjaman[ id=" + id + " ]";
    }

}
