package qv.com.main.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="productcart")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductCart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer quantity;
	
	@Column(columnDefinition = "nvarchar(20) not null")
	@NotEmpty(message = "status must be entered")
	@Length(max = 20, min =1 , message = "Length is between 1 and 20")
	private String status;
	
	@OneToOne(mappedBy = "productcart",cascade = CascadeType.ALL)
    private User user;
	
	@Column(columnDefinition = "int NOT NULL UNIQUE")
	private Integer edition_id;
}
