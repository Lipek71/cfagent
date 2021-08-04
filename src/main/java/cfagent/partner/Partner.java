package cfagent.partner;

import cfagent.address.Address;
import cfagent.agent.Agent;
import cfagent.insurance.Insurance;
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
@Table(name = "partners")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "company")
    private boolean company;

    @Column(name = "active")
    private boolean active;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent agent;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Address> addresses;

    @OneToMany(mappedBy = "partner", cascade = CascadeType.ALL)
    private List<Insurance> insurances;

    public Partner(String name, boolean company) {
        this.name = name;
        this.company = company;
    }

    public void addAddress(Address address){
        if (addresses == null) {
            addresses = new ArrayList<>();
        }
        addresses.add(address);
        address.setPartner(this);
    }

    public void addInsurance(Insurance insurance){
        if (insurances == null) {
            insurances = new ArrayList<>();
        }
        insurances.add(insurance);
        insurance.setPartner(this);
    }
}
