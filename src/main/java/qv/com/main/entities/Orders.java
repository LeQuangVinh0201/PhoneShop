package qv.com.main.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false)
	private Integer quantity;
	
//	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST) // no moi xoa dc thang child
	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "cartId")
	private ProductCart productcart;
	
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "edition_id", referencedColumnName = "id")
    private Edition edition;

}
