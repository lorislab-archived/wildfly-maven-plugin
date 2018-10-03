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
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * The deployment task.
 *
 * @author Andrej Petras
 */
@Mojo(name = "deploy",
        defaultPhase = LifecyclePhase.DEPLOY,
        requiresProject = true,
        threadSafe = true)
public class DeploymentMojo extends AbstractServerMavenPlugin {

    /**
     * The deploy the file to the server.
     *
     * @throws MojoExecutionException if the method fails
     * @throws MojoFailureException if the method fails
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            File targetDir = getLocalTargetDir();
            if (exploded) {
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
            } else {
                getLog().info("Deploy the file: " + deployFile.getAbsolutePath() + " to the server: " + targetDir.getAbsolutePath());
                FileUtils.copyFileToDirectory(deployFile, targetDir);
                getLog().info("Deployed finished!");
            }
        } catch (IOException ex) {
            throw new MojoExecutionException("Error to copy " + deployFile.getAbsolutePath(), ex);
        }
    }
}
