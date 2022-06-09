package com.jamar.apc.persistence.entities;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "CENCLIENTESCONSULTAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cenclientesconsultar.findAll", query = "SELECT c FROM Cenclientesconsultar c")
    , @NamedQuery(name = "Cenclientesconsultar.findByTipoidentificacion", query = "SELECT c FROM Cenclientesconsultar c WHERE c.tipoidentificacion = :tipoidentificacion")
    , @NamedQuery(name = "Cenclientesconsultar.findByNumeroidentificacion", query = "SELECT c FROM Cenclientesconsultar c WHERE c.numeroidentificacion = :numeroidentificacion")
    , @NamedQuery(name = "Cenclientesconsultar.findByPrimerapellido", query = "SELECT c FROM Cenclientesconsultar c WHERE c.primerapellido = :primerapellido")
    , @NamedQuery(name = "Cenclientesconsultar.findByCodigo", query = "SELECT c FROM Cenclientesconsultar c WHERE c.codigo = :codigo")
    , @NamedQuery(name = "Cenclientesconsultar.findByControl", query = "SELECT c FROM Cenclientesconsultar c WHERE c.control = :control")
    , @NamedQuery(name = "Cenclientesconsultar.findByFechasalida", query = "SELECT c FROM Cenclientesconsultar c WHERE c.fechasalida = :fechasalida")
    , @NamedQuery(name = "Cenclientesconsultar.findByFechaentrada", query = "SELECT c FROM Cenclientesconsultar c WHERE c.fechaentrada = :fechaentrada")
    , @NamedQuery(name = "Cenclientesconsultar.findById", query = "SELECT c FROM Cenclientesconsultar c WHERE c.id = :id")})
public class Cenclientesconsultar implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "TIPOIDENTIFICACION")
    private short tipoidentificacion;
    @Basic(optional = false)
    @Column(name = "NUMEROIDENTIFICACION")
    private String numeroidentificacion;
    @Column(name = "PRIMERAPELLIDO")
    private String primerapellido;
    @Column(name = "CODIGO")
    private String codigo;
    @Column(name = "CONTROL")
    private Short control;
    @Column(name = "FECHASALIDA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;
    @Column(name = "FECHAENTRADA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaentrada;
    @Column(name = "ORIGEN")
    private String origen;
    @Column(name = "USUARIO")
    private String usuario;
    @Id
	@SequenceGenerator( allocationSize=1,name="CEN_CLIENTES_CONSULTAR", sequenceName="CLIENTESCONSULTAR")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_CLIENTES_CONSULTAR")
    @Column(name = "ID")
    private Long id;
    
  //bi-directional many-to-one association to DatActividadEconomica
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cenresumenes> resumenes;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cendetalles> detalles;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cencanceladas> referenciasCanceladas;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cenvalidacion> validacion;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cenestatus> estatus ;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cengenerales> generales ;
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Cenmovimientos> movimientos ;		
  	@OneToMany(cascade=CascadeType.ALL,mappedBy="id")
  	private List<Censcore> scores ;
  	
  	
  	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<Censcore> getScores() {
		return scores;
	}

	public void setScores(List<Censcore> scores) {
		this.scores = scores;
	}

	public List<Cenmovimientos> getMovimientos() {
		return movimientos;
	}

	public void setMovimientos(List<Cenmovimientos> movimientos) {
		this.movimientos = movimientos;
	}

	public List<Cengenerales> getGenerales() {
		return generales;
	}

	public void setGenerales(List<Cengenerales> generales) {
		this.generales = generales;
	}

	public List<Cenresumenes> getResumenes() {
		return resumenes;
	}

	public void setResumenes(List<Cenresumenes> resumenes) {
		this.resumenes = resumenes;
	}

	public List<Cendetalles> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<Cendetalles> detalles) {
		this.detalles = detalles;
	}

	public List<Cencanceladas> getReferenciasCanceladas() {
		return referenciasCanceladas;
	}

	public void setReferenciasCanceladas(List<Cencanceladas> referenciasCanceladas) {
		this.referenciasCanceladas = referenciasCanceladas;
	}

	public List<Cenvalidacion> getValidacion() {
		return validacion;
	}

	public void setValidacion(List<Cenvalidacion> validacion) {
		this.validacion = validacion;
	}

	public List<Cenestatus> getEstatus() {
		return estatus;
	}

	public void setEstatus(List<Cenestatus> estatus) {
		this.estatus = estatus;
	}

	public Cenclientesconsultar() {
    }

    public Cenclientesconsultar(Long id) {
        this.id = id;
    }

    public Cenclientesconsultar(Long id, short tipoidentificacion, String numeroidentificacion) {
        this.id = id;
        this.tipoidentificacion = tipoidentificacion;
        this.numeroidentificacion = numeroidentificacion;
    }

    public short getTipoidentificacion() {
        return tipoidentificacion;
    }

    public void setTipoidentificacion(short tipoidentificacion) {
        this.tipoidentificacion = tipoidentificacion;
    }

    public String getNumeroidentificacion() {
        return numeroidentificacion;
    }

    public void setNumeroidentificacion(String numeroidentificacion) {
        this.numeroidentificacion = numeroidentificacion;
    }

    public String getPrimerapellido() {
        return primerapellido;
    }

    public void setPrimerapellido(String primerapellido) {
        this.primerapellido = primerapellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Short getControl() {
        return control;
    }

    public void setControl(Short control) {
        this.control = control;
    }

    public Date getFechasalida() {
        return fechasalida;
    }

    public void setFechasalida(Date fechasalida) {
        this.fechasalida = fechasalida;
    }

    public Date getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        if (!(object instanceof Cenclientesconsultar)) {
            return false;
        }
        Cenclientesconsultar other = (Cenclientesconsultar) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.com.neodigital.clovis.configuration.Cenclientesconsultar[ id=" + id + " ]";
    }
    
}
