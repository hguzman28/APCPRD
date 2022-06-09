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
@Table(name = "CENMOVIMIENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenmovimientos.findAll", query = "SELECT c FROM Cenmovimientos c")
    , @NamedQuery(name = "Cenmovimientos.findById", query = "SELECT c FROM Cenmovimientos c WHERE c.id = :id")
    , @NamedQuery(name = "Cenmovimientos.findByIdmovimiento", query = "SELECT c FROM Cenmovimientos c WHERE c.idmovimiento = :idmovimiento")
    , @NamedQuery(name = "Cenmovimientos.findByNomAsoc", query = "SELECT c FROM Cenmovimientos c WHERE c.nomAsoc = :nomAsoc")
    , @NamedQuery(name = "Cenmovimientos.findByFec1", query = "SELECT c FROM Cenmovimientos c WHERE c.fec1 = :fec1")
    , @NamedQuery(name = "Cenmovimientos.findByIdentClie", query = "SELECT c FROM Cenmovimientos c WHERE c.identClie = :identClie")})
public class Cenmovimientos implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_MOVIMIENTOS", sequenceName="MOVIMIENTOS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_MOVIMIENTOS")
    @Column(name = "IDMOVIMIENTO")
    private Long idmovimiento;
    
    @Column(name = "NOM_ASOC")
    private String nomAsoc;
    
    @Column(name = "FEC1")
    private String fec1;
    
    @Column(name = "IDENT_CLIE")
    private String identClie;

    public Cenmovimientos() {
    }

    public Cenmovimientos(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }


    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdmovimiento() {
        return idmovimiento;
    }

    public void setIdmovimiento(Long idmovimiento) {
        this.idmovimiento = idmovimiento;
    }

    public String getNomAsoc() {
        return nomAsoc;
    }

    public void setNomAsoc(String nomAsoc) {
        this.nomAsoc = nomAsoc;
    }

    public String getFec1() {
        return fec1;
    }

    public void setFec1(String fec1) {
        this.fec1 = fec1;
    }

    public String getIdentClie() {
        return identClie;
    }

    public void setIdentClie(String identClie) {
        this.identClie = identClie;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idmovimiento != null ? idmovimiento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenmovimientos)) {
            return false;
        }
        Cenmovimientos other = (Cenmovimientos) object;
        if ((this.idmovimiento == null && other.idmovimiento != null) || (this.idmovimiento != null && !this.idmovimiento.equals(other.idmovimiento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenmovimientos[ idmovimiento=" + idmovimiento + " ]";
    }
    
}
