package aa.my.javaio.file;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import java.io.File;
import java.util.Collection;

/**
 * 多用工具啊
 */
public class A_BaseFile {
    public static void main(String[] args) {
        IOFileFilter canRead = CanReadFileFilter.CAN_READ;
        SuffixFileFilter suffixFileFilter = new SuffixFileFilter(".txt");
        Collection<File> files = FileUtils.listFilesAndDirs(new File("D:\\forTest"), suffixFileFilter, suffixFileFilter);
        for (File file : files) {
            System.out.println(file.getAbsolutePath());
        }
    }
}
