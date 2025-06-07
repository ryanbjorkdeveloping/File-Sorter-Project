import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;

public class Functionality {

  private Path imageFiles;
  private Path docFiles;
  private Path musicFiles;
  
  // Constructor to put everything together
  public Functionality (Path imageFiles, Path docFiles, Path musicFiles){

    this.imageFiles = imageFiles;
    this.docFiles = docFiles;
    this.musicFiles = musicFiles;
    
  }

  //to create folders, we want to check if they exist, if not, create them
  public void createDocTypeFolders() {
    createImageFolder();
    createDocFolder();
    createMusicFolder();
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
      Files.lines(Paths.get("ImageFiles.txt")).forEach(imgFileList::add);
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
      Files.lines(Paths.get("DocFiles.txt")).forEach(docFileList::add);
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
      Files.lines(Paths.get("MusicFiles.txt")).forEach(musicFileList::add);
    } 
    catch (IOException e) {
      System.out.println("Error reading music files: " + e.getMessage());
    }
    return musicFileList.toArray(new String[musicFileList.size()]);
  }
  
}