package fileManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;

public class FileManager {

    public static ArrayList<File> files = new ArrayList<>();
    public static StringBuilder path = new StringBuilder("/Users/milinia");
    public static File paths = new File(path.toString());

    public static void processFilesFromFolder(File folder){

        File[] folderEntries = folder.listFiles();

        if (folderEntries != null) {

            int i = 0;

            for (File entry : folderEntries) {

                if (entry.isDirectory()) {

                    if (files.size() > 0){
                        File file1 = files.get(i);
                        files.set(i, entry);
                        i++;
                        files.add(file1);
                    }
                    else{
                        files.add(entry);
                    }
                }
                else {
                    files.add(entry);
                }
            }
        }
        else{
            throw new IllegalArgumentException("Directory does not exist! Incorrect path!");
        }
    }

    public static void changeDirectoryDown (String name){

        File file = new File(name);

        if (file.isAbsolute()){

            if (!file.exists()) {
                throw new IllegalArgumentException("Directory does not exist!");
            }

            if (!file.isDirectory()){
                throw new IllegalArgumentException("You have entered not a directory!!!");
            }
            else{
                path.delete(0,path.capacity());
                path.append(name);
                paths = new File(String.valueOf(path));
                files.removeAll(files);
                processFilesFromFolder(paths);
            }
        }
        else{
            changeDirectoryDown(path + "/" + file.getName());
        }
    }

    public static void changeDirectoryUp (){
        files.removeAll(files);
        String[] path1 = path.toString().split("\\/");
        path.delete(0,path.capacity());
        for (int i = 0; i < path1.length - 1;i++){
            if (i != path1.length - 2 ){
                path.append(path1[i]).append("/");
            }
            else{
                path.append(path1[i]);
            }
        }

        File paths1 = new File(String.valueOf(path));
        processFilesFromFolder(paths1);
    }

    public static void show() {

        try{
            String[] directory = path.toString().split("\\/");
            int h = 0;

            for (int i = 1; i < directory.length; i++){

                if (directory[i].equals("")){
                    continue;
                }

                for (int j = h; j > 0; j--){
                    System.out.print(" ");
                }
                h = h + 3;
                System.out.print("|-");
                System.out.println(directory[i]);
            }

            for (int i = 0; i < files.size(); i++){

                for (int j = 0; j < h + 3; j++){
                    System.out.print(" ");
                }
                System.out.print("|");
                System.out.printf("%-20s",files.get(i).getName());
                System.out.print("  ");
                System.out.printf("%-15s",sizeOfFile(files.get(i)) + " bytes");
                System.out.print("  ");
                System.out.printf("%-15s",getLastModifiedTimeOfFile(files.get(i)));
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static long sizeOfFile (File file) throws IOException {
        return Files.size(Paths.get(file.getPath()));
    }

    public static FileTime getLastModifiedTimeOfFile(File file) throws IOException {
        return Files.getLastModifiedTime(Paths.get(file.getPath()));
    }

    public static void delete(String name) {

        try {
            File file = new File(name);
            boolean existing = false;

            if (!file.isAbsolute()) {
                delete(path.toString() + "/" + file.getName());
            }
            else {
                if (!file.exists()){
                    throw new IllegalArgumentException("File does not exist! Or entered incorrect path!");
                }
            }

            if (file.isDirectory()){

                File[] folderEntries = file.listFiles();

                if (folderEntries != null) {

                    for (File file1 : folderEntries) {

                        if (file1.isDirectory()){
                            delete(file1.getPath());
                        }
                        else{
                            Files.delete(file1.toPath());
                        }

                    }
                }
                Files.delete(file.toPath());
                files.remove(file);
            }
            else {
                files.remove(file);
                Files.delete(file.toPath());
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static void copy(String pathFrom, String pathTo) {

        try {
            File file1 = new File(pathFrom);
            File file2 = new File(pathTo);

            if (!file2.toPath().isAbsolute()){
                throw new IllegalArgumentException("Incorrect path to copy!");
            }
            else{
                if (!file2.exists()){
                    throw new IllegalArgumentException("No such file!");
                }
                if (!file2.isDirectory()){
                    throw new IllegalArgumentException("You can copy only to a folder!");
                }
            }

            if (file1.isAbsolute()){

                File path1 = new File(file2.getPath() + "/" + file1.getName());

                if (file1.isDirectory()){
                    if (!path1.exists()) {
                        Files.createDirectory(path1.toPath());
                    }
                }
                else{
                    if (!path1.exists()){
                        path1.createNewFile();
                    }
                }

                if (file1.exists()){

                    if (file1.isDirectory()){

                        File[] folderEntries = file1.listFiles();

                        if (folderEntries != null) {

                            for (File file : folderEntries) {
                                copy(file.getPath(), file2.getPath() + "/" + file1.getName());
                            }
                        }
                    }
                    else{
                        Files.copy(file1.toPath(),path1.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }
            }
            else{
                copy(path + "/" + file1.getName(), pathTo);
            }
        } catch (IllegalArgumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
