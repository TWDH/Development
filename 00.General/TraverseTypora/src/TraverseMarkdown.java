import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @Author He Zhu
 * @Date 2021-10-15 3:58 p.m.
 * @Version 1.0
 */
public class TraverseMarkdown {
    public static void main(String[] args) throws Exception {
        System.out.println(args);
        File file = new File("README.md");
        FileOutputStream fop = new FileOutputStream(file);
        if (!file.exists()) {
            file.createNewFile();
        }
        String currentPath = ".";
        printFiles(new File("D:\\2.Projects\\OTTPocket\\pocket-doc"), 1, fop, currentPath);
    }

    public static void printFiles(File dir, int tab, FileOutputStream fop, String currentPath) throws Exception {
        if (dir.isDirectory()) {
            File[] allFiles = dir.listFiles();

            try {
                assert allFiles != null;
                for (File allFile : allFiles) {

                    if (allFile.isFile() && allFile.getName().endsWith(".md") || allFile.isDirectory()) {
                        if (tab != 1) {
                            for (int j = 0; j < tab - 1; j++) {
                                fop.write("     ".getBytes());
                            }
                        }
                        if (tab == 1) {
                            currentPath = currentPath + "/";
                        }

                        fop.write("- [".getBytes());
                        fop.write(allFile.getName().getBytes(StandardCharsets.UTF_8));
                        fop.write("]".getBytes());
                        fop.write("(".getBytes());
                        if (tab == 1) {
                            fop.write(currentPath.getBytes());
                        } else {
                            fop.write((currentPath + "/" + allFile.getName()).getBytes());
                        }
                        fop.write(")".getBytes());
                        fop.write("\n".getBytes(StandardCharsets.UTF_8));
                    }

                    if (allFile.isDirectory()) {
                        if (tab == 1) {
                            currentPath = currentPath.substring(0, currentPath.length() - 1);
                        }
                        printFiles(allFile, tab + 1, fop, currentPath + "/" + allFile.getName());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
