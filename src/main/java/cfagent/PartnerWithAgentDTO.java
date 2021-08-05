package cfagent;

import cfagent.insurance.InsuranceDTO;

import java.util.List;

public class PartnerWithAgentDTO {

    private Long id;

    private String name;

    private boolean company;

    private boolean active;

    private AgentOnlyIdDTO agent;

    private List<InsuranceDTO> insurances;
}
