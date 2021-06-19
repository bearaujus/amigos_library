/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import assets.Utill;
import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "peminjaman_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeminjamanDetails.findAll", query = "SELECT p FROM PeminjamanDetails p")
    , @NamedQuery(name = "PeminjamanDetails.findById", query = "SELECT p FROM PeminjamanDetails p WHERE p.id = :id")
    , @NamedQuery(name = "PeminjamanDetails.findByTanggalPengambilan", query = "SELECT p FROM PeminjamanDetails p WHERE p.tanggalPengambilan = :tanggalPengambilan")
    , @NamedQuery(name = "PeminjamanDetails.findByTanggalPengembalian", query = "SELECT p FROM PeminjamanDetails p WHERE p.tanggalPengembalian = :tanggalPengembalian")
    , @NamedQuery(name = "PeminjamanDetails.findByStatusKonfirmasi", query = "SELECT p FROM PeminjamanDetails p WHERE p.statusKonfirmasi = :statusKonfirmasi")})
public class PeminjamanDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "tanggal_pengambilan")
    @Temporal(TemporalType.DATE)
    private Date tanggalPengambilan;
    @Column(name = "tanggal_pengembalian")
    @Temporal(TemporalType.DATE)
    private Date tanggalPengembalian;
    @Column(name = "status_konfirmasi")
    private Boolean statusKonfirmasi;
    @JoinColumn(name = "id_buku", referencedColumnName = "id")
    @ManyToOne
    private Buku idBuku;
    @JoinColumn(name = "id_denda", referencedColumnName = "id")
    @ManyToOne
    private Denda idDenda;
    @JoinColumn(name = "id_peminjaman", referencedColumnName = "id")
    @ManyToOne
    private Peminjaman idPeminjaman;

    public PeminjamanDetails() {
    }

    public PeminjamanDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getTanggalPengembalian() {
        return tanggalPengembalian;
    }

    public Date getTanggalPengambilan() {
        return tanggalPengambilan;
    }

    public void setTanggalPengambilan(Date tanggalPengambilan) {
        this.tanggalPengambilan = tanggalPengambilan;
    }

    public void setTanggalPengembalian(Date tanggalPengembalian) {
        this.tanggalPengembalian = tanggalPengembalian;
    }

    public Boolean getStatusKonfirmasi() {
        return statusKonfirmasi;
    }

    public void setStatusKonfirmasi(Boolean statusKonfirmasi) {
        this.statusKonfirmasi = statusKonfirmasi;
    }

    public Buku getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(Buku idBuku) {
        this.idBuku = idBuku;
    }

    public Denda getIdDenda() {
        return idDenda;
    }

    public void setIdDenda(Denda idDenda) {
        this.idDenda = idDenda;
    }

    public Peminjaman getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(Peminjaman idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public String getTanggalPengambilanFormatted() {
        return Utill.getDateFormatted(tanggalPengambilan);
    }

    public String getTanggalPengembalianFormatted() {
        return Utill.getDateFormatted(tanggalPengembalian);
    }

    public Integer getLamaPeminjaman() {
        if (!statusKonfirmasi) {
            return null;
        } else if (tanggalPengembalian == null) {
            return Utill.getDiffDays(tanggalPengambilan, new Date());
        }
        return Utill.getDiffDays(tanggalPengambilan, tanggalPengembalian);
    }

    public String getIdAliiasFormatted() {
        return "PD - " + getId();
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
        if (!(object instanceof PeminjamanDetails)) {
            return false;
        }
        PeminjamanDetails other = (PeminjamanDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.PeminjamanDetails[ id=" + id + " ]";
    }

}
