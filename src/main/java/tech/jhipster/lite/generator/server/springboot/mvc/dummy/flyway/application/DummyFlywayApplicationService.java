package tech.jhipster.lite.generator.server.springboot.mvc.dummy.flyway.application;

import java.time.Instant;
import org.springframework.stereotype.Service;
import tech.jhipster.lite.generator.server.springboot.mvc.dummy.flyway.domain.DummyFlywayModuleFactory;
import tech.jhipster.lite.module.domain.JHipsterModule;
import tech.jhipster.lite.module.domain.properties.JHipsterModuleProperties;

@Service
public class DummyFlywayApplicationService {

  private final DummyFlywayModuleFactory factory;

  public DummyFlywayApplicationService() {
    factory = new DummyFlywayModuleFactory();
  }

  public JHipsterModule buildModule(JHipsterModuleProperties properties) {
    return factory.buildModule(properties, Instant.now());
  }
}
