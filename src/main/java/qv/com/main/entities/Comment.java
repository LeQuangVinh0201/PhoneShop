package qv.com.main.entities;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="comment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "nvarchar(250) not null")
	@NotEmpty(message = "content must be entered")
	@Length(max = 250, min =1 , message = "Length is between 1 and 250")
	private String contents;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	@NotEmpty(message = "status must be entered")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String status;
	
	@ManyToOne()
	@JoinColumn(name = "userNameId")
	User user;
	
//	@OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "edition_id", referencedColumnName = "id")
//    private Edition edition;
	
	@Column(nullable = false)
	private Integer edition_id;
}
