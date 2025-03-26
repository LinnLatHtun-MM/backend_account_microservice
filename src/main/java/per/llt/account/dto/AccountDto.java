package per.llt.account.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Schema(name = "Account", description = "Schema to hold customer and account information")
public class AccountDto {

    @Schema(description = "Account Number for ABC bank")
    @NotEmpty(message = "Account Number cannot be null or empty.")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account Number for ABC bank", example = "Saving")
    @NotEmpty(message = "Account Type cannot be null or empty.")
    private String accountType;

    @Schema(description = "Branch for ABC bank", example = "Mandalay")
    @NotEmpty(message = "Branch Address cannot be null or empty.")
    private String branchAddress;

}
