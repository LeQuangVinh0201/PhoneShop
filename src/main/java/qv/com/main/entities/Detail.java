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
@Table(name="detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Detail {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(250) not null")
	@NotEmpty(message = "information must be entered")
	@Length(max = 250, min =1 , message = "Length is between 1 and 250")
	private String information;
	
	@OneToOne(mappedBy = "detail",cascade = CascadeType.ALL)
    private Telephone telephone;
	
	public Detail( String information, Telephone telephone) {
		this.information = information;
		this.telephone = telephone;
	}
}
