package qv.com.main.entities;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="edition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Edition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinColumn(name = "maspId")
	private Telephone telephone;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@Column(nullable = false)
	private Long price;
	
	@Column
	private Long discount;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="edition", cascade =CascadeType.ALL)
	private List<Revenue> revenues;
	
	
//	@OneToOne(mappedBy = "edition",cascade = CascadeType.ALL)
//    private Comment comment;
}
