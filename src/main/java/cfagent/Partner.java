package cfagent;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private boolean company;

    @Column(name = "address_id")
    private Long addresId;

    @Column(name = "agent_id")
    private long agentId;

    @Column(name = "insurence_id")
    private Long insurenceId;

    @Column(name = "active")
    private boolean active;

    public Partner(String name, boolean company) {
        this.name = name;
        this.company = company;
    }
}
