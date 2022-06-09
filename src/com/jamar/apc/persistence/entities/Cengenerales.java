package com.jamar.apc.persistence.entities;




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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kos
 */
@Entity
@Table(name = "CENGENERALES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cengenerales.findAll", query = "SELECT c FROM Cengenerales c")
    , @NamedQuery(name = "Cengenerales.findById", query = "SELECT c FROM Cengenerales c WHERE c.id = :id")
    , @NamedQuery(name = "Cengenerales.findByIdgenerales", query = "SELECT c FROM Cengenerales c WHERE c.idgenerales = :idgenerales")
    , @NamedQuery(name = "Cengenerales.findByNombre", query = "SELECT c FROM Cengenerales c WHERE c.nombre = :nombre")
    , @NamedQuery(name = "Cengenerales.findByApellido", query = "SELECT c FROM Cengenerales c WHERE c.apellido = :apellido")
    , @NamedQuery(name = "Cengenerales.findByIdentClie", query = "SELECT c FROM Cengenerales c WHERE c.identClie = :identClie")
    , @NamedQuery(name = "Cengenerales.findByFecCreacion", query = "SELECT c FROM Cengenerales c WHERE c.fecCreacion = :fecCreacion")
    , @NamedQuery(name = "Cengenerales.findByNomAsoc", query = "SELECT c FROM Cengenerales c WHERE c.nomAsoc = :nomAsoc")
    , @NamedQuery(name = "Cengenerales.findByFecDefuncion", query = "SELECT c FROM Cengenerales c WHERE c.fecDefuncion = :fecDefuncion")})
public class Cengenerales implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_GENERALES", sequenceName="GENERALES")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_GENERALES")
    @Column(name = "IDGENERALES")
    private Long idgenerales;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "IDENT_CLIE")
    private String identClie;
    @Column(name = "FEC_CREACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @Column(name = "NOM_ASOC")
    private String nomAsoc;
    @Column(name = "FEC_DEFUNCION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecDefuncion;

    public Cengenerales() {
    }

    public Cengenerales(Long idgenerales) {
        this.idgenerales = idgenerales;
    }

    

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Long getIdgenerales() {
        return idgenerales;
    }

    public void setIdgenerales(Long idgenerales) {
        this.idgenerales = idgenerales;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getIdentClie() {
        return identClie;
    }

    public void setIdentClie(String identClie) {
        this.identClie = identClie;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public String getNomAsoc() {
        return nomAsoc;
    }

    public void setNomAsoc(String nomAsoc) {
        this.nomAsoc = nomAsoc;
    }

    public Date getFecDefuncion() {
        return fecDefuncion;
    }

    public void setFecDefuncion(Date fecDefuncion) {
        this.fecDefuncion = fecDefuncion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgenerales != null ? idgenerales.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cengenerales)) {
            return false;
        }
        Cengenerales other = (Cengenerales) object;
        if ((this.idgenerales == null && other.idgenerales != null) || (this.idgenerales != null && !this.idgenerales.equals(other.idgenerales))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cengenerales[ idgenerales=" + idgenerales + " ]";
    }
    
}
