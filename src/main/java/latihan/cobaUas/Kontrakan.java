/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latihan.cobaUas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ROG
 */
@Entity
@Table(name = "kontrakan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Kontrakan.findAll", query = "SELECT k FROM Kontrakan k"),
    @NamedQuery(name = "Kontrakan.findById", query = "SELECT k FROM Kontrakan k WHERE k.id = :id"),
    @NamedQuery(name = "Kontrakan.findByNama", query = "SELECT k FROM Kontrakan k WHERE k.nama = :nama"),
    @NamedQuery(name = "Kontrakan.findByNik", query = "SELECT k FROM Kontrakan k WHERE k.nik = :nik"),
    @NamedQuery(name = "Kontrakan.findByTanggalLahir", query = "SELECT k FROM Kontrakan k WHERE k.tanggalLahir = :tanggalLahir"),
    @NamedQuery(name = "Kontrakan.findByTimestamp", query = "SELECT k FROM Kontrakan k WHERE k.timestamp = :timestamp")})
public class Kontrakan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nama")
    private String nama;
    @Column(name = "nik")
    private Integer nik;
    @Column(name = "tanggal_lahir")
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    @Lob
    @Column(name = "file")
    private byte[] file;
    @Basic(optional = false)
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    public Kontrakan() {
    }

    public Kontrakan(Integer id) {
        this.id = id;
    }

    public Kontrakan(Integer id, Date timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getNik() {
        return nik;
    }

    public void setNik(Integer nik) {
        this.nik = nik;
    }

    public Date getTanggalLahir() {
        return tanggalLahir;
    }

    public void setTanggalLahir(Date tanggalLahir) {
        this.tanggalLahir = tanggalLahir;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
        if (!(object instanceof Kontrakan)) {
            return false;
        }
        Kontrakan other = (Kontrakan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "latihan.cobaUas.Kontrakan[ id=" + id + " ]";
    }
    
}
