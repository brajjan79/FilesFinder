package com.github.brajjan79.filesfinder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class TestFilesFinder {

    private File mock_file;
    private File no_match_file;
    private File mock_dir;
    private File mock_dir_two;

    @Before
    public void setup() {
        mock_file = mock(File.class);
        setupFile(mock_file, "mock_name.txt", false);
        no_match_file = mock(File.class);
        setupFile(no_match_file, "no_match_file.txt", false);
        mock_dir = mock(File.class);
        setupFile(mock_dir, "mock_dir_main", true);
        mock_dir_two = mock(File.class);
        setupFile(mock_dir_two, "mock_dir_name_level_2", true);
    }

    @Test
    public void testFindInSingleDirTwoFilesDefaultRecursive() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("mock", mock_dir);
        assertEquals("Two matching files should have been found in dir.", 2, foundFiles.length);
    }

    @Test
    public void testFindInSingleDirTwoFilesRecursiveOptionTrue() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("mock", mock_dir, true);
        assertEquals("Two matching files should have been found in dir.", 2, foundFiles.length);
    }

    @Test
    public void testFindInSingleDirTwoFilesRecursiveOptionFalse() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("mock", mock_dir, false);
        assertEquals("Two matching files should have been found in dir.", 2, foundFiles.length);
    }

    @Test
    public void testFindInTwoDirsFourFilesRecursiveOptionTrue() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file, mock_dir_two });
        when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("mock", mock_dir, true);
        assertEquals("Four matching files should have been found in 2 dirs.", 4, foundFiles.length);
    }

    @Test
    public void testFindInTwoDirsFourFilesRecursiveOptionFalse() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file, mock_dir_two });
        when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("mock", mock_dir, false);
        assertEquals("Two matching files should have been found in 1 dir.", 2, foundFiles.length);
    }
    @Test
    public void testFindInTwoDirsFourFilesRecursiveFindAll() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file, mock_dir_two });
        when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.findAll("mock", mock_dir, true);
        assertEquals("Five matching files or folders should have been found in 2 dirs.", 5, foundFiles.length);
    }

    @Test
    public void testFindTwoDirsNoFiles() throws Throwable {
        when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file, mock_dir_two });
        when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file, no_match_file });
        final File[] foundFiles = FilesFinder.find("not_a_name_in_mock", mock_dir);
        assertEquals("No matching files should have been found in 2 dirs.", 0, foundFiles.length);
    }

    private void setupFile(File mock_entity, String name, boolean isDirectory) {
        when(mock_entity.isDirectory()).thenReturn(isDirectory);
        when(mock_entity.getName()).thenReturn(name);
    }
}
