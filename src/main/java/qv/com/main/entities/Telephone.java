package qv.com.main.entities;



import java.util.ArrayList;
import java.util.List;
import java.util.Set;


import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="telephones")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Telephone {
	
	@Id
	@Column(columnDefinition = "nvarchar(50)")
	private String masp;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	@NotEmpty(message = "Name must be entered")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String name;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	@NotEmpty(message = "pictureUrl must be entered")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String pictureUrl;
	
	@Column(columnDefinition = "nvarchar(50) not null")
	@NotEmpty(message = "series must be entered")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String series;
	
	@Column(columnDefinition = "nvarchar(50)")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String createdBy;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String createdAt;
	
	@Column(columnDefinition = "nvarchar(50)")
	@Length(max = 50, min =1 , message = "Length is between 1 and 50")
	private String updatedBy;
	
	@Column(columnDefinition = "nvarchar(50)")
	private String updatedAt;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy ="telephone", cascade =CascadeType.ALL)
	private List<Edition> editions;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade =CascadeType.ALL)
	@JoinColumn(name = "id")
	private Brand brand;
	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id", referencedColumnName = "id")
    private Detail detail;
	
	public void addDetailForTelephone(String information) {
		this.setDetail(new Detail(information,this));
	}
	
	@Transient
	public String getPhotoUrlPath() {
		if (pictureUrl == null || masp == null) {
			return null;
		}
		
		return "/uploads/" + pictureUrl;
		
	}
//	@PrePersist   //duoc tao ra truoc khi luu thong tin vao du lieu database
//	public void preCreate() {
//		createdAt = new Date();
//	}
}
