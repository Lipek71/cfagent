package cfagent;

import cfagent.address.AddressDTO;
import cfagent.address.CreateAddressCommand;
import cfagent.address.UpdateAddressCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from addresses")
public class AddressControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test()
    void listAddressTest() {

        AddressDTO addressDTO = template.postForObject("/api/cfagent/address", new CreateAddressCommand("2330", "Dunaharaszti", "Szőlőhegy u. 55.") , AddressDTO.class);

        assertEquals("2330", addressDTO.getPostcode());

        template.postForObject("/api/cfagent/address", new CreateAddressCommand("2053", "Herceghalom", "Széchenyi u. 27.") , AddressDTO.class);

        List<AddressDTO> addressDTOList = template.exchange("/api/cfagent/address",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AddressDTO>>() {})
                .getBody();

        assertThat(addressDTOList)
                .extracting(AddressDTO::getPostcode)
                .containsExactly("2330", "2053");
    }

    @Test()
    void updateAddressTest() {

        AddressDTO addressDTO = template.postForObject("/api/cfagent/address", new CreateAddressCommand("2330", "Dunaharaszti", "Szőlőhegy u. 55.") , AddressDTO.class);

        assertEquals("2330", addressDTO.getPostcode());

        template.put("/api/cfagent/address/" + addressDTO.getId(), new UpdateAddressCommand("2051", "Biatorbágy", "Nagy u. 54.") , AddressDTO.class);

        List<AddressDTO> addressDTOList = template.exchange("/api/cfagent/address",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AddressDTO>>() {})
                .getBody();

        assertThat(addressDTOList)
                .extracting(AddressDTO::getCity)
                .containsExactly("Biatorbágy");

    }

    @Test
    void exeptionAddressTest(){
        Problem problem = template.postForObject("/api/cfagent/address", new CreateAddressCommand("", "Dunaharaszti", "Szőlőhegy u. 55.") , Problem.class);

        assertEquals(Status.BAD_REQUEST, problem.getStatus());
    }

    @Test
    void exeptionUpdateAddressTest(){
        AddressDTO addressDTO = template.postForObject("/api/cfagent/address", new CreateAddressCommand("2330", "Dunaharaszti", "Szőlőhegy u. 55.") , AddressDTO.class);

        Problem problem = template.exchange("/api/cfagent/address/2" , HttpMethod.PUT, new HttpEntity<>(new UpdateAddressCommand("2051", "Biatorbágy", "Nagy u. 54.")), new ParameterizedTypeReference<Problem>(){}).getBody();

        assertEquals(Status.INTERNAL_SERVER_ERROR, problem.getStatus());
    }

}