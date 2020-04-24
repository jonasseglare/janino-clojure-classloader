package clojure.lang;
import org.codehaus.janino.JavaSourceClassLoader;
import java.io.File;
import java.nio.file.FileSystems;
import java.util.Date;

public class CustomLoader {

    public static File getUncheckedSrcPath() {
        String prop = System.getProperty("janino_src_path");
        if (prop == null) {
            return new File("src_janino");
        } else {
            return new File(prop);
        }
        //return new File("janino");
    }

    public static File getSrcPath() {
        File f = getUncheckedSrcPath();
        if (f.exists()) {
            return f;
        } else {
            throw new RuntimeException("No directory at " + f.getAbsolutePath()
                + ". Consider defining 'janino_src_path' system property or creating this directory");
        }
    }
    
    public static java.lang.ClassLoader custom_loader = null;
    public static long latest = 0;

    public static void reset() {
        custom_loader = null;
    }

    private static long getLatestModifiedDate(File dir) {
        File[] files = dir.listFiles();
        long latestDate = 0;
        for (File file : files) {
            long fileModifiedDate = file.isDirectory() 
                ? getLatestModifiedDate(file) : file.lastModified();
            if (fileModifiedDate > latestDate) {
                latestDate = fileModifiedDate;
            }
        }
        return Math.max(latestDate, dir.lastModified());
    }
    
    public static java.lang.ClassLoader get() {
        File src_path = getSrcPath();
        long x = getLatestModifiedDate(src_path);
        if (x > latest) {
            custom_loader = null;
            latest = x;
        }
        
        if (custom_loader == null) {
            custom_loader = new JavaSourceClassLoader(
                ClassLoader.getSystemClassLoader(),
                new File[]{src_path}, 
                null);
        }
        return custom_loader;
    }
}
    
