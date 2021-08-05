package cfagent;

import cfagent.agent.AgentDTO;
import cfagent.insurance.InsuranceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cfagent/queries")
@Tag(name = "Queries.")
public class CfAgentController {

    final private CfAgentService cfAgentService;

    public CfAgentController(CfAgentService cfAgentService) {
        this.cfAgentService = cfAgentService;
    }

    @GetMapping("insurance")
    @Operation(summary = "List insurance company(ies) and its/their insurance(s).")
    public List<InsuranceDTO> listInsurances(@RequestParam Optional<String> company) {
        return cfAgentService.listInsurances(company);
    }

    @GetMapping("agent")
    @Operation(summary = "List agent(s) and her/his partner(s) and her/his insurance(s).")
    public List<AgentDTO> listPartnersAndInsurances(@RequestParam Optional<String> name) {
        return cfAgentService.listPartnersAndInsurances(name);
    }

    @GetMapping("partner")
    @Operation(summary = "List partner(s) and her/his agent and her/his insurance(s).")
    public List<PartnerWithAgentDTO> listPartnerAgentInsurance(@RequestParam Optional<String> name) {
        return cfAgentService.listPartnerAgentInsurance(name);
    }
}
