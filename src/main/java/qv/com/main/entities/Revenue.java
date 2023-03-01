package qv.com.main.entities;

import java.util.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="revenue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Revenue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String username;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	private String phonename;
	
	@Column(nullable = false)
	private Integer capacity;
	
	@Column(nullable = false)
	private Integer editionId;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(nullable = false)
	private Long sellprice;
	
	@Column(nullable = false)
	private Long total;
	
	@Temporal(TemporalType.DATE)
	private Date selldate;
	
	
}
