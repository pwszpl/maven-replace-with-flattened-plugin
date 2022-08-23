# Replace POM with flattened Maven plugin

Maven plugin for replacing pom.xml with flattened represantation if both exists in target directory.

# Usage
To use in build:

        <build>
            ...
                <plugin>
                    <groupId>io.github.pwszpl.maven</groupId>
                    <artifactId>replace-with-flattened-plugin</artifactId>
                    <version>1.0</version>
                    <executions>
                        <execution>
                            <id>replace-with-flattened</id>
                            <goals>
                                <goal>repl2flatten</goal>
                            </goals>
                            <phase>initialize</phase>
                        </execution>
                    </executions>
                </plugin>
            ...
        </build>