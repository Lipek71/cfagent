package cfagent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateAddressCommand {

    @Schema(description = "Postcode", example = "2330")
    @NotBlank
    private String postcode;

    @Schema(description = "City", example = "Dunaharaszti")
    @NotBlank
    private String city;

    @Schema(description = "Street and house number", example = "Szőlőhegy u. 55.")
    @NotBlank
    private String street;
}
