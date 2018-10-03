# Deploy your application to the Wildfly server

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/lorislab/wildfly-maven-plugin.png?branch=master)](https://travis-ci.org/lorislab/wildfly-maven-plugin)
[![Download](https://api.bintray.com/packages/lorislab/maven/wildfly-maven-plugin/images/download.svg) ](http://dl.bintray.com/lorislab/maven/org/lorislab/wildfly-maven-plugin/wildfly-maven-plugin/)
[![Join the chat at https://gitter.im/lorislab/wildfly-maven-plugin](https://badges.gitter.im/lorislab/wildfly-maven-plugin.svg)](https://gitter.im/lorislab/wildfly-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# Goals
* deploy - copy the artifact to the local wildfly server.
* docker-deploy - copy the artifact to the wildfly docker container.
* docker-undeploy - delete the artifact from the wildfly docker container.

## Parameters

| Name        | Property    | Default     | Goal        | Description |
| ----------- | ----------- | ----------- | ----------- | ----------- |
| absolutePath | org.lorislab.maven.wildfly.server.path | | docker-deploy,docker-undeploy,deploy | The wildfly server deployments absolute path directory. |
| deployments | org.lorislab.maven.wildfly.server.deployments | deployments | docker-deploy,docker-undeploy,deploy | The wildfly server deployments directory. |
| profile | org.lorislab.maven.wildfly.server.profile | standalone | docker-deploy,docker-undeploy,deploy |  The Wildfly profile. |
| deployFile | | ${project.build.directory}/${project.build.finalName}.${project.packaging} | docker-deploy,docker-undeploy,deploy | The path of the file to deploy. |
| deployDir | | ${project.build.directory}/${project.build.finalName} | docker-deploy,docker-undeploy,deploy | The directory to deploy for exploded |
| targetDirName | | ${project.build.finalName}.${project.packaging} | docker-deploy,docker-undeploy,deploy | The directory name in the deploy directory. |
| widlflyDir | org.lorislab.maven.wildfly.server.dir | | docker-deploy,docker-undeploy,deploy | The local wildfly server directory |
| container | org.lorislab.maven.wildfly.container | ${project.artifactId} | docker-deploy,docker-undeploy | The docker container name |
| exploded | org.lorislab.maven.wildfly.exploded | false | docker-deploy,docker-undeploy,deploy | The exploded flag. If true copy director and not artifact to the server |

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