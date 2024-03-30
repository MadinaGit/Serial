import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress gameProgress = new GameProgress(7, 3, 4, 47.542);
        GameProgress gameProgress1 = new GameProgress(2, 5, 3, 34.847);
        GameProgress gameProgress2 = new GameProgress(9, 2, 8, 74.388);

        saveGame("D://Games/savegames/save1.dat", gameProgress);
        saveGame("D://Games/savegames/save2.dat", gameProgress1);
        saveGame("D://Games/savegames/save3.dat", gameProgress2);

        List<String> saveDatList = new ArrayList<>();
        saveDatList.add("D://Games/savegames/save1.dat");
        saveDatList.add("D://Games/savegames/save2.dat");
        saveDatList.add("D://Games/savegames/save3.dat");

        zipFiles("D://Games/savegames/zip.zip", saveDatList);

        deleteFile(saveDatList);
    }

    public static void saveGame(String filePathway, GameProgress gameProgress) {


        try (FileOutputStream fos = new FileOutputStream(filePathway);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void zipFiles(String pathway, List<String> saveDatListAll) {
        try (ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(pathway))) {
            for (String s : saveDatListAll) {
                File fileName = new File(s);
                FileInputStream fis = new FileInputStream(s);
                ZipEntry entry = new ZipEntry(fileName.getName());
                zout.putNextEntry(entry);

                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
                fis.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


    public static void deleteFile(List<String> saveList) {

        for (String saveList1 : saveList) {

            File dir = new File(saveList1);

            if (dir.delete()) {
                System.out.println(" Файл удален");
            }
        }
    }
}
