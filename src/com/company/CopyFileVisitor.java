package com.company;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class CopyFileVisitor implements FileVisitor {

    private Path copyFrom;
    private Path copyTo;


    @Override
    public FileVisitResult preVisitDirectory(Object dir, BasicFileAttributes attrs) throws IOException {
        Path newDir=copyTo.resolve(copyFrom.relativize((Path)dir));

        try{
            Files.copy((Path)dir,newDir);
        }catch (IOException e){
            System.out.println("unable to create" +newDir +"e");
            return FileVisitResult.SKIP_SUBTREE;
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(Object file, BasicFileAttributes attrs) throws IOException {
        try{
            copySubTree((Path) file, copyTo.resolve(copyFrom.relativize((Path)file)));
        }catch(IOException e){
            System.err.println("unable to copy" + copyFrom +e);
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFileFailed(Object file, IOException exc) throws IOException {
        return null;
    }

    @Override
    public FileVisitResult postVisitDirectory(Object dir, IOException exc) throws IOException {
        return null;
    }

    private static void copySubTree(Path from, Path to) throws IOException{
        Files.copy(from,to);
    }
}
