package qv.com.main.entities;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="revenues")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer storage;
	
	@Column()
	private Integer sold;
	
	@Temporal(TemporalType.DATE)
	private Date selldate;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinColumn(name = "editionId")
	private Edition edition;
	
	
}
