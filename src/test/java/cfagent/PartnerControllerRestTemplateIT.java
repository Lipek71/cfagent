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
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from flyway_schema_history")
public class PartnerControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test()
    void listPartnerTest() {

        PartnerDTO partnerDTO = template.postForObject("/api/cfagent/partner", new CreatePartnerCommand("John Doe", false) , PartnerDTO.class);

        assertEquals("John Doe", partnerDTO.getName());

        template.postForObject("/api/cfagent/partner", new CreatePartnerCommand("Jane Doe", false) , PartnerDTO.class);

        List<PartnerDTO> partnerDTOList = template.exchange("/api/cfagent/partner",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PartnerDTO>>() {})
                .getBody();

        assertThat(partnerDTOList)
                .extracting(PartnerDTO::getName)
                .contains("John Doe", "Jane Doe");
    }

    @Test()
    void deletePartnerTest() {

        PartnerDTO partnerDTO = template.postForObject("/api/cfagent/partner", new CreatePartnerCommand("John Doe", false) , PartnerDTO.class);

        assertEquals("John Doe", partnerDTO.getName());

        template.postForObject("/api/cfagent/partner", new CreatePartnerCommand("Jane Doe", false) , PartnerDTO.class);

        List<PartnerDTO> partnerDTOList = template.exchange("/api/cfagent/partner",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PartnerDTO>>() {})
                .getBody();

        template.delete("/api/cfagent/partner/1");

        assertThat(partnerDTOList)
                .extracting(PartnerDTO::getName)
                .contains("John Doe", "Jane Doe");
    }

    @Test()
    void updatePartnerTest() {

        PartnerDTO partnerDTO = template.postForObject("/api/cfagent/partner", new CreatePartnerCommand("John Doe", false) , PartnerDTO.class);

        assertEquals("John Doe", partnerDTO.getName());

        template.put("/api/cfagent/partner/1", new UpdatePartnerCommand("Jane Doe", false, false) , PartnerDTO.class);

        assertEquals("John Doe", partnerDTO.getName());

    }
}
