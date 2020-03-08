import com.es2.bridge.APIMoodle;
import com.es2.bridge.APIRequest;
import com.es2.bridge.APIRequestContentAggregator;
import com.es2.bridge.ServiceNotFoundException;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class BridgePatternTest {
    private APIRequest req;

    @DisplayName("Test if the exception is thrown for not existing services get") @Test
    public void testServiceNotFoundExceptionThrown() {
        req = new APIRequest();
        assertThrows(ServiceNotFoundException.class, () -> {
            req.getContent("abc", "12");
        });
    }

    @DisplayName("Test if the request returns null for not existing content") @Test
    public void testRequestReturnsNull() throws ServiceNotFoundException {
        req = new APIRequest();
        String idService = req.addService(new APIMoodle());
        assertNull(req.getContent(idService, "12"));
    }

    @DisplayName("Test if the request returns the content previously added") @Test
    public void testContentPreviouslyAdded() throws ServiceNotFoundException {
        req = new APIRequest();
        String idService = req.addService(new APIMoodle());
        String idContent = req.setContent(idService, "12");
        assertEquals(req.getContent(idService, idContent), "12");
    }

    @DisplayName("Test if the aggregated content of one service is returned correctly") @Test
    public void testAggregatedContent() throws ServiceNotFoundException {
        req = new APIRequestContentAggregator();
        String idService = req.addService(new APIMoodle());
        req.setContent(idService, "Eu vou");
        req.setContent(idService, " a Viseu");
        req.setContent(idService, " estudar");
        assertEquals(req.getContent(idService, "0"), "Eu vou a Viseu estudar");
    }

    @DisplayName("Test if the exception is thrown for not existing services, set") @Test
    public void testSetContent() throws ServiceNotFoundException {
        req = new APIRequestContentAggregator();
        String idService = req.addService(new APIMoodle());
        assertThrows(ServiceNotFoundException.class, () -> {
            req.setContent(idService + 1, "Eu vou");
        });
    }
}
