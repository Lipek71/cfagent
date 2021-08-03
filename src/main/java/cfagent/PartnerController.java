package cfagent;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.violations.Violation;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/cfagent/partner")
@Tag(name = "Operations on partner")
public class PartnerController {

    final private PartnerService partnerService;

    public PartnerController(PartnerService partnerService) {
        this.partnerService = partnerService;
    }

    @GetMapping
    @Operation(summary = "List conditioned partners.")
    public List<PartnerDTO> listPartners(@RequestParam Optional<String> name) {
        return partnerService.listPartners(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a partner.")
    @ApiResponse(responseCode = "201", description = "Partner has been created.")
    public PartnerDTO createPartner(@Valid @RequestBody CreatePartnerCommand command) {
        return partnerService.createPartner(command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a partners.")
    public PartnerDTO updateAgent(@PathVariable("id") long id, @Valid @RequestBody UpdatePartnerCommand command) {
        return partnerService.updatePartner(id, command);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a partner.")
    public void deletePartner(@PathVariable("id") long id){
        partnerService.deletePartner(id);
    }


    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFound(IllegalArgumentException iae) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("agent/not-found"))
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
