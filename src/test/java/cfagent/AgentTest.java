package cfagent;

import cfagent.agent.Agent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AgentTest {

    Agent agent = new Agent(1L, "John Doe", "12345678901", true, null);

    @Test
    void getterTest() {
        assertEquals("John Doe", agent.getName());
        assertEquals(1L, agent.getId());
        assertTrue(agent.isActive());
    }

    @Test
    void setterTest() {
        agent.setMnbNumber("99999999999");
        agent.setActive(false);
        assertEquals("99999999999", agent.getMnbNumber());
        assertFalse(agent.isActive());
    }

    @Test
    void constructorTest(){
        Agent testAgent = new Agent("Jane Doe", "11111111111");
        assertEquals("Jane Doe", testAgent.getName());
    }
}