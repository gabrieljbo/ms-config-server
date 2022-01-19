package dev.gabrieljbo.msconfigserver;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Config Server tests")
class ConfigServerApplicationTests {

    @Test
    @DisplayName("Context loads test")
    void contextLoadsTest() {
        assertTrue(true);
    }

}
