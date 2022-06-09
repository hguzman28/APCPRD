package com.jamar.apc.persistence.entities;



import java.io.Serializable;
import java.util.List;

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
@Table(name = "CENCANCELADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cencanceladas.findAll", query = "SELECT c FROM Cencanceladas c")
    , @NamedQuery(name = "Cencanceladas.findById", query = "SELECT c FROM Cencanceladas c WHERE c.id = :id")
    , @NamedQuery(name = "Cencanceladas.findByIdcanceladas", query = "SELECT c FROM Cencanceladas c WHERE c.idcanceladas = :idcanceladas")})
public class Cencanceladas implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
	@Id
	@SequenceGenerator( allocationSize=1,name="CEN_CANCELADAS", sequenceName="CANCELADAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_CANCELADAS")
	@Column(name = "IDCANCELADAS")
    private Long idcanceladas;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="idcanceladas")
  	private List<Cenreferenciascanceladas> referenciasCanceladas;

  	
    public List<Cenreferenciascanceladas> getReferenciasCanceladas() {
		return referenciasCanceladas;
	}

	public void setReferenciasCanceladas(List<Cenreferenciascanceladas> referenciasCanceladas) {
		this.referenciasCanceladas = referenciasCanceladas;
	}

	public Cencanceladas() {
    }

    public Cencanceladas(Long idcanceladas) {
        this.idcanceladas = idcanceladas;
    }

  

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdcanceladas() {
        return idcanceladas;
    }

    public void setIdcanceladas(Long idcanceladas) {
        this.idcanceladas = idcanceladas;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcanceladas != null ? idcanceladas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cencanceladas)) {
            return false;
        }
        Cencanceladas other = (Cencanceladas) object;
        if ((this.idcanceladas == null && other.idcanceladas != null) || (this.idcanceladas != null && !this.idcanceladas.equals(other.idcanceladas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cencanceladas[ idcanceladas=" + idcanceladas + " ]";
    }
    
}
