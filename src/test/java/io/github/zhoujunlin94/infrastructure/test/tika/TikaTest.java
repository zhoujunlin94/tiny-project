package io.github.zhoujunlin94.infrastructure.test.tika;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.model.PicturesTable;
import org.apache.poi.hwpf.usermodel.Picture;
import org.apache.tika.Tika;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

/**
 * @author zhoujunlin
 * @date 2025-05-22-15:59
 */
@Slf4j
public class TikaTest {

    @Test
    @SneakyThrows
    public void testParseTxtContent() {
        Tika tika = new Tika();
        File file = new File("D:\\Users\\Administrator\\Downloads\\test.txt");
        String content = tika.parseToString(file);
        log.warn(content);
    }

    @Test
    @SneakyThrows
    public void testParseDocContent() {
        Tika tika = new Tika();
        File file = new File("D:\\Users\\Administrator\\Downloads\\ceshi.doc");
        String content = tika.parseToString(file);
        log.warn(content);

        getDocImg("D:\\Users\\Administrator\\Downloads\\ceshi.doc");
    }


    @SneakyThrows
    private void getDocImg(String docFilePath) {
        try (FileInputStream fis = new FileInputStream(docFilePath);
             HWPFDocument doc = new HWPFDocument(fis)) {

            PicturesTable picturesTable = doc.getPicturesTable();
            List<Picture> pictures = picturesTable.getAllPictures();

            int i = 0;
            for (Picture picture : pictures) {
                String ext = picture.suggestFileExtension();
                byte[] data = picture.getContent();
                try (FileOutputStream fos = new FileOutputStream("image" + (i++) + "." + ext)) {
                    fos.write(data);
                }
            }
        }
    }

}
