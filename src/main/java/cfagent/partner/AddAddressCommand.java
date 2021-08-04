package cfagent.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddAddressCommand {

    @Schema(description = "Postcode", example = "2330")
    @NotBlank(message = "The postcode can't empty.")
    private String postcode;

    @Schema(description = "City", example = "Dunaharaszti")
    @NotBlank(message = "The city can't empty.")
    private String city;

    @Schema(description = "Street and house number", example = "Szőlőhegy u. 55.")
    @NotBlank(message = "The street can't empty.")
    private String street;
}
