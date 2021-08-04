package cfagent.agent;

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
@RequestMapping("api/cfagent/agent")
@Tag(name = "Operations on agents.")
public class AgentController {

    final private AgentService agentService;

    public AgentController(AgentService agentService) {
        this.agentService = agentService;
    }

    @GetMapping
    @Operation(summary = "List conditioned agents.")
    public List<AgentDTO> listAgents(@RequestParam Optional<String> name) {
        return agentService.listAgents(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create an agent.")
    @ApiResponse(responseCode = "201", description = "Agent has been created.")
    public AgentDTO createAgent(@Valid @RequestBody CreateAgentCommand command){
        return agentService.createAgent(command);
    }

    @PostMapping("/{id}")
    @Operation(summary = "Add a partner by id.")
    public AgentDTO addPartnerToAgent(@PathVariable("id") long id, AddPartnerCommand command){
        return agentService.addPartnerToAgent(id, command);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an agent.")
    public AgentDTO updateAgent(@PathVariable("id") long id, @Valid @RequestBody UpdateAgentCommand command) {
        return agentService.updateAgent(id, command);
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
