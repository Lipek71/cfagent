package cfagent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePartnerCommand {

    @Schema(description = "Partner name", example = "Lipka László")
    @NotBlank
    private String name;

    @Schema(description = "Company", example = "true")
    private boolean company;

    @Schema(description = "Partner status", example = "true")
    private boolean active;
}
