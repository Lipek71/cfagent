package cfagent.insurance;

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
@RequestMapping("api/cfagent/insurance")
@Tag(name = "Operations on insurances")
public class InsuranceController {

    final private InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService) {
        this.insuranceService = insuranceService;
    }

    @GetMapping
    @Operation(summary = "List conditioned insurances.")
    public List<InsuranceDTO> listAgents(@RequestParam Optional<String> company, @RequestParam Optional<String> type, @RequestParam Optional<String> insurance) {
        return insuranceService.listInsurances(company, type, insurance);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an insurence.")
    @ApiResponse(responseCode = "201", description = "Insurence has been created.")
    public InsuranceDTO createInsurance(@Valid @RequestBody CreateInsuranceCommand command){
        return insuranceService.createInsurance(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an agent.")
    public InsuranceDTO updateInsurance(@PathVariable("id") long id, @Valid @RequestBody UpdateInsuranceCommand command) {
        return insuranceService.updateInsurance(id, command);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("insurance/not-found"))
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
