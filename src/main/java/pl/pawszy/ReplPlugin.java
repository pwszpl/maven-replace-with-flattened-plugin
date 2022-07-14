package pl.pawszy;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Mojo(name="repl2flatten", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ReplPlugin extends AbstractMojo {
    @Parameter(defaultValue="./")
    private String srcDir;

    @Override
    public void execute(){
        try {
            Files.walk(Paths.get(srcDir))
                    .filter(e -> { return e.toFile().getName().equals("pom.xml"); })
                    .forEach(e -> { replaceWithFlatten(e);} );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void replaceWithFlatten(Path e) {
        String srcDir = e.toFile().getParentFile().getAbsolutePath();
        File flattenedPom = new File(srcDir+File.separator+".flattened-pom.xml");
        if(flattenedPom.exists()){
                e.toFile().delete();
                flattenedPom.renameTo(e.toFile());
        }
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

}
