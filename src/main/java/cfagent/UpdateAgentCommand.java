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
public class UpdateAgentCommand {

    @Schema(description = "Agent name", example = "Lipka László")
    @NotBlank
    private String name;

    @Schema(description = "Agent MNB number", example = "12345678901")
    @Pattern(regexp ="[0-9]*" , message = "Only number")
    @Size(min = 11, max = 11, message = "Size is 11")
    private String mnbNumber;

    @Schema(description = "Agent status", example = "true")
    private boolean active;
}
