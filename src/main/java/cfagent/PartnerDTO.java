package cfagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {

    private Long id;

    private String name;

    private boolean company;

    private Long addresId;

    private long agentId;

    private Long insurenceId;

    private boolean active;
}
