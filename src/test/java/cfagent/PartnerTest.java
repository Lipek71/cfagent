package cfagent;

import cfagent.partner.Partner;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PartnerTest {

    Partner partner = new Partner(1L, "John Doe", false, true, null, null, null );

    @Test
    void getterTest() {
        assertEquals("John Doe", partner.getName());
        assertEquals(1L, partner.getId());
        assertTrue(partner.isActive());
    }

    @Test
    void setterTest() {
        partner.setCompany(true);
        partner.setActive(false);
        assertTrue(partner.isCompany());
        assertFalse(partner.isActive());
    }

    @Test
    void constructorTest(){
        Partner testPartner = new Partner("Jane Doe", false);
        assertEquals("Jane Doe", testPartner.getName());
    }
}
