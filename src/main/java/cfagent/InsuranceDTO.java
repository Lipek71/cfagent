package cfagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsuranceDTO {

    private Long id;

    private String company;

    private String type;

    private String insurance;

    private boolean active;
}
