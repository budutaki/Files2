import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {

    public static void main(String[] args) {

        GameProgress saved1 = new GameProgress(70, 5, 4, 34.6);
        GameProgress saved2 = new GameProgress(45, 6, 5, 56.6);
        GameProgress saved3 = new GameProgress(89, 5, 7, 70.6);

        List<String> savedGames = Arrays.asList(
                "/Users/Mi/IdeaProjects/Files/Games/savedgames/saved1.txt",
                "/Users/Mi/IdeaProjects/Files/Games/savedgames/saved2.txt",
                "/Users/Mi/IdeaProjects/Files/Games/savedgames/saved3.txt");

        saveGame("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved1.txt", saved1);
        saveGame("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved2.txt", saved2);
        saveGame("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved3.txt", saved3);

        zipFiles("/Users/Mi/IdeaProjects/Files/Games/savedgames/saved.zip", savedGames);
    }


    public static boolean saveGame(String path, GameProgress gameProgress) {
        try (FileOutputStream fos = new FileOutputStream(path);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(gameProgress);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean zipFiles(String path, List<String> list) {
        File zipFile = new File(path);

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipFile))) {
            for (String l : list) {
                File file = new File(l);

                try (FileInputStream fis = new FileInputStream(file)) {
                    ZipEntry entry = new ZipEntry(file.getName());
                    zos.putNextEntry(entry);
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    zos.write(buffer);
                    zos.closeEntry();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}

