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

import java.io.File;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * The docker deployment task.
 *
 * @author Andrej Petras
 */
@Mojo(name = "docker-deploy",
        defaultPhase = LifecyclePhase.DEPLOY,
        requiresProject = true,
        threadSafe = true)
public class DockerDeploymentMojo extends AbstractDockerMojo {

    /**
     * The deploy the file to the server.
     *
     * @throws MojoExecutionException if the method fails
     * @throws MojoFailureException if the method fails
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        File file;  
        String tmp;
        if (exploded) {
            if (deployDir == null || !deployDir.exists()) {
                throw new MojoFailureException("The build directory does not exists! Path: " + deployFile.getAbsolutePath());
            }            
            file = deployDir;
            tmp = "/. ";
        } else {            
            if (deployFile == null || !deployFile.exists()) {
                throw new MojoFailureException("The build final name does not exists! Path: " + deployFile.getAbsolutePath());
            }
            file = deployFile;
            tmp = " ";
        }
            
        try {            
            String target = container + ":" + getDockerPath();
            String cmd = "docker cp " + file.getAbsolutePath() + tmp + target;
            getLog().info(cmd);
            Process p = Runtime.getRuntime().exec(cmd);
            p.waitFor();            
            getLog().info("Docker deployed finished!");
        } catch (Exception ex) {
            throw new MojoExecutionException("Error to copy to the docker " + file.getAbsolutePath(), ex);
        }
    }
     
}
