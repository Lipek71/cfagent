package cfagent;

import cfagent.address.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    Address address = new Address(1L, "2330", "Dunaharaszti", "Szőlőhegy u. 55.", null);

    @Test
    void getterTest() {
        assertEquals("2330", address.getPostcode());
        assertEquals(1L, address.getId());
    }

    @Test
    void setterTest() {
        address.setPostcode("2053");
        address.setCity("Herceghalom");
        assertEquals("2053", address.getPostcode());
        assertEquals("Herceghalom", address.getCity());
    }

    @Test
    void constructorTest(){
        Address testAddress = new Address("2330", "Dunaharaszti", "Szőlőhegy u. 55.");
        assertEquals("Szőlőhegy u. 55.", testAddress.getStreet());
    }
}
