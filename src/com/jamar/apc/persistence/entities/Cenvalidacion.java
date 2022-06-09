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



@Entity
@Table(name = "CENVALIDACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenvalidacion.findAll", query = "SELECT c FROM Cenvalidacion c")
    , @NamedQuery(name = "Cenvalidacion.findById", query = "SELECT c FROM Cenvalidacion c WHERE c.id = :id")
    , @NamedQuery(name = "Cenvalidacion.findByIdvalidacion", query = "SELECT c FROM Cenvalidacion c WHERE c.idvalidacion = :idvalidacion")
    , @NamedQuery(name = "Cenvalidacion.findByEsValido", query = "SELECT c FROM Cenvalidacion c WHERE c.esValido = :esValido")})
public class Cenvalidacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
	@Id
	@SequenceGenerator( allocationSize=1,name="CEN_VALIDACION", sequenceName="VALIDACION")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_VALIDACION")
    @Column(name = "IDVALIDACION")
    private Long idvalidacion;
    @Column(name = "ES_VALIDO")
    private Short esValido;

    public Cenvalidacion() {
    }

    public Cenvalidacion(Long idvalidacion) {
        this.idvalidacion = idvalidacion;
    }

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdvalidacion() {
        return idvalidacion;
    }

    public void setIdvalidacion(Long idvalidacion) {
        this.idvalidacion = idvalidacion;
    }

    public Short getEsValido() {
        return esValido;
    }

    public void setEsValido(Short esValido) {
        this.esValido = esValido;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalidacion != null ? idvalidacion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenvalidacion)) {
            return false;
        }
        Cenvalidacion other = (Cenvalidacion) object;
        if ((this.idvalidacion == null && other.idvalidacion != null) || (this.idvalidacion != null && !this.idvalidacion.equals(other.idvalidacion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenvalidacion[ idvalidacion=" + idvalidacion + " ]";
    }
    
}
