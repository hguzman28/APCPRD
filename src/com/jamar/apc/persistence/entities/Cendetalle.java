package com.jamar.apc.persistence.entities;



import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "CENDETALLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cendetalle.findAll", query = "SELECT c FROM Cendetalle c")
    , @NamedQuery(name = "Cendetalle.findById", query = "SELECT c FROM Cendetalle c WHERE c.id = :id")
    , @NamedQuery(name = "Cendetalle.findByIddetalles", query = "SELECT c FROM Cendetalle c WHERE c.iddetalles = :iddetalles")
    , @NamedQuery(name = "Cendetalle.findByIddetalle", query = "SELECT c FROM Cendetalle c WHERE c.iddetalle = :iddetalle")
    , @NamedQuery(name = "Cendetalle.findByDescrCortaRela", query = "SELECT c FROM Cendetalle c WHERE c.descrCortaRela = :descrCortaRela")
    , @NamedQuery(name = "Cendetalle.findByFecInicioRel", query = "SELECT c FROM Cendetalle c WHERE c.fecInicioRel = :fecInicioRel")
    , @NamedQuery(name = "Cendetalle.findByFecFinRel", query = "SELECT c FROM Cendetalle c WHERE c.fecFinRel = :fecFinRel")
    , @NamedQuery(name = "Cendetalle.findByMontoOriginal", query = "SELECT c FROM Cendetalle c WHERE c.montoOriginal = :montoOriginal")
    , @NamedQuery(name = "Cendetalle.findByNumPagos", query = "SELECT c FROM Cendetalle c WHERE c.numPagos = :numPagos")
    , @NamedQuery(name = "Cendetalle.findByDescrFormaPago", query = "SELECT c FROM Cendetalle c WHERE c.descrFormaPago = :descrFormaPago")
    , @NamedQuery(name = "Cendetalle.findByImportePago", query = "SELECT c FROM Cendetalle c WHERE c.importePago = :importePago")
    , @NamedQuery(name = "Cendetalle.findByFecUltimoPago", query = "SELECT c FROM Cendetalle c WHERE c.fecUltimoPago = :fecUltimoPago")
    , @NamedQuery(name = "Cendetalle.findByMontoUltimoPago", query = "SELECT c FROM Cendetalle c WHERE c.montoUltimoPago = :montoUltimoPago")
    , @NamedQuery(name = "Cendetalle.findByDescrObsCorta", query = "SELECT c FROM Cendetalle c WHERE c.descrObsCorta = :descrObsCorta")
    , @NamedQuery(name = "Cendetalle.findBySaldoActual", query = "SELECT c FROM Cendetalle c WHERE c.saldoActual = :saldoActual")
    , @NamedQuery(name = "Cendetalle.findByNumDiasAtraso", query = "SELECT c FROM Cendetalle c WHERE c.numDiasAtraso = :numDiasAtraso")
    , @NamedQuery(name = "Cendetalle.findByMontoCodificado", query = "SELECT c FROM Cendetalle c WHERE c.montoCodificado = :montoCodificado")
    , @NamedQuery(name = "Cendetalle.findByFecActualizacion", query = "SELECT c FROM Cendetalle c WHERE c.fecActualizacion = :fecActualizacion")
    , @NamedQuery(name = "Cendetalle.findByCodGrupoEcon", query = "SELECT c FROM Cendetalle c WHERE c.codGrupoEcon = :codGrupoEcon")
    , @NamedQuery(name = "Cendetalle.findByTipoAsoc", query = "SELECT c FROM Cendetalle c WHERE c.tipoAsoc = :tipoAsoc")
    , @NamedQuery(name = "Cendetalle.findByNumRefer", query = "SELECT c FROM Cendetalle c WHERE c.numRefer = :numRefer")
    , @NamedQuery(name = "Cendetalle.findByNomAsoc", query = "SELECT c FROM Cendetalle c WHERE c.nomAsoc = :nomAsoc")
    , @NamedQuery(name = "Cendetalle.findByHistoria", query = "SELECT c FROM Cendetalle c WHERE c.historia = :historia")})
