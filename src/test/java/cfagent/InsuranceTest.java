package cfagent;

import cfagent.insurance.Insurance;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsuranceTest {

    Insurance insurance = new Insurance(1L, "Allianz Biztosító Zrt.", "vagyonbiztosítás", "Autóm CASCO", true, null);

    @Test
    void getterTest() {
        assertEquals("Allianz Biztosító Zrt.", insurance.getCompany());
        assertEquals(1L, insurance.getId());
        assertTrue(insurance.isActive());
    }

    @Test
    void setterTest() {
        insurance.setType("nyugdíjbiztosítás");
        insurance.setInsurance("Gondoskodás nyugdíjbiztosítás");
        insurance.setActive(false);
        assertEquals("nyugdíjbiztosítás", insurance.getType());
        assertFalse(insurance.isActive());
    }

    @Test
    void constructorTest(){
        Insurance testInsurance = new Insurance( "Allianz Biztosító Zrt.", "vagyonbiztosítás", "Autóm CASCO", true);
        assertEquals("Autóm CASCO", testInsurance.getInsurance());
    }
}
