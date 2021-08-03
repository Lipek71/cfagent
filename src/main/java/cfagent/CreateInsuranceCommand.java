package cfagent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateInsuranceCommand {

    @Schema(description = "Insurance company", example = "Allianz Biztosító Zrt.")
    @NotBlank
    private String company;

    @Schema(description = "Type", example = "vagyonbiztosítás")
    @NotBlank
    private String type;

    @Schema(description = "Insurance", example = "Autóm CASCO")
    @NotBlank
    private String insurance;

    @Schema(description = "Active", example = "true")
    private boolean active;
}
