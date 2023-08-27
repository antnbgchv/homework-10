package bgchv.antn;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZippedFilesTest {

    String pdfFileContent = "This is a small demonstration .pdf file";
    String xlsxContent = "Homer Simpson";


    private ZipInputStream openZip() {
        InputStream is = cl.getResourceAsStream("zip/zipSample.zip");
        return new ZipInputStream(is);
    }
    private final ClassLoader cl = ZippedFilesTest.class.getClassLoader();

    @DisplayName("Checking the contents of pdf")
    @Test
    void checkPdfContentTest() throws Exception {
        try (ZipInputStream zipInputStream = openZip()) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".pdf")) {
                    PDF pdf = new PDF(zipInputStream);
                    Assertions.assertTrue(pdf.text.contains(pdfFileContent));
                }
            }
        }
    }

    @DisplayName("Checking the contents of csv")
    @Test
    void checkCsvContentTest() throws Exception {
        try (ZipInputStream zipInputStream = openZip();
             Reader reader = new InputStreamReader(zipInputStream)) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".csv")) {
                    final CSVReader csv = new CSVReader(reader);
                    List<String[]> strings = csv.readAll();

                    Assertions.assertEquals(5, strings.size());
                    Assertions.assertArrayEquals
                            (new String[]{"Full name", " Email", " Address"}, strings.get(0));
                    Assertions.assertArrayEquals
                            (new String[]{"Homer Simpson", " mr.plow@gmail.com", " 742 Evergreen Terrace"},
                                    strings.get(1));
                    Assertions.assertArrayEquals
                            (new String[]{"Marge Simpson", " marjorie_bouvier@gmail.com", " 742 Evergreen Terrace"},
                                    strings.get(2));
                    Assertions.assertArrayEquals
                            (new String[]{"Bart Simpson", " el-barto@gmail.com", " 742 Evergreen Terrace"},
                                    strings.get(3));
                    Assertions.assertArrayEquals
                            (new String[]{"Lisa Simpson", " ladypenelopeariel@gmail.com", " 742 Evergreen Terrace"},
                                    strings.get(4));
                }
            }
        }
    }

    @DisplayName("Checking the contents of xlsx")
    @Test
    void checkXlsxContentTest() throws Exception {
        try (ZipInputStream zipInputStream = openZip()) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                final String name = entry.getName();
                if (name.contains(".xlsx")) {
                    XLS xls = new XLS(zipInputStream);
                    Assertions.assertEquals(xlsxContent, xls.excel.getSheetAt(0)
                            .getRow(1)
                            .getCell(0)
                            .getStringCellValue());
                }
            }
        }
    }

}