package cfagent.partner;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddInsuranceCommand {

    @Schema(description = "Insurance company", example = "Allianz Biztosító Zrt.")
    @NotBlank(message = "The company can't empty.")
    private String company;

    @Schema(description = "Type", example = "vagyonbiztosítás")
    @NotBlank(message = "The type can't empty.")
    private String type;

    @Schema(description = "Insurance", example = "Autóm CASCO")
    @NotBlank(message = "The insurance can't empty.")
    private String insurance;

    @Schema(description = "Active", example = "true")
    private boolean active;
}
