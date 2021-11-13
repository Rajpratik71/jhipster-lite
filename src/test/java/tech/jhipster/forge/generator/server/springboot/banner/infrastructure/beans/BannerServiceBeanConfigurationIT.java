package tech.jhipster.forge.generator.server.springboot.banner.infrastructure.beans;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import tech.jhipster.forge.IntegrationTest;
import tech.jhipster.forge.generator.server.springboot.banner.domain.BannerDomainService;

@IntegrationTest
class BannerServiceBeanConfigurationIT {

  @Autowired
  ApplicationContext applicationContext;

  @Test
  void shouldGetBean() {
    assertThat(applicationContext.getBean("bannerService")).isNotNull().isInstanceOf(BannerDomainService.class);
  }
}