public class Cendetalle implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
	@ManyToOne
    @JoinColumn(name = "IDDETALLES")
    private Cendetalles iddetalles;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_DETALLE", sequenceName="DETALLE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_DETALLE")
    @Column(name = "IDDETALLE")
    private Long iddetalle;
    @Column(name = "DESCR_CORTA_RELA")
    private String descrCortaRela;
    @Column(name = "FEC_INICIO_REL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecInicioRel;
    @Column(name = "FEC_FIN_REL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecFinRel;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MONTO_ORIGINAL")
    private BigDecimal montoOriginal;
    @Column(name = "NUM_PAGOS")
    private Short numPagos;
    @Column(name = "DESCR_FORMA_PAGO")
    private String descrFormaPago;
    @Column(name = "IMPORTE_PAGO")
    private BigDecimal importePago;
    @Column(name = "FEC_ULTIMO_PAGO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecUltimoPago;
    @Column(name = "MONTO_ULTIMO_PAGO")
    private BigDecimal montoUltimoPago;
    @Column(name = "DESCR_OBS_CORTA")
    private String descrObsCorta;
    @Column(name = "SALDO_ACTUAL")
    private BigDecimal saldoActual;
    @Column(name = "NUM_DIAS_ATRASO")
    private String numDiasAtraso;
    @Column(name = "MONTO_CODIFICADO")
    private String montoCodificado;
    @Column(name = "FEC_ACTUALIZACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecActualizacion;
    @Column(name = "COD_GRUPO_ECON")
    private String codGrupoEcon;
    @Column(name = "TIPO_ASOC")
    private String tipoAsoc;
    @Column(name = "NUM_REFER")
    private String numRefer;
    @Column(name = "NOM_ASOC")
    private String nomAsoc;
    @Column(name = "HISTORIA")
    private String historia;

    public Cendetalle() {
    }

    public Cendetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }


    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Cendetalles getIddetalles() {
        return iddetalles;
    }

    public void setIddetalles(Cendetalles iddetalles) {
        this.iddetalles = iddetalles;
    }

    public Long getIddetalle() {
        return iddetalle;
    }

    public void setIddetalle(Long iddetalle) {
        this.iddetalle = iddetalle;
    }

    public String getDescrCortaRela() {
        return descrCortaRela;
    }

    public void setDescrCortaRela(String descrCortaRela) {
        this.descrCortaRela = descrCortaRela;
    }

    public Date getFecInicioRel() {
        return fecInicioRel;
    }

    public void setFecInicioRel(Date fecInicioRel) {
        this.fecInicioRel = fecInicioRel;
    }

    public Date getFecFinRel() {
        return fecFinRel;
    }

    public void setFecFinRel(Date fecFinRel) {
        this.fecFinRel = fecFinRel;
    }

    public BigDecimal getMontoOriginal() {
        return montoOriginal;
    }

    public void setMontoOriginal(BigDecimal montoOriginal) {
        this.montoOriginal = montoOriginal;
    }

    public Short getNumPagos() {
        return numPagos;
    }

    public void setNumPagos(Short numPagos) {
        this.numPagos = numPagos;
    }

    public String getDescrFormaPago() {
        return descrFormaPago;
    }

    public void setDescrFormaPago(String descrFormaPago) {
        this.descrFormaPago = descrFormaPago;
    }

    public BigDecimal getImportePago() {
        return importePago;
    }

    public void setImportePago(BigDecimal importePago) {
        this.importePago = importePago;
    }

    public Date getFecUltimoPago() {
        return fecUltimoPago;
    }

    public void setFecUltimoPago(Date fecUltimoPago) {
        this.fecUltimoPago = fecUltimoPago;
    }

    public BigDecimal getMontoUltimoPago() {
        return montoUltimoPago;
    }

    public void setMontoUltimoPago(BigDecimal montoUltimoPago) {
        this.montoUltimoPago = montoUltimoPago;
    }

    public String getDescrObsCorta() {
        return descrObsCorta;
    }

    public void setDescrObsCorta(String descrObsCorta) {
        this.descrObsCorta = descrObsCorta;
    }

    public BigDecimal getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(BigDecimal saldoActual) {
        this.saldoActual = saldoActual;
    }

    public String getNumDiasAtraso() {
        return numDiasAtraso;
    }

    public void setNumDiasAtraso(String numDiasAtraso) {
        this.numDiasAtraso = numDiasAtraso;
    }

    public String getMontoCodificado() {
        return montoCodificado;
    }

    public void setMontoCodificado(String montoCodificado) {
        this.montoCodificado = montoCodificado;
    }

    public Date getFecActualizacion() {
        return fecActualizacion;
    }

    public void setFecActualizacion(Date fecActualizacion) {
        this.fecActualizacion = fecActualizacion;
    }

    public String getCodGrupoEcon() {
        return codGrupoEcon;
    }

    public void setCodGrupoEcon(String codGrupoEcon) {
        this.codGrupoEcon = codGrupoEcon;
    }

    public String getTipoAsoc() {
        return tipoAsoc;
    }

    public void setTipoAsoc(String tipoAsoc) {
        this.tipoAsoc = tipoAsoc;
    }

    public String getNumRefer() {
        return numRefer;
    }

    public void setNumRefer(String numRefer) {
        this.numRefer = numRefer;
    }

    public String getNomAsoc() {
        return nomAsoc;
    }

    public void setNomAsoc(String nomAsoc) {
        this.nomAsoc = nomAsoc;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddetalle != null ? iddetalle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cendetalle)) {
            return false;
        }
        Cendetalle other = (Cendetalle) object;
        if ((this.iddetalle == null && other.iddetalle != null) || (this.iddetalle != null && !this.iddetalle.equals(other.iddetalle))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cendetalle[ iddetalle=" + iddetalle + " ]";
    }
    
}
