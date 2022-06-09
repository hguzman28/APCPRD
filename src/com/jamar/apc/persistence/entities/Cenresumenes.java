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
@Table(name = "CENRESUMENES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenresumenes.findAll", query = "SELECT c FROM Cenresumenes c")
    , @NamedQuery(name = "Cenresumenes.findById", query = "SELECT c FROM Cenresumenes c WHERE c.id = :id")
    , @NamedQuery(name = "Cenresumenes.findByIdresumenes", query = "SELECT c FROM Cenresumenes c WHERE c.idresumenes = :idresumenes")})
public class Cenresumenes implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_RESUMENES", sequenceName="RESUMENES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_RESUMENES")
    @Column(name = "IDRESUMENES")
    private Long idresumenes;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="idresumenes")
  	private List<Cenresumen> resumenes;
  	
  	
    public List<Cenresumen> getResumenes() {
		return resumenes;
	}

	public void setResumenes(List<Cenresumen> resumenes) {
		this.resumenes = resumenes;
	}

	public Cenresumenes() {
    }

    public Cenresumenes(Long idresumenes) {
        this.idresumenes = idresumenes;
    }

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdresumenes() {
        return idresumenes;
    }

    public void setIdresumenes(Long idresumenes) {
        this.idresumenes = idresumenes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresumenes != null ? idresumenes.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenresumenes)) {
            return false;
        }
        Cenresumenes other = (Cenresumenes) object;
        if ((this.idresumenes == null && other.idresumenes != null) || (this.idresumenes != null && !this.idresumenes.equals(other.idresumenes))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenresumenes[ idresumenes=" + idresumenes + " ]";
    }
    
}
