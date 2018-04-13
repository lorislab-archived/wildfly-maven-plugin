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
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The deployment task.
 *
 * @author Andrej Petras
 */
@Mojo(name = "deploy",
        defaultPhase = LifecyclePhase.DEPLOY,
        requiresProject = true,
        threadSafe = true)
@Execute(goal = "deploy", phase = LifecyclePhase.INSTALL)
public class DeploymentMojo extends AbstractServerMavenPlugin {

    /**
     * The exploded flag.
     */
    @Parameter(defaultValue = "false")
    private boolean exploded = false;

    /**
     * The docker flag.
     */
    @Parameter(defaultValue = "false")
    private boolean docker = false;

    /**
     * The docker container name.
     */
    @Parameter
    private String dockerContainer = null;

    /**
     * The docker command.
     */    
    @Parameter(defaultValue = "docker cp")
    private String dockerCommand = "docker cp";
    
    /**
     * The deploy the file to the server.
     *
     * @throws MojoExecutionException if the method fails
     * @throws MojoFailureException if the method fails
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

        if (exploded) {
            if (docker) {
                try {                                        
                    String path = getDockerPath();
                    String target = dockerContainer + ":" + path;
                    getLog().info("Docker deploy the directory: " + deployDir.getAbsolutePath() + " to the docker container: " + target);
                    String cmd = dockerCommand + " " + deployDir.getAbsolutePath() + "/. " + target;

                    getLog().info(cmd);
                    Process p = Runtime.getRuntime().exec(cmd);
                    p.waitFor();

                    getLog().info("Docker deployed finished!");
                } catch (Exception ex) {
                    throw new MojoExecutionException("Error to copy to the docker " + deployDir.getAbsolutePath(), ex);
                } 
            } else {
                try {
                    File targetDir = getTargetDir();
                    
                    getLog().info("Deploy the directory: " + targetDirName + " to the server: " + targetDir.getAbsolutePath());

                    File target = new File(targetDir, targetDirName);

                    FileUtils.deleteDirectory(target);

                    // create target directory
                    if (!target.exists()) {
                        target.mkdir();
                    }

                    // copy directory
                    FileUtils.copyDirectory(deployDir, target);

                    // redeploy the application
                    FileUtils.touch(new File(targetDir, targetDirName + ".dodeploy"));
                    getLog().info("Exploded deployed finished!");
                } catch (IOException ex) {
                    throw new MojoExecutionException("Error to copy " + deployFile.getAbsolutePath(), ex);
                }
            }
        } else {
            if (docker) {
                try {
                    String path = getDockerPath();
                    String target = dockerContainer + ":" + path;
                    getLog().info("Docker deploy the file: " + deployFile.getAbsolutePath() + " to the docker container: " + target);
                    String cmd = dockerCommand + " " + deployFile.getAbsolutePath() + " " + target;

                    getLog().info(cmd);
                    Process p = Runtime.getRuntime().exec(cmd);
                    p.waitFor();

                    getLog().info("Docker deployed finished!");
                } catch (Exception ex) {
                    throw new MojoExecutionException("Error to copy to the docker " + deployFile.getAbsolutePath(), ex);
                }                
            } else {
                try {
                    File targetDir = getTargetDir();
                    getLog().info("Deploy the file: " + deployFile.getAbsolutePath() + " to the server: " + targetDir.getAbsolutePath());
                    FileUtils.copyFileToDirectory(deployFile, targetDir);
                    getLog().info("Deployed finished!");
                } catch (IOException ex) {
                    throw new MojoExecutionException("Error to copy " + deployFile.getAbsolutePath(), ex);
                }
            }
        }
    }

    
    /**
     * Gets the target directory.
     * 
     * @return the target directory.
     * 
     * @throws MojoExecutionException if the method fails.
     * @throws MojoFailureException if the method fails.
     */
    protected String getDockerPath() throws MojoExecutionException, MojoFailureException {

        if (deployFile == null || !deployFile.exists()) {
            throw new MojoFailureException("The build final name does not exists! Path: " + deployFile.getAbsolutePath());
        }

        if (widlflyDir == null || widlflyDir.isEmpty()) {
            throw new MojoFailureException("The Wildfly directory is not defined! property: org.lorislab.maven.wildfly.server.dir");
        }

        return widlflyDir + "/" + profile + "/" + deployments;
    }      
}
