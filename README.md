# Wildfly plugin to deploy the application 

## Docker example

```java
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

```java
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