package cfagent.agent;

import cfagent.partner.Partner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "agents")
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

    @OneToMany(mappedBy = "agent", cascade = CascadeType.ALL)
    private List<Partner> partners;

    public Agent(String name, String mnbNumber) {
        this.name = name;
        this.mnbNumber = mnbNumber;
    }

    public void addPartner(Partner partner){
        if (partners == null) {
            partners = new ArrayList<>();
        }
        partners.add(partner);
        partner.setAgent(this);
    }
}
