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
@Table(name = "CENREFERENCIASCANCELADAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenreferenciascanceladas.findAll", query = "SELECT c FROM Cenreferenciascanceladas c")
    , @NamedQuery(name = "Cenreferenciascanceladas.findById", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.id = :id")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByIdcanceladas", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.idcanceladas = :idcanceladas")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByIdreferenciascanceladas", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.idreferenciascanceladas = :idreferenciascanceladas")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByNomAsoc", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.nomAsoc = :nomAsoc")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByDescrCortaRela", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.descrCortaRela = :descrCortaRela")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByFecInicioRel", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.fecInicioRel = :fecInicioRel")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByFecFinRel", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.fecFinRel = :fecFinRel")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByMontoOriginal", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.montoOriginal = :montoOriginal")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByFecLiquidacion", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.fecLiquidacion = :fecLiquidacion")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByHistoria", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.historia = :historia")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByDescrObsCorta", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.descrObsCorta = :descrObsCorta")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByMontoCodificado", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.montoCodificado = :montoCodificado")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByCodGrupoEcon", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.codGrupoEcon = :codGrupoEcon")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByTipoAsoc", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.tipoAsoc = :tipoAsoc")
    , @NamedQuery(name = "Cenreferenciascanceladas.findByNumRefer", query = "SELECT c FROM Cenreferenciascanceladas c WHERE c.numRefer = :numRefer")})
public class Cenreferenciascanceladas implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
	@ManyToOne
	@JoinColumn(name = "IDCANCELADAS")
    private Cencanceladas idcanceladas;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_REF_CANCELADAS", sequenceName="REFERENCIASCANCELADAS")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_REF_CANCELADAS")
    @Column(name = "IDREFERENCIASCANCELADAS")
    private Long idreferenciascanceladas;
    @Column(name = "NOM_ASOC")
    private String nomAsoc;
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
    @Column(name = "FEC_LIQUIDACION")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecLiquidacion;
    @Column(name = "HISTORIA")
    private String historia;
    @Column(name = "DESCR_OBS_CORTA")
    private String descrObsCorta;
    @Column(name = "MONTO_CODIFICADO")
    private String montoCodificado;
    @Column(name = "COD_GRUPO_ECON")
    private String codGrupoEcon;
    @Column(name = "TIPO_ASOC")
    private String tipoAsoc;
    @Column(name = "NUM_REFER")
    private String numRefer;

    public Cenreferenciascanceladas() {
    }

    public Cenreferenciascanceladas(Long idreferenciascanceladas) {
        this.idreferenciascanceladas = idreferenciascanceladas;
    }

 

    public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }

    public Cencanceladas getIdcanceladas() {
        return idcanceladas;
    }

    public void setIdcanceladas(Cencanceladas idcanceladas) {
        this.idcanceladas = idcanceladas;
    }

    public Long getIdreferenciascanceladas() {
        return idreferenciascanceladas;
    }

    public void setIdreferenciascanceladas(Long idreferenciascanceladas) {
        this.idreferenciascanceladas = idreferenciascanceladas;
    }

    public String getNomAsoc() {
        return nomAsoc;
    }

    public void setNomAsoc(String nomAsoc) {
        this.nomAsoc = nomAsoc;
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

    public Date getFecLiquidacion() {
        return fecLiquidacion;
    }

    public void setFecLiquidacion(Date fecLiquidacion) {
        this.fecLiquidacion = fecLiquidacion;
    }

    public String getHistoria() {
        return historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }

    public String getDescrObsCorta() {
        return descrObsCorta;
    }

    public void setDescrObsCorta(String descrObsCorta) {
        this.descrObsCorta = descrObsCorta;
    }

    public String getMontoCodificado() {
        return montoCodificado;
    }

    public void setMontoCodificado(String montoCodificado) {
        this.montoCodificado = montoCodificado;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idreferenciascanceladas != null ? idreferenciascanceladas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cenreferenciascanceladas)) {
            return false;
        }
        Cenreferenciascanceladas other = (Cenreferenciascanceladas) object;
        if ((this.idreferenciascanceladas == null && other.idreferenciascanceladas != null) || (this.idreferenciascanceladas != null && !this.idreferenciascanceladas.equals(other.idreferenciascanceladas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenreferenciascanceladas[ idreferenciascanceladas=" + idreferenciascanceladas + " ]";
    }
    
}
