package qv.com.main.entities;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@Column(columnDefinition = "nvarchar(50)")
	private String userName;
	
	@Column(columnDefinition = "nvarchar(250) not null")
	@NotEmpty(message = "password must be entered")
	@Length(max = 250, min =1 , message = "Length is between 1 and 250")
	private String password;
	
	@Column(columnDefinition = "nvarchar(20)")
	private String role;
	
	@Column(columnDefinition = "nvarchar(20)")
	private String phoneNumber;
	
	@Column(columnDefinition = "nvarchar(200)")
	private String email;
	
	@OneToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, orphanRemoval=true)
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private ProductCart productcart;
	
	@OneToMany(mappedBy ="user", cascade =CascadeType.ALL)
	Set<Comment> comments;
	
	
}
