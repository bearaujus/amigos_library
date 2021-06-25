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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "buku")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Buku.findAll", query = "SELECT b FROM Buku b")
    , @NamedQuery(name = "Buku.findById", query = "SELECT b FROM Buku b WHERE b.id = :id")
    , @NamedQuery(name = "Buku.findByJudul", query = "SELECT b FROM Buku b WHERE b.judul = :judul")
    , @NamedQuery(name = "Buku.findByHarga", query = "SELECT b FROM Buku b WHERE b.harga = :harga")
    , @NamedQuery(name = "Buku.findByStok", query = "SELECT b FROM Buku b WHERE b.stok = :stok")
    , @NamedQuery(name = "Buku.findByPengarang", query = "SELECT b FROM Buku b WHERE b.pengarang = :pengarang")
    , @NamedQuery(name = "Buku.findByTahunTerbit", query = "SELECT b FROM Buku b WHERE b.tahunTerbit = :tahunTerbit")
    , @NamedQuery(name = "Buku.findByJumlahHalaman", query = "SELECT b FROM Buku b WHERE b.jumlahHalaman = :jumlahHalaman")
    , @NamedQuery(name = "Buku.findByRating", query = "SELECT b FROM Buku b WHERE b.rating = :rating")
    , @NamedQuery(name = "Buku.findByGambarLink", query = "SELECT b FROM Buku b WHERE b.gambarLink = :gambarLink")
    , @NamedQuery(name = "Buku.findByGambarSrc", query = "SELECT b FROM Buku b WHERE b.gambarSrc = :gambarSrc")})
public class Buku implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Size(max = 500)
    @Column(name = "judul")
    private String judul;
    @Column(name = "harga")
    private Integer harga;
    @Column(name = "stok")
    private Integer stok;
    @Size(max = 500)
    @Column(name = "pengarang")
    private String pengarang;
    @Column(name = "tahun_terbit")
    @Temporal(TemporalType.DATE)
    private Date tahunTerbit;
    @Column(name = "jumlah_halaman")
    private Integer jumlahHalaman;
    @Column(name = "rating")
    private Integer rating;
    @Size(max = 500)
    @Column(name = "gambar_link")
    private String gambarLink;
    @Size(max = 500)
    @Column(name = "gambar_src")
    private String gambarSrc;
    @JoinColumn(name = "id_kategori", referencedColumnName = "id")
    @ManyToOne
    private Kategori idKategori;
    @OneToMany(mappedBy = "idBuku")
    private Collection<PeminjamanDetails> peminjamanDetailsCollection;

    public Buku() {
    }

    public Buku(String judul, Integer harga, Integer stok, String pengarang, String tahunTerbit, Integer jumlahHalaman, Integer rating, String gambarLink, String gambarSrc, Kategori idKategori) throws ParseException {
        this.judul = judul;
        this.harga = harga;
        this.stok = stok;
        this.pengarang = pengarang;
        this.tahunTerbit = new SimpleDateFormat("dd/MM/yyyy").parse(tahunTerbit);
        this.jumlahHalaman = jumlahHalaman;
        this.rating = rating;
        this.gambarLink = gambarLink;
        this.gambarSrc = gambarSrc;
        this.idKategori = idKategori;
    }

    public Buku(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public Integer getHarga() {
        return harga;
    }

    public void setHarga(Integer harga) {
        this.harga = harga;
    }

    public Integer getStok() {
        return stok;
    }

    public void setStok(Integer stok) {
        this.stok = stok;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public Date getTahunTerbit() {
        return tahunTerbit;
    }

    public void setTahunTerbit(Date tahunTerbit) {
        this.tahunTerbit = tahunTerbit;
    }

    public Integer getJumlahHalaman() {
        return jumlahHalaman;
    }

    public void setJumlahHalaman(Integer jumlahHalaman) {
        this.jumlahHalaman = jumlahHalaman;
    }

    public Kategori getIdKategori() {
        return idKategori;
    }

    public void setIdKategori(Kategori idKategori) {
        this.idKategori = idKategori;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getGambarLink() {
        return gambarLink;
    }

    public void setGambarLink(String gambarLink) {
        this.gambarLink = gambarLink;
    }

    public String getGambarSrc() {
        return gambarSrc;
    }

    public void setGambarSrc(String gambarSrc) {
        this.gambarSrc = gambarSrc;
    }

    public String getTahunTerbitFormatted() {
        return Utill.getDateFormatted(tahunTerbit);
    }

    public String getTahunTerbitYearOnlyFormatted() {
        return Utill.getYearFormatted(tahunTerbit);
    }

    public String getHargaFormatted() {
        return Utill.getRupiahConverter(harga);
    }

    public String getIdAliiasFormatted() {
        return "BK - " + getId();
    }
    
    public String getRatingFormatted() {
        if (rating <= 0) {
            return "Not rated yet";
        }
        String o = "";
        for (int i = 0; i < rating; i++) {
            o += "⭐";
        }
        return o;
    }

    @XmlTransient
    public Collection<PeminjamanDetails> getPeminjamanDetailsCollection() {
        return peminjamanDetailsCollection;
    }

    public void setPeminjamanDetailsCollection(Collection<PeminjamanDetails> peminjamanDetailsCollection) {
        this.peminjamanDetailsCollection = peminjamanDetailsCollection;
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
        if (!(object instanceof Buku)) {
            return false;
        }
        Buku other = (Buku) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Buku[ id=" + id + " ]";
    }

}
