package cfagent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "company")
    private String company;

    @Column(name = "type")
    private String type;

    @Column(name = "insurance")
    private String insurance;

    @Column(name = "active")
    private boolean active;

    public Insurance(String company, String type, String insurance, boolean active) {
        this.company = company;
        this.type = type;
        this.insurance = insurance;
        this.active = active;
    }
}
