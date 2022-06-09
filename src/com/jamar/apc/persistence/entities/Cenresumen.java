package com.jamar.apc.persistence.entities;


import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CENRESUMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenresumen.findAll", query = "SELECT c FROM Cenresumen c")
    , @NamedQuery(name = "Cenresumen.findById", query = "SELECT c FROM Cenresumen c WHERE c.id = :id")
    , @NamedQuery(name = "Cenresumen.findByIdresumenes", query = "SELECT c FROM Cenresumen c WHERE c.idresumenes = :idresumenes")
    , @NamedQuery(name = "Cenresumen.findByIdresumen", query = "SELECT c FROM Cenresumen c WHERE c.idresumen = :idresumen")
    , @NamedQuery(name = "Cenresumen.findByRelacion", query = "SELECT c FROM Cenresumen c WHERE c.relacion = :relacion")
    , @NamedQuery(name = "Cenresumen.findByCantidad", query = "SELECT c FROM Cenresumen c WHERE c.cantidad = :cantidad")
    , @NamedQuery(name = "Cenresumen.findByMonto", query = "SELECT c FROM Cenresumen c WHERE c.monto = :monto")
    , @NamedQuery(name = "Cenresumen.findBySaldoActual", query = "SELECT c FROM Cenresumen c WHERE c.saldoActual = :saldoActual")})
public class Cenresumen implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
	@ManyToOne
	@JoinColumn(name = "IDRESUMENES")
    private Cenresumenes idresumenes;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_RESUMEN", sequenceName="RESUMEN")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_RESUMEN")
    @Column(name = "IDRESUMEN")
    private Long idresumen;
    @Column(name = "RELACION")
    private String relacion;
    @Column(name = "CANTIDAD")
    private Integer cantidad;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO")
    private BigDecimal monto;
    @Column(name = "SALDO_ACTUAL")
    private BigDecimal saldoActual;

    public Cenresumen() {
    }

    public Cenresumen(Long idresumen) {
        this.idresumen = idresumen;
    }

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Cenresumenes getIdresumenes() {
        return idresumenes;
    }

    public void setIdresumenes(Cenresumenes idresumenes) {
        this.idresumenes = idresumenes;
    }

    public Long getIdresumen() {
        return idresumen;
    }

    public void setIdresumen(Long idresumen) {
        this.idresumen = idresumen;
    }

    public String getRelacion() {
        return relacion;
    }

    public void setRelacion(String relacion) {
        this.relacion = relacion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idresumen != null ? idresumen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenresumen)) {
            return false;
        }
        Cenresumen other = (Cenresumen) object;
        if ((this.idresumen == null && other.idresumen != null) || (this.idresumen != null && !this.idresumen.equals(other.idresumen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenresumen[ idresumen=" + idresumen + " ]";
    }
    
}
