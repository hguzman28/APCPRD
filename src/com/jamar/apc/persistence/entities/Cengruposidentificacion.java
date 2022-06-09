package com.jamar.apc.persistence.entities;




import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kos
 */
@Entity
@Table(name = "CENGRUPOSIDENTIFICACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cengruposidentificacion.findAll", query = "SELECT c FROM Cengruposidentificacion c")
    , @NamedQuery(name = "Cengruposidentificacion.findById", query = "SELECT c FROM Cengruposidentificacion c WHERE c.id = :id")
    , @NamedQuery(name = "Cengruposidentificacion.findByFecha", query = "SELECT c FROM Cengruposidentificacion c WHERE c.fecha = :fecha")
    , @NamedQuery(name = "Cengruposidentificacion.findByTipo", query = "SELECT c FROM Cengruposidentificacion c WHERE c.tipo = :tipo")
    , @NamedQuery(name = "Cengruposidentificacion.findByIdentificacion", query = "SELECT c FROM Cengruposidentificacion c WHERE c.identificacion = :identificacion")
    , @NamedQuery(name = "Cengruposidentificacion.findByTipoidentificacion", query = "SELECT c FROM Cengruposidentificacion c WHERE c.tipoidentificacion = :tipoidentificacion")
    , @NamedQuery(name = "Cengruposidentificacion.findByTipocomentario", query = "SELECT c FROM Cengruposidentificacion c WHERE c.tipocomentario = :tipocomentario")
    , @NamedQuery(name = "Cengruposidentificacion.findByFechavencimientocomentario", query = "SELECT c FROM Cengruposidentificacion c WHERE c.fechavencimientocomentario = :fechavencimientocomentario")
    , @NamedQuery(name = "Cengruposidentificacion.findByIdclientesconsultar", query = "SELECT c FROM Cengruposidentificacion c WHERE c.idclientesconsultar = :idclientesconsultar")})
public class Cengruposidentificacion implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "FECHA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "TIPO")
    private Short tipo;
    @Column(name = "IDENTIFICACION")
    private String identificacion;
    @Column(name = "TIPOIDENTIFICACION")
    private Short tipoidentificacion;
    @Column(name = "TIPOCOMENTARIO")
    private String tipocomentario;
    @Column(name = "FECHAVENCIMIENTOCOMENTARIO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechavencimientocomentario;
    @Basic(optional = false)
    @Column(name = "IDCLIENTESCONSULTAR")
    private BigInteger idclientesconsultar;

    public Cengruposidentificacion() {
    }

    public Cengruposidentificacion(BigDecimal id) {
        this.id = id;
    }

    public Cengruposidentificacion(BigDecimal id, BigInteger idclientesconsultar) {
        this.id = id;
        this.idclientesconsultar = idclientesconsultar;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Short getTipo() {
        return tipo;
    }

    public void setTipo(Short tipo) {
        this.tipo = tipo;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public Short getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(Short tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getTipocomentario() {
        return tipocomentario;
    }

    public void setTipocomentario(String tipocomentario) {
        this.tipocomentario = tipocomentario;
    }

    public Date getFechavencimientocomentario() {
        return fechavencimientocomentario;
    }

    public void setFechavencimientocomentario(Date fechavencimientocomentario) {
        this.fechavencimientocomentario = fechavencimientocomentario;
    }

    public BigInteger getIdclientesconsultar() {
        return idclientesconsultar;
    }

    public void setIdclientesconsultar(BigInteger idclientesconsultar) {
        this.idclientesconsultar = idclientesconsultar;
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
        if (!(object instanceof Cengruposidentificacion)) {
            return false;
        }
        Cengruposidentificacion other = (Cengruposidentificacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cengruposidentificacion[ id=" + id + " ]";
    }
    
}
