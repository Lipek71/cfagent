package cfagent.address;

import cfagent.partner.Partner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "postcode")
    private String postcode;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "partner_id")
    private Partner partner;

    public Address(String postcode, String city, String street) {
        this.postcode = postcode;
        this.city = city;
        this.street = street;
    }
}
