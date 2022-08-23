package pl.pawszy;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugin.logging.SystemStreamLog;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.monitor.logging.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Mojo(name="repl2flatten", defaultPhase = LifecyclePhase.GENERATE_SOURCES)
public class ReplPlugin extends AbstractMojo {
    private SystemStreamLog log = new SystemStreamLog();
    @Parameter(defaultValue=".")
    private String srcDir;

    @Override
    public void execute(){
        try {
            List<Path> poms = Files.walk(Paths.get(srcDir)).filter(e -> e.toFile().getName().equals("pom.xml")).collect(Collectors.toList());
            poms.forEach(e -> { replaceWithFlatten(e);} );
        } catch (IOException e) {
            log.error(e);
        }
    }

    private void replaceWithFlatten(Path e) {
        String srcDir = e.toFile().getParentFile().getAbsolutePath();
        File flattenedPom = new File(srcDir+File.separator+".flattened-pom.xml");
        if(flattenedPom.exists()){
                e.toFile().delete();
                flattenedPom.renameTo(e.toFile());
        } else {
            log.info("Couldn't find flattened for pom: "+e);
        }
    }

    public void setSrcDir(String srcDir) {
        this.srcDir = srcDir;
    }

}
