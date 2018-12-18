# Deploy your application to the Wildfly server

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.com/lorislab/wildfly-maven-plugin.svg?branch=develop)](https://travis-ci.com/lorislab/wildfly-maven-plugin)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.lorislab.maven/wildfly-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.lorislab.maven/wildfly-maven-plugin)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/01d4440634194ca7845b3c86b2b45475)](https://www.codacy.com/app/lorislab/wildfly-maven-plugin?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=lorislab/wildfly-maven-plugin&amp;utm_campaign=Badge_Grade)
[![Join the chat at https://gitter.im/lorislab/wildfly-maven-plugin](https://badges.gitter.im/lorislab/wildfly-maven-plugin.svg)](https://gitter.im/lorislab/wildfly-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

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