import config.PersistenceConfig;
import org.junit.jupiter.api.Test;


public class ConnectionTest {

    @Test
    public void connectionToDatabaseIsVerified() {
        PersistenceConfig.getEntityManagerFactory();
    }
}
