package com.github.brajjan79.filesfinder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.github.stringreformat.RegexFormat;

public class FilesFinder {

    /**
     * Find file/files based on the provided regex. Traverses provided directory and
     * subdirs.
     *
     * @param regex      - Regexp or search text
     * @param input_file - The provided file or directory
     * @return List of files, empty list if nothing is found.
     */
    public static File[] find(String regex, File input_file) {
        final List<File> fileList = findFiles(regex, input_file);
        return fileList.toArray(new File[0]);
    }

    private static List<File> findFiles(String regex, File input_file) {
        final List<File> fileList = new ArrayList<>();
        if (input_file.isDirectory()) {
            fileList.addAll(findFilesInDirectory(regex, input_file));
        } else if (RegexFormat.hasMatch(input_file.getName(), regex)) {
            fileList.add(input_file);
        }
        return fileList;
    }

    private static List<File> findFilesInDirectory(String regex, File input_file) {
        final List<File> fileList = new ArrayList<>();
        for (final File listed_files : input_file.listFiles()) {
            fileList.addAll(findFiles(regex, listed_files));
        }
        return fileList;

    }

}
