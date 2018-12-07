require 'buildr/git_auto_version'
require 'buildr/gpg'

desc 'dagger-gwt-lite: SuperSourced elements to optimize Dagger for GWT/J2CL'
define 'dagger-gwt-lite' do
  project.group = 'org.realityforge.dagger'
  compile.options.source = '1.8'
  compile.options.target = '1.8'
  compile.options.lint = 'all'

  pom.include_transitive_dependencies << artifact(:dagger_core)
  pom.include_transitive_dependencies << artifact(:dagger_core_sources)
  pom.include_transitive_dependencies << artifact(:javax_inject)
  pom.include_transitive_dependencies << artifact(:javax_inject_sources)

  project.version = ENV['PRODUCT_VERSION'] if ENV['PRODUCT_VERSION']

  pom.add_apache_v2_license
  pom.add_github_project('realityforge/dagger-gwt-lite')
  pom.add_developer('realityforge', 'Peter Donald')

  compile.with :dagger_core, :dagger_core_sources, :javax_inject, :javax_inject_sources

  package(:jar).include(project._('src/main/java/dagger')).include(project._('src/main/java/javax'))
  package(:sources)
  package(:javadoc)

  ipr.add_component_from_artifact(:idea_codestyle)
end
