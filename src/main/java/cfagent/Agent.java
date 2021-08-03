package cfagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agent")
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "mnb_number")
    private String mnbNumber;

    @Column(name = "active")
    private boolean active;

    public Agent(String name, String mnbNumber) {
        this.name = name;
        this.mnbNumber = mnbNumber;
    }
}
