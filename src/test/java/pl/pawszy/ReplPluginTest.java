package pl.pawszy;

import org.codehaus.plexus.util.FileUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ReplPluginTest {
    private ReplPlugin repl;

    @Before
    public void init(){
        repl = new ReplPlugin();
    }

    @Test
    public void flattenFiles() throws IOException {
        String buildDir = System.getProperty("workDir");
        repl.setSrcDir(buildDir+"/target/test-classes");
        repl.execute();

        File file1 = new File(buildDir+"/src/test/resources/.flattened-pom.xml");
        File file2 = new File(buildDir+"/target/test-classes/pom.xml");
        Assert.assertTrue(FileUtils.contentEquals(file1, file2));

        File file3 = new File(buildDir+"/src/test/resources/hierarchical/.flattened-pom.xml");
        File file4 = new File(buildDir+"/target/test-classes/hierarchical/pom.xml");
        Assert.assertTrue(FileUtils.contentEquals(file3, file4));
    }

}