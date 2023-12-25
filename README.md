[![Java CI with Maven](https://github.com/brajjan79/FilesFinder/actions/workflows/maven.yml/badge.svg)](https://github.com/brajjan79/FilesFinder/actions/workflows/maven.yml)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/f8a038a363184bd2a112d31111d99452)](https://app.codacy.com/gh/brajjan79/FilesFinder/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)
[![Codacy Badge](https://app.codacy.com/project/badge/Coverage/f8a038a363184bd2a112d31111d99452)](https://app.codacy.com/gh/brajjan79/FilesFinder/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_coverage)
[![JitPack](https://jitpack.io/v/brajjan79/FilesFinder.svg)](https://jitpack.io/#brajjan79/FilesFinder)

# FilesFinder
JAVA plugin to search on local harddisk for files and directories where the name is matching a provided regexp and then 
returns a list of the found files.

## Usage

The search string may contain any regexp or word to match. This enables the coder to select if a name
should start with, contain or end with or simple be formatted in a specific way. With regexps this could
also be used to match files that does not contain "xxx" or well anything you may use regexps to match. 

**Imports:**
```JAVA
import java.io.File;
import com.github.brajjan79.filesfinder.FilesFinder
```

**Code:**
```JAVA
File someBaseDir = new File("path/to/dir");
String searchRegex = "test"

File[] foundFiles = FilesFinder.find(searchRegex, someBaseDir)

--> '"foundFiles" will contain all files with "test" in the name'

recursive = false;
File[] foundFiles = FilesFinder.find(searchRegex, someBaseDir, recursive)

--> '"foundFiles" will contain all files with "test" in the name in the provided base directory'

recursive = true;
File[] foundFiles = FilesFinder.findAll(searchRegex, someBaseDir, recursive)

--> '"foundFiles" will contain all files and directories with "test" in the name in the provided base directory'
```

## Installation

Recommended is to install the tool via a dependency manager like Maven or
Gradle.
The source data can be downloaded here:
<https://github.com/brajjan79/FilesFinder/tags>

### Maven, Gradle etc

See Jitpack: <https://jitpack.io/#brajjan79/FilesFinder>
