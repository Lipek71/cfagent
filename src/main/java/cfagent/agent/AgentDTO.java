package cfagent.agent;

import cfagent.partner.PartnerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private Long id;

    private String name;

    private String mnbNumber;

    private boolean active;

    private List<PartnerDTO> partners;
}
