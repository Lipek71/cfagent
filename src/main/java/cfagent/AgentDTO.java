package cfagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AgentDTO {

    private Long id;

    private String name;

    private String mnbNumber;

    private boolean active;

}
