package qv.com.main.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qv.com.main.entities.ProductCart;
import qv.com.main.entities.Telephone;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditionDto {
	
	private Integer id;
		
	private Integer capacity;
	
	private Long price;
	
	private Long discount;
	
	private String maspId;
}
