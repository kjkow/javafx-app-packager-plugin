package kjkow;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
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

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Compressing dir /lib and jar file from target directory
 * @goal create-release
 */
@Mojo(name = "fxPackager", defaultPhase = LifecyclePhase.DEPLOY)  //todo: if I remove javadoc I get error, which should I keep?
public class FxApplicationPackager extends AbstractMojo {

/*    @Parameter(defaultValue = "${project.version}", readonly = true)
    private String projectVerison;*/ //todo: why those annotations ain't working?

    private List<String> libFiles;

    public void execute() throws MojoExecutionException {
        libFiles = new ArrayList<>();

        MavenProject project = (MavenProject) getPluginContext().get("project");

        String targetDirectory = project.getBuild().getDirectory();
        generateFileList(new File(targetDirectory + "\\lib"));
        generateFileList(new File(targetDirectory + "\\genark_v" + project.getVersion() + ".jar"));

        //todo: zip those files!
    }

    private void generateFileList(File node){
        if(node.isFile()){
            getLog().info("generating zip entry: " + node.getAbsolutePath()); //only for testing, remove after
            libFiles.add(node.getAbsolutePath());
        }else if(node.isDirectory()){
            String[] subNode = node.list();
            for(String fileName: subNode){
                generateFileList(new File(node, fileName));
            }
        }

    }
}
