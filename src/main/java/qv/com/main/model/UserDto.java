package qv.com.main.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import qv.com.main.entities.ProductCart;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@NotEmpty
	private String userName;
	
	@NotEmpty
	private String password;
	
	private String role;
	
    private ProductCart productcart;
	
}
