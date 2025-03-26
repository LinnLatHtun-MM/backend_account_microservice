package per.llt.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;


@Data
@Schema(name = "Customer", description = "Schema to hold customer and account information")
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "Lin Lat Htun")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "Length of the customer name should be between 5 and 30 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "linnlathtun99.mm@gmail.com")
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Invalid email address")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "0987654321")
    @NotEmpty(message = "Mobile Number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @Schema(description = "Account Details of the customer")
    private AccountDto accountDto;
}
