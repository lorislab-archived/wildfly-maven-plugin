/*
 * Copyright 2014 Andrej Petras.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lorislab.maven.wildfly;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The docker deployment task.
 *
 * @author Andrej Petras
 */
@Mojo(name = "docker-undeploy",
        defaultPhase = LifecyclePhase.NONE,
        requiresProject = true,
        threadSafe = true)
public class DockerUndeployMojo extends AbstractDockerMojo {
    
    /**
     * The file pattern for delete artifact from server deployments directory.
     */
    @Parameter(defaultValue = "${project.artifactId}-*.${project.packaging}", property = "org.lorislab.maven.wildfly.pattern" )
    protected String pattern;
    
    /**
     * The deploy the file to the server.
     *
     * @throws MojoExecutionException if the method fails
     * @throws MojoFailureException if the method fails
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            String path = getDockerPath();
            String cmd = "docker exec " + container + " find " + path + " -name " + pattern + " -delete";
            getLog().info(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();            
            getLog().info("Docker clean finished!");
        } catch (Exception ex) {
            throw new MojoExecutionException("Error to clean the docker " + deployFile.getAbsolutePath(), ex);
        }
    }
   
}
