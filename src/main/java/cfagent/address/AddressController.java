package cfagent.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/cfagent/address")
@Tag(name = "Operations on addresses")
public class AddressController {

    final private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @Operation(summary = "List conditioned addresses")
    public List<AddressDTO> listAddresses(@RequestParam Optional<String> postcode, @RequestParam Optional<String> city, @RequestParam Optional<String> street) {
        return addressService.listAdresses(postcode, city, street);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an address")
    @ApiResponse(responseCode = "201", description = "Agent has been created.")
    public AddressDTO createAgent(@Valid @RequestBody CreateAddressCommand command){
        return addressService.createAddress(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an address")
    public AddressDTO updateAgent(@PathVariable("id") long id, @Valid @RequestBody UpdateAddressCommand command) {
        return addressService.updateAddress(id, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("address/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(iae.getMessage())
                        .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
