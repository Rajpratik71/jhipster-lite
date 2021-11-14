package tech.jhipster.light.generator.buildtool.generic.infrastructure.beans;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.light.IntegrationTest;
import tech.jhipster.light.generator.buildtool.generic.domain.BuildToolDomainService;

@IntegrationTest
class BuildToolBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("buildToolService")).isNotNull().isInstanceOf(BuildToolDomainService.class);
  }
}