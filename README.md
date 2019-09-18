# Deploy your application to the Wildfly server

[![License](https://img.shields.io/github/license/quarkusio/quarkus?style=for-the-badge&logo=apache)](https://www.apache.org/licenses/LICENSE-2.0)
[![CircleCI](https://img.shields.io/circleci/build/github/lorislab-archived/wildfly-maven-plugin?logo=circleci&style=for-the-badge)](https://circleci.com/gh/lorislab-archived/wildfly-maven-plugin)
[![Maven Central](https://img.shields.io/maven-central/v/org.lorislab.maven/wildfly-maven-plugin?logo=java&style=for-the-badge)](https://maven-badges.herokuapp.com/maven-central/org.lorislab.maven/wildfly-maven-plugin)
[![GitHub tag (latest SemVer)](https://img.shields.io/github/v/tag/lorislab-archived/wildfly-maven-plugin?logo=github&style=for-the-badge)](https://github.com/lorislab-archived/wildfly-maven-plugin/releases/latest)

# Goals
* deploy - copy the artifact to the local wildfly server.
* docker-deploy - copy the artifact to the wildfly docker container.
* docker-undeploy - delete the artifact from the wildfly docker container.

## Parameters

### absolutePath
* description: The deployments absolute path directory.
* property: org.lorislab.maven.wildfly.server.path
* default: <not_defined>
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### pattern
* description: The file pattern for delete artifact from server deployments directory.
* property: org.lorislab.maven.wildfly.pattern
* default: ${project.artifactId}-*.${project.packaging}
* goals: 
  * docker-undeploy

### targetDirName
* description: The directory name in the deploy directory.
* property: 
* default: ${project.build.finalName}.${project.packaging}
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### deployDir
* description: The directory to deploy for exploded
* property: 
* default: ${project.build.directory}/${project.build.finalName}
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### deployFile
* description: The path of the file to deploy.
* property: 
* default: ${project.build.directory}/${project.build.finalName}.${project.packaging}
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### deployments
* description: The name of the deployments directory.
* property: org.lorislab.maven.wildfly.server.deployments
* default: deployments
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### profile
* description: The profile.
* property: org.lorislab.maven.wildfly.server.profile
* default: standalone
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### widlflyDir
* description: The local server directory.
* property: org.lorislab.maven.wildfly.server.dir
* default: standalone
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

### container
* description: The profile.
* property: org.lorislab.maven.wildfly.container
* default: ${project.artifactId}
* goals: 
  * docker-deploy
  * docker-undeploy

### exploded
* description: The exploded flag. If true copy director and not artifact to the server
* property: org.lorislab.maven.wildfly.exploded
* default: false
* goals: 
  * docker-deploy
  * docker-undeploy
  * deploy

# Examples

## Docker
```xml
<plugin>
    <groupId>org.lorislab.maven</groupId>
    <artifactId>wildfly-maven-plugin</artifactId>
    <version>1.1.0</version>
    <executions>
        <execution>
            <phase>install</phase>
            <goals>
                <goal>docker-deploy</goal>
            </goals>
            <configuration>
                <container>${project.artifactId}</container>
                <widlflyDir>/opt/jboss/wildfly</widlflyDir>
            </configuration>
        </execution>
    </executions>
</plugin>
```
```xml
<plugin>
    <groupId>org.lorislab.maven</groupId>
    <artifactId>wildfly-maven-plugin</artifactId>
    <version>1.1.0</version>
    <executions>
        <execution>
            <phase>clean</phase>
            <goals>
                <goal>docker-undeploy</goal>
            </goals>
            <configuration>
                <absolutePath>/deployments</absolutePath>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## Localhost

```xml
<plugin>
    <groupId>org.lorislab.maven</groupId>
    <artifactId>jboss-server-plugin</artifactId>
    <version>1.1.0</version>
    <executions>
        <execution>
            <phase>install</phase>
            <goals>
                <goal>deploy</goal>
            </goals>
            <configuration>
                <jbossDir>${JBOSS_HOME}</jbossDir>
            </configuration>
        </execution>
    </executions>
</plugin>

```