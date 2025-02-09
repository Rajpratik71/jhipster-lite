package tech.jhipster.lite.module.infrastructure.secondary;

import org.springframework.stereotype.Repository;
import tech.jhipster.lite.error.domain.Assert;
import tech.jhipster.lite.module.domain.JHipsterModuleApplied;
import tech.jhipster.lite.module.domain.JHipsterModuleChanges;
import tech.jhipster.lite.module.domain.JHipsterModulesRepository;
import tech.jhipster.lite.module.domain.postaction.JHipsterModuleExecutionContext;
import tech.jhipster.lite.npm.domain.NpmVersions;
import tech.jhipster.lite.project.infrastructure.primary.JavaProjects;
import tech.jhipster.lite.projectfile.domain.ProjectFilesReader;

@Repository
class FileSystemJHipsterModulesRepository implements JHipsterModulesRepository {

  private final FileSystemJHipsterModuleFiles files;
  private final FileSystemJavaBuildCommandsHandler javaBuild;
  private final FileSystemSpringPropertiesCommandsHandler springProperties;
  private final FileSystemPackageJsonHandler packageJson;
  private final FileSystemReplacer replacer;
  private final JavaProjects projects;

  public FileSystemJHipsterModulesRepository(ProjectFilesReader filesReader, NpmVersions npmVersions, JavaProjects projects) {
    files = new FileSystemJHipsterModuleFiles(filesReader);
    javaBuild = new FileSystemJavaBuildCommandsHandler();
    springProperties = new FileSystemSpringPropertiesCommandsHandler();
    packageJson = new FileSystemPackageJsonHandler(npmVersions);
    replacer = new FileSystemReplacer();
    this.projects = projects;
  }

  @Override
  public void apply(JHipsterModuleChanges changes) {
    Assert.notNull("changes", changes);

    changes.preActions().run();

    files.create(changes.projectFolder(), changes.filesToAdd());
    files.delete(changes.projectFolder(), changes.filesToDelete());
    javaBuild.handle(changes.indentation(), changes.projectFolder(), changes.javaBuildCommands());
    springProperties.handle(changes.projectFolder(), changes.springProperties());
    packageJson.handle(changes.indentation(), changes.projectFolder(), changes.packageJson());
    replacer.handle(changes.projectFolder(), changes.mandatoryReplacements());
    replacer.handle(changes.projectFolder(), changes.optionalReplacements());

    changes.postActions().run(new JHipsterModuleExecutionContext(changes.projectFolder()));
  }

  @Override
  public void applied(JHipsterModuleApplied moduleApplied) {
    projects.moduleApplied(moduleApplied);
  }
}
