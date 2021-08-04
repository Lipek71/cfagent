package cfagent.partner;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePartnerCommand {

    @Schema(description = "Partner name", example = "John Doe")
    @NotBlank(message = "The name can't empty.")
    private String name;

    @Schema(description = "Company", example = "false")
    private boolean company;

    @Schema(description = "Partner status", example = "true")
    private boolean active;
}
