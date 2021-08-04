package cfagent.agent;

import cfagent.partner.Partner;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AgentService {

    private AgentRepository agentRepository;

    private ModelMapper modelMapper;

    public List<AgentDTO> listAgents(Optional<String> name) {
        return agentRepository.findAll().stream()
                .filter(a -> name.isEmpty() || a.getName().toLowerCase().contains(name.get().toLowerCase()))
                .map(a -> modelMapper.map(a, AgentDTO.class))
                .collect(Collectors.toList());
    }

    public AgentDTO createAgent(CreateAgentCommand command) {
        Agent agent = new Agent(command.getName(), command.getMnbNumber());

        agent.setActive(true);

        agentRepository.save(agent);

        return modelMapper.map(agent, AgentDTO.class);
    }

    @Transactional
    public AgentDTO updateAgent(long id, UpdateAgentCommand command) {
        Agent agent = agentRepository.getById(id);
        if(agent == null){
            throw new IllegalArgumentException("Agent not found!" + id);
        }
        if (!agent.getName().equals(command.getName())
                || !agent.getMnbNumber().equals(command.getMnbNumber())
                || !agent.isActive() == command.isActive()) {

            agent.setName(command.getName());
            agent.setMnbNumber(command.getMnbNumber());
            agent.setActive(command.isActive());

        }
        return modelMapper.map(agent, AgentDTO.class);
    }

    @Transactional
    public AgentDTO addPartnerToAgent(long id, AddPartnerCommand command) {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Agent not found: " + id));
        Partner partner = new Partner(command.getName(), command.isCompany());
        agent.addPartner(partner);
        return modelMapper.map(agent, AgentDTO.class);
    }
}


