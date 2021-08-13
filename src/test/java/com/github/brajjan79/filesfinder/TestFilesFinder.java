package com.github.brajjan79.filesfinder;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

public class TestFilesFinder {

    private File mock_file;
    private File mock_dir;
    private File mock_dir_two;

    @Before
    public void setup() {
        mock_file = PowerMockito.mock(File.class);
        setupFile(mock_file, "mock_name.txt", false);
        mock_dir = PowerMockito.mock(File.class);
        setupFile(mock_dir, "mock_dir_name.txt", true);
        mock_dir_two = PowerMockito.mock(File.class);
        setupFile(mock_dir_two, "mock_dir_name_2.txt", true);
    }

    private void setupFile(File mock_entity, String name, boolean isDirectory) {
        PowerMockito.when(mock_entity.isDirectory()).thenReturn(isDirectory);
        PowerMockito.when(mock_entity.isFile()).thenReturn(!isDirectory);
        PowerMockito.when(mock_entity.getName()).thenReturn(name);
    }

    @Test
    public void testFindSingleFile() throws Throwable {
        final File[] foundFiles = FilesFinder.find("mock_name", mock_file);
        assertEquals("One matching files should have been found.", 1, foundFiles.length);
    }

    @Test
    public void testFindSingleFileNoMatch() throws Throwable {
        final File[] foundFiles = FilesFinder.find("no_match", mock_file);
        assertEquals("No matching file should have been found.", 0, foundFiles.length);
    }

    @Test
    public void testFindInSingleDirTwoFiles() throws Throwable {
        PowerMockito.when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file });
        final File[] foundFiles = FilesFinder.find("mock_name", mock_dir);
        assertEquals("Two matching files should have been found in dir.", 2, foundFiles.length);
    }

    @Test
    public void testFindInTwoDirsFourFiles() throws Throwable {
        PowerMockito.when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, mock_dir_two });
        PowerMockito.when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file });
        final File[] foundFiles = FilesFinder.find("mock_name", mock_dir);
        assertEquals("Four matching files should have been found in 2 dirs.", 4, foundFiles.length);
    }

    @Test
    public void testFindTwoDirsNoFiles() throws Throwable {
        PowerMockito.when(mock_dir.listFiles()).thenReturn(new File[] { mock_file, mock_file, mock_dir_two });
        PowerMockito.when(mock_dir_two.listFiles()).thenReturn(new File[] { mock_file, mock_file });
        final File[] foundFiles = FilesFinder.find("not_a_name_in_mock", mock_dir);
        assertEquals("No matching files should have been found in 2 dirs.", 0, foundFiles.length);
    }

}
