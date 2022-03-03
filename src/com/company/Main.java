package com.company;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static void main(String[] args) {
	openZip("D://Games//savegame/zip.zip","D://Games//savegame");
        System.out.println(openProgress("D://Games//savegame/save2.dat"));
    }
    public static GameProgress openProgress(String dirSave) {
        GameProgress gameProgress = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(dirSave))) {
            gameProgress = (GameProgress) ois.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return gameProgress;
    }

    public static void openZip(String dirZip, String dirOpenZip){
        try (ZipInputStream zip = new ZipInputStream(new FileInputStream(dirZip))) {
            ZipEntry entry;
            String name;
            while ((entry = zip.getNextEntry()) != null){
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(name);
                for (int c = zip.read(); c != -1; c = zip.read()) {
                    fout.write(c);
                }
                fout.flush();
                zip.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());;
        }
    }
}
