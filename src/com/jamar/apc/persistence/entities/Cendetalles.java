package com.jamar.apc.persistence.entities;


import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kos
 */
@Entity
@Table(name = "CENDETALLES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cendetalles.findAll", query = "SELECT c FROM Cendetalles c")
    , @NamedQuery(name = "Cendetalles.findById", query = "SELECT c FROM Cendetalles c WHERE c.id = :id")
    , @NamedQuery(name = "Cendetalles.findByIddetalles", query = "SELECT c FROM Cendetalles c WHERE c.iddetalles = :iddetalles")})
public class Cendetalles implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_DETALLES", sequenceName="DETALLES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_DETALLES")
    @Column(name = "IDDETALLES")
    private Long iddetalles;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="iddetalles")
  	private List<Cendetalle> detalles;

    public Cendetalles() {
    }

    public Cendetalles(Long iddetalles) {
        this.iddetalles = iddetalles;
    }

    public List<Cendetalle> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Cendetalle> detalles) {
		this.detalles = detalles;
	}

	public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIddetalles() {
        return iddetalles;
    }

    public void setIddetalles(Long iddetalles) {
        this.iddetalles = iddetalles;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalles != null ? iddetalles.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cendetalles)) {
            return false;
        }
        Cendetalles other = (Cendetalles) object;
        if ((this.iddetalles == null && other.iddetalles != null) || (this.iddetalles != null && !this.iddetalles.equals(other.iddetalles))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cendetalles[ iddetalles=" + iddetalles + " ]";
    }
    
}
