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
@Table(name = "CENSCORE")
@XmlRootElement

public class Censcore implements Serializable {

    private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name="ID")
	private Cenclientesconsultar id;
    
	@Id
	@SequenceGenerator( allocationSize=1,name="CEN_SCORE", sequenceName="SCORE")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CEN_SCORE")
    @Column(name = "ID_SCORE")
    private Long idScore;
    
    @Column(name = "PI")
    private BigDecimal pi;
    
    @Column(name = "SCORE")
    private Long score;
    
    @Column(name = "EXCLUSION")
    private String exclusion;
    
    public Censcore() {
    }

  

    public Long getIdScore() {
		return idScore;
	}



	public void setIdScore(Long idScore) {
		this.idScore = idScore;
	}



	public BigDecimal getPi() {
		return pi;
	}



	public void setPi(BigDecimal pi) {
		this.pi = pi;
	}



	public Long getScore() {
		return score;
	}



	public void setScore(Long score) {
		this.score = score;
	}



	public String getExclusion() {
		return exclusion;
	}



	public void setExclusion(String exclusion) {
		this.exclusion = exclusion;
	}



	public Cenclientesconsultar getId() {
        return id;
    }

    public void setId(Cenclientesconsultar id) {
        this.id = id;
    }



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((exclusion == null) ? 0 : exclusion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idScore == null) ? 0 : idScore.hashCode());
		result = prime * result + ((pi == null) ? 0 : pi.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Censcore other = (Censcore) obj;
		if (exclusion == null) {
			if (other.exclusion != null)
				return false;
		} else if (!exclusion.equals(other.exclusion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idScore == null) {
			if (other.idScore != null)
				return false;
		} else if (!idScore.equals(other.idScore))
			return false;
		if (pi == null) {
			if (other.pi != null)
				return false;
		} else if (!pi.equals(other.pi))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Censcore [id=" + id + ", idScore=" + idScore + ", pi=" + pi + ", score=" + score + ", exclusion="
				+ exclusion + "]";
	}

    
}
