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
import org.apache.maven.plugins.annotations.Parameter;

/**
 * The abstract docker deployment task.
 *
 * @author Andrej Petras
 */
public abstract class AbstractDockerMojo extends AbstractServerMavenPlugin {

    /**
     * The docker container name.
     */
    @Parameter(defaultValue = "${project.artifactId}", property = "org.lorislab.maven.wildfly.container", required = true)
    protected String container;

    /**
     * Gets the target directory.
     *
     * @return the target directory.
     *
     * @throws MojoExecutionException if the method fails.
     * @throws MojoFailureException if the method fails.
     */
    protected String getDockerPath() throws MojoExecutionException, MojoFailureException {
        String result = null;
        
        // check the absolute path
        if (absolutePath != null && !absolutePath.isEmpty()) {
            result = absolutePath;
        } else {
            // build wildfly path
            if (widlflyDir == null || widlflyDir.isEmpty()) {
                throw new MojoFailureException("The Wildfly directory is not defined! property: org.lorislab.maven.wildfly.server.dir");
            }
            result = widlflyDir + "/" + profile + "/" + deployments;
        }
        if (result != null && !result.endsWith("/")) {
            result = result + "/";
        }
        return result;
    }
}
