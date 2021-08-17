package com.github.brajjan79.filesfinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.stringreformat.RegexFormat;

public class FilesFinder {

    private FilesFinder() {
    }

    /**
     * Find file/files based on the provided regex. Traverses provided directory and
     * sub directories. Will search recursively.
     *
     * @param regex      - Regexp or search text
     * @param input_file - The provided directory
     * @return List of files, empty list if nothing is found.
     */
    public static File[] find(String regex, File input_file) {
        return doFind(regex, input_file, true, false);
    }

    /**
     * Find file/files based on the provided regex. Traverses provided directory and
     * sub directories.
     *
     * @param regex      - Regexp or search text
     * @param input_file - The provided directory
     * @param recursive  - Search recursively or not
     * @return List of files, empty list if nothing is found.
     */
    public static File[] find(String regex, File input_file, boolean recursive) {
        return doFind(regex, input_file, recursive, false);
    }

    /**
     * Find file/files and directories based on the provided regex. Traverses
     * provided directory and sub directories if recursive is true.
     *
     * @param regex      - Regexp or search text
     * @param input_file - The provided directory
     * @param recursive  - Search recursively or not
     * @return List of files, empty list if nothing is found.
     */
    public static File[] findAll(String regex, File input_file, boolean recursive) {
        return doFind(regex, input_file, recursive, true);
    }

    private static File[] doFind(String regex, File input_file, boolean recursive, boolean findAll) {
        List<File> fileList;
        if (recursive) {
            fileList = traverseDirStructure(regex, input_file, findAll);
        } else {
            fileList = findFiles(regex, input_file, findAll);
        }
        return fileList.toArray(new File[0]);
    }

    private static List<File> traverseDirStructure(String regex, File input_file, boolean findAll) {
        final List<File> fileList = new ArrayList<>();
        for (final File listed_files : input_file.listFiles()) {
            if (listed_files.isDirectory()) {
                fileList.addAll(traverseDirStructure(regex, listed_files, findAll));
            }
        }

        fileList.addAll(findFiles(regex, input_file, findAll));

        return fileList;
    }

    private static List<File> findFiles(String regex, File input_file, boolean findAll) {
        final List<File> fileList = new ArrayList<>();
        for (final File listed_files : input_file.listFiles()) {
            if (RegexFormat.hasMatch(listed_files.getName(), regex)) {
                if (!listed_files.isDirectory() || findAll) {
                    fileList.add(input_file);
                }
            }
        }
        return fileList;
    }

}
