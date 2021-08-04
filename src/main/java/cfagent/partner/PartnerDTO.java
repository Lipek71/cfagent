package cfagent.partner;

import cfagent.address.Address;
import cfagent.agent.Agent;
import cfagent.insurance.Insurance;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {

    private Long id;

    private String name;

    private boolean company;

    private boolean active;
}
