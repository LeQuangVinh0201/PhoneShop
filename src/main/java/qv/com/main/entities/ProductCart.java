package qv.com.main.entities;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	
	@Column(columnDefinition = "nvarchar(20) not null")
	@NotEmpty(message = "status must be entered")
	@Length(max = 20, min =1 , message = "Length is between 1 and 20")
	private String status;
	
	@OneToOne(mappedBy = "productcart",cascade = CascadeType.ALL)
    private User user;
	
	//@OneToMany(fetch = FetchType.EAGER, mappedBy ="productcart", cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval=true) //co the xoa dc
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="productcart",cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval=true) //co the xoa va edit duoc
	private List<Orders> orders;
	
	public void removeOrder(Orders order) {
		orders.remove(order);
    }
}
