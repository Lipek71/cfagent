package cfagent;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from insurances")
public class InsuranceControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test()
    void listInsuranceTest() {

        InsuranceDTO insuranceDTO = template.postForObject("/api/cfagent/insurance", new CreateInsuranceCommand("Allianz Biztosító Zrt.", "vagyonbiztosítás", "Autóm CASCO", true) , InsuranceDTO.class);

        assertEquals("Allianz Biztosító Zrt.", insuranceDTO.getCompany());

        template.postForObject("/api/cfagent/insurance", new CreateInsuranceCommand("Generali Biztosító Zrt.", "vagyonbiztosítás", "Házőrző", true) , InsuranceDTO.class);

        List<InsuranceDTO> insuranceDTOList = template.exchange("/api/cfagent/insurance",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<InsuranceDTO>>() {})
                .getBody();

        assertThat(insuranceDTOList)
                .extracting(InsuranceDTO::getCompany)
                .containsExactly("Allianz Biztosító Zrt.", "Generali Biztosító Zrt.");
    }

    @Test()
    void updateInsuranceTest() {

        InsuranceDTO insuranceDTO = template.postForObject("/api/cfagent/insurance", new CreateInsuranceCommand("Allianz Biztosító Zrt.", "vagyonbiztosítás", "Autóm CASCO", true) , InsuranceDTO.class);

        assertEquals("Allianz Biztosító Zrt.", insuranceDTO.getCompany());

        template.put("/api/cfagent/insurance/" + insuranceDTO.getId(), new UpdateInsuranceCommand("Generali Biztosító Zrt.", "vagyonbiztosítás", "Házőrző", true) , InsuranceDTO.class);

        List<InsuranceDTO> insuranceDTOList = template.exchange("/api/cfagent/insurance",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<InsuranceDTO>>() {})
                .getBody();

        assertThat(insuranceDTOList)
                .extracting(InsuranceDTO::getInsurance)
                .containsExactly("Házőrző");

    }

    @Test
    void exeptionAgentTest(){
        Problem problem = template.postForObject("/api/cfagent/insurance", new CreateInsuranceCommand("Allianz Biztosító Zrt.", "", "Autóm CASCO", true) , Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

}
