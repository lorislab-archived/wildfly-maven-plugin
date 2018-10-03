# Wildfly plugin to deploy the application 

[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Build Status](https://travis-ci.org/lorislab/p6.png?branch=master)](https://travis-ci.org/lorislab/wildfly-maven-plugin)
[![Download](https://api.bintray.com/packages/lorislab/maven/wildfly-maven-plugin/images/download.svg) ](http://dl.bintray.com/lorislab/maven/org/lorislab/wildfly-maven-plugin/wildfly-maven-plugin/)
[![Join the chat at https://gitter.im/lorislab/wildfly-maven-plugin](https://badges.gitter.im/lorislab/wildfly-maven-plugin.svg)](https://gitter.im/lorislab/wildfly-maven-plugin?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Docker example

```xml
<plugin>
    <groupId>org.lorislab.maven</groupId>
    <artifactId>wildfly-maven-plugin</artifactId>
    <version>1.0.0</version>
    <executions>
        <execution>
            <phase>install</phase>
            <goals>
                <goal>deploy</goal>
            </goals>
            <configuration>
                <docker>true</docker>
                <dockerContainer>test</dockerContainer>
                <widlflyDir>/opt/wildfly</widlflyDir>
            </configuration>
        </execution>
    </executions>
</plugin>
```

## Default example

```xml
<plugin>
    <groupId>org.lorislab.maven</groupId>
    <artifactId>jboss-server-plugin</artifactId>
    <version>1.0.0</version>
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