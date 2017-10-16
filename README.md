# javafx-app-packager-plugin

Goal is to write production ready plugin, business logic is not important, 
though I will be using this plugin in private project.

Just testing mojo api possibilities.
Project structure generated with maven archetype maven-archetype-mojo

Usage:

First, mvn install plugin on your machine, so it is avaiable in your maven repository.

Then add a profile to project where you will use this plugin, like this:

    <profiles>
    (...)
        <profile>
            <id>zip-release</id>
            <activation>
                <activeByDefault>false</activeByDefault>
            </activation>
            <build>
                <plugins>
                    <plugin>
                        <groupId>kjkow</groupId>
                        <artifactId>javafx-app-packager-plugin</artifactId>
                        <version>1.0-SNAPSHOT</version>
                        <executions>
                            <execution>
                                <id>create-release</id>
                                <phase>install</phase>
                                <goals>
                                    <goal>create-release</goal>
                                </goals>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
    
    
    Then running maven with profile like
    mvn install -Pzip-release
