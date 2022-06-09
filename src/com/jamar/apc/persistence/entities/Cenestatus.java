package com.jamar.apc.persistence.entities;


import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kos
 */
@Entity
@Table(name = "CENESTATUS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenestatus.findAll", query = "SELECT c FROM Cenestatus c")
    , @NamedQuery(name = "Cenestatus.findById", query = "SELECT c FROM Cenestatus c WHERE c.id = :id")
    , @NamedQuery(name = "Cenestatus.findByIdestatus", query = "SELECT c FROM Cenestatus c WHERE c.idestatus = :idestatus")
    , @NamedQuery(name = "Cenestatus.findByEstatus", query = "SELECT c FROM Cenestatus c WHERE c.estatus = :estatus")})
public class Cenestatus implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_ESTATUS", sequenceName="ESTATUS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_ESTATUS")
    @Column(name = "IDESTATUS")
    private Long idestatus;
    @Column(name = "ESTATUS")
    private Short estatus;

    public Cenestatus() {
    }

    public Cenestatus(Long idestatus) {
        this.idestatus = idestatus;
    }


    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdestatus() {
        return idestatus;
    }

    public void setIdestatus(Long idestatus) {
        this.idestatus = idestatus;
    }

    public Short getEstatus() {
        return estatus;
    }

    public void setEstatus(Short estatus) {
        this.estatus = estatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idestatus != null ? idestatus.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenestatus)) {
            return false;
        }
        Cenestatus other = (Cenestatus) object;
        if ((this.idestatus == null && other.idestatus != null) || (this.idestatus != null && !this.idestatus.equals(other.idestatus))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenestatus[ idestatus=" + idestatus + " ]";
    }
    
}
