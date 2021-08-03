package cfagent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from agent")
public class AgentControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test()
    void listTrainingClassTest() {

        AgentDTO agentDTO = template.postForObject("/api/cfagent", new CreateAgentCommand("John Doe", "12345123451") , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

        template.postForObject("/api/cfagent", new CreateAgentCommand("Jane Doe", "12345123452") , AgentDTO.class);

        List<AgentDTO> agentDTOList = template.exchange("/api/cfagent",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<AgentDTO>>() {})
                .getBody();

        assertThat(agentDTOList)
                .extracting(AgentDTO::getName)
                .contains("John Doe", "Jane Doe");
    }

    @Test()
    void deleteTrainingClassTest() {

        AgentDTO agentDTO = template.postForObject("/api/cfagent", new CreateAgentCommand("John Doe", "12345123451") , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

        template.postForObject("/api/cfagent", new CreateAgentCommand("Jane Doe", "12345123452") , AgentDTO.class);

        List<AgentDTO> agentDTOList = template.exchange("/api/cfagent",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AgentDTO>>() {})
                .getBody();

        template.delete("/api/cfagent/2");

        assertThat(agentDTOList)
                .extracting(AgentDTO::getName)
                .contains("John Doe", "Jane Doe");
    }

    @Test()
    void updateTrainingClassTest() {

        AgentDTO agentDTO = template.postForObject("/api/cfagent", new CreateAgentCommand("John Doe", "12345123451") , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

        template.put("/api/cfagent", new UpdateAgentCommand("Jane Doe", "12345123452", false) , AgentDTO.class);

        assertEquals("John Doe", agentDTO.getName());

    }
}
