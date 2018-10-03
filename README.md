# Deploy your application to the Wildfly server

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/lorislab/wildfly-maven-plugin.png?branch=master)](https://travis-ci.org/lorislab/wildfly-maven-plugin)
[![Download](https://api.bintray.com/packages/lorislab/maven/wildfly-maven-plugin/images/download.svg) ](http://dl.bintray.com/lorislab/maven/org/lorislab/wildfly-maven-plugin/wildfly-maven-plugin/)
[![Join the chat at https://gitter.im/lorislab/wildfly-maven-plugin](https://badges.gitter.im/lorislab/wildfly-maven-plugin.svg)](https://gitter.im/lorislab/wildfly-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

# Goals
* deploy
* docker-deploy
* docker-undeploy

## docker-deploy

Copy the artifact to the wildfly docker container.

## docker-undeploy

Delete the artifact from the wildfly docker container.

## deploy

Copy the artifact to the local wildfly server.

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