package cfagent;

import cfagent.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerWithAgentDTO {

    private Long id;

    private String name;

    private boolean company;

    private boolean active;

    private AgentOnlyIdDTO agent;

    private List<InsuranceDTO> insurances;
}
