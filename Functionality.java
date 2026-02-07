import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


//this class is for the functionality of the program, such as creating folders, adding keywords, and getting the list of keywords, and creating lists of file types
public class Functionality {

  private Path imageFiles;
  private Path docFiles;
  private Path musicFiles;

  private List<Path> specifiedFilePath;
  private List<String> keywordList;

  private Path resolveDataFile(String fileName) {
    Path direct = Paths.get(fileName);
    if (Files.exists(direct)) {
      return direct;
    }

    Path subfolder = Paths.get("File Sorter Project", fileName);
    if (Files.exists(subfolder)) {
      return subfolder;
    }

    return direct;
  }

  // Add this constructor for AllFiles compatibility
  public Functionality (Path imageFiles, Path docFiles, Path musicFiles, List<Path> specifiedFilePath, List<String> keywordList) {
    this.imageFiles = imageFiles;
    this.docFiles = docFiles;
    this.musicFiles = musicFiles;
    this.specifiedFilePath = specifiedFilePath;
    this.keywordList = keywordList;
  }

  //to create folders, we want to check if they exist, if not, create them
  public void createDocTypeFolders() {
    createImageFolder();
    createDocFolder();
    createMusicFolder();
    createSpecifiedFolder();
  }

  //create the folders for a specific file name type, and return the path to the folder
  //the specified folders will be automatically put in the Desktop part of the computer, but that can be changed later on
  public List<Path> createSpecifiedFolder() {
    List<Path> createdFolders = new ArrayList<>();

    for (int i = 0; i < specifiedFilePath.size(); i++) {
      if (Files.notExists(specifiedFilePath.get(i))) {
        try {
          Files.createDirectories(specifiedFilePath.get(i));
          createdFolders.add(specifiedFilePath.get(i));
        }
        catch (IOException e) {
          System.out.println("Unable to create directory");
        }
      }
    }
    return createdFolders;
  }

  public Path createImageFolder() {
    //check for if there isn't any image folder, if not, create one
    if (Files.notExists(imageFiles)) {
      try {
        Files.createDirectories(imageFiles);
      }
      catch (IOException e) {
        System.out.println("Unable to create directory");
      }
    }

    return imageFiles;
  }

  public Path createDocFolder() {
    //check for if there isn't any doc folder, if not, create one
    if (Files.notExists(docFiles)) {
      try {
        Files.createDirectories(docFiles);
      }
      catch (IOException e) {
        System.out.println("Unable to create directory");
      }
    }

    return docFiles;
  }

  public Path createMusicFolder() {
    //check for if there isn't any music folder, if not, create one
    if (Files.notExists(musicFiles)) {
      try {
        Files.createDirectories(musicFiles);
      }
      catch (IOException e) {
        System.out.println("Unable to create directory");
      }
    }

    return musicFiles;
  }

  //create an array list to store all the image files and convert it to an array
  public String[] imgList() {
    ArrayList<String> imgFileList = new ArrayList<String>();
    try {
      Files.lines(resolveDataFile("ImageFiles.txt"))
        .map(String::trim)
        .filter(line -> !line.isEmpty())
        .forEach(imgFileList::add);
    } 
    catch (IOException e) {
      System.out.println("Error reading image files: " + e.getMessage());
    }
    return imgFileList.toArray(new String[imgFileList.size()]);
  }

  //create an array list to store all the doc files and convert it to an array
  public String[] docList() {
    ArrayList<String> docFileList = new ArrayList<String>();
    try{
      Files.lines(resolveDataFile("DocFiles.txt"))
        .map(String::trim)
        .filter(line -> !line.isEmpty())
        .forEach(docFileList::add);
    }
    catch (IOException e) {
      System.out.println("Error reading document files: " + e.getMessage());
    }
    return docFileList.toArray(new String[docFileList.size()]);
  }

  //create an array list to store all the music files and convert it to an array
  public String[] musicList() {
    ArrayList<String> musicFileList = new ArrayList<String>();
    try{
      Files.lines(resolveDataFile("MusicFiles.txt"))
        .map(String::trim)
        .filter(line -> !line.isEmpty())
        .forEach(musicFileList::add);
    } 
    catch (IOException e) {
      System.out.println("Error reading music files: " + e.getMessage());
    }
    return musicFileList.toArray(new String[musicFileList.size()]);
  }
  
}