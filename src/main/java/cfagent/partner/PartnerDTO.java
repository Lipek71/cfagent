package cfagent.partner;

import cfagent.insurance.InsuranceDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartnerDTO {

    private Long id;

    private String name;

    private boolean company;

    private boolean active;

    private List<InsuranceDTO> insurances;
}
