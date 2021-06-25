/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import assets.Utill;
import java.io.Serializable;
import java.util.Collection;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Bear Au Jus - ジュースとくま
 */
@Entity
@Table(name = "denda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Denda.findAll", query = "SELECT d FROM Denda d")
    , @NamedQuery(name = "Denda.findById", query = "SELECT d FROM Denda d WHERE d.id = :id")
    , @NamedQuery(name = "Denda.findByKeterangan", query = "SELECT d FROM Denda d WHERE d.keterangan = :keterangan")
    , @NamedQuery(name = "Denda.findByTotalDenda", query = "SELECT d FROM Denda d WHERE d.totalDenda = :totalDenda")})
public class Denda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "keterangan")
    private String keterangan;
    @Column(name = "total_denda")
    private Integer totalDenda;
    @OneToMany(mappedBy = "idDenda")
    private Collection<PeminjamanDetails> peminjamanDetailsCollection;

    public Denda() {
    }

    public Denda(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTotalDenda() {
        return totalDenda;
    }

    public void setTotalDenda(Integer totalDenda) {
        this.totalDenda = totalDenda;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTotalDendaFormatted() {
        return Utill.getRupiahConverter(totalDenda);
    }

    public String getIdAliiasFormatted() {
        return "DD - " + getId();
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
        if (!(object instanceof Denda)) {
            return false;
        }
        Denda other = (Denda) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Denda[ id=" + id + " ]";
    }

}
