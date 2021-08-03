package cfagent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from agents")
public class AgentControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test()
    void listAgentTest() {

        AgentDTO agentDTO = template.postForObject("/api/cfagent/agent", new CreateAgentCommand("John Doe", "12345123451") , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

        template.postForObject("/api/cfagent/agent", new CreateAgentCommand("Jane Doe", "12345123452") , AgentDTO.class);

        List<AgentDTO> agentDTOList = template.exchange("/api/cfagent/agent",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AgentDTO>>() {})
                .getBody();

        assertThat(agentDTOList)
                .extracting(AgentDTO::getName)
                .contains("John Doe", "Jane Doe");
    }

    @Test()
    void updateAgentTest() {

        AgentDTO agentDTO = template.postForObject("/api/cfagent/agent", new CreateAgentCommand("John Doe", "12345123451") , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

        template.put("/api/cfagent/agent/" + agentDTO.getId(), new UpdateAgentCommand("Jane Doe", "12345123452", false) , AgentDTO.class);

        List<AgentDTO> agentDTOList = template.exchange("/api/cfagent/agent",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AgentDTO>>() {})
                .getBody();

        assertThat(agentDTOList)
                .extracting(AgentDTO::getName)
                .containsExactly("Jane Doe");

    }
}
