package cfagent;

import cfagent.agent.AgentDTO;
import cfagent.agent.AgentRepository;
import cfagent.insurance.InsuranceDTO;
import cfagent.insurance.InsuranceReposity;
import cfagent.partner.PartnerRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CfAgentService {

    private InsuranceReposity insuranceReposity;

    private AgentRepository agentRepository;

    private PartnerRepository partnerRepository;

    private ModelMapper modelMapper;

    public List<InsuranceDTO> listInsurances(Optional<String> company) {
        return insuranceReposity.findAll(Sort.by(Sort.Direction.ASC, "company")).stream()
                .filter(i -> company.isEmpty() || i.getCompany().toLowerCase().contains(company.get().toLowerCase()))
                .map(i -> modelMapper.map(i, InsuranceDTO.class))
                .collect(Collectors.toList());
    }

    public List<AgentDTO> listPartnersAndInsurances(Optional<String> name) {
        return agentRepository.findAll(Sort.by(Sort.Direction.ASC, "name")).stream()
                .filter(a -> name.isEmpty() || a.getName().toLowerCase().contains(name.get().toLowerCase()))
                .map(a -> modelMapper.map(a, AgentDTO.class))
                .collect(Collectors.toList());
    }

    public List<PartnerWithAgentDTO> listPartnerAgentInsurance(Optional<String> name) {
        return partnerRepository.getPartnerAgentInsurance().stream()
                .filter(a -> name.isEmpty() || a.getName().toLowerCase().contains(name.get().toLowerCase()))
                .map(a -> modelMapper.map(a, PartnerWithAgentDTO.class))
                .collect(Collectors.toList());
    }
}
