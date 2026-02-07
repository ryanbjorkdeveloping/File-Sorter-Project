import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//purpose of class: check to see if file has keyword in name, if so, move to folder with that keyword, if not, check file type and move to corresponding folder, if not, do nothing
public class AllFiles extends Functionality {

  // Path of where all the files are located that need to be sorted
  Path allFilesLocation;
  // List of keywords for sorting by name
  private final List<String> keywordList;
  private final List<Path> specifiedFilePath;

  public AllFiles (Path imageFiles, Path docFiles, Path musicFiles, List<Path> specifiedFilePath, List<String> keywordList, Path allFilesLocation) {
    super(imageFiles, docFiles, musicFiles, specifiedFilePath, keywordList);
    this.allFilesLocation = allFilesLocation;
    this.keywordList = keywordList;
    this.specifiedFilePath = specifiedFilePath;
  }

  //this is where we are actually checking whether to sort file based on keyword or file type, and then moving the file to the corresponding folder
  public void sortAllFiles() {
    try (DirectoryStream<Path> stream = Files.newDirectoryStream(allFilesLocation)) {
      for (Path file : stream) {
        if (!Files.isRegularFile(file)) {
          continue;
        }

        //2. checking for keywords in the file name, and if there is a match, move the file to the corresponding folder
        boolean movedByKeyword = sortByKeyword(file, keywordList);

        //3. if there is no match for the keywords, check for the file type and move to the corresponding folder
        if (!movedByKeyword) {
          sortByFileType(file);
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading files: " + e.getMessage());
    }
  }

  public boolean sortByKeyword(Path file, List<String> keywords) {
    String fileName = file.getFileName().toString().toLowerCase();

    for (int i = 0; i < keywords.size() && i < specifiedFilePath.size(); i++) {
      String keyword = keywords.get(i).trim().toLowerCase();

      if (keyword.isEmpty()) {
        continue;
      }

      if (fileName.contains(keyword)) {
        try{
          Path destDir = specifiedFilePath.get(i);
          Files.createDirectories(destDir);
          Path destination = destDir.resolve(file.getFileName());
          Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
          System.out.println("Moved by keyword to: " + destination);
          return true;
        }
        catch (IOException e) {
          System.out.println("Error moving file: " + e.getMessage());
        }
      }
    }
    return false;
  }
  
  public void sortByFileType(Path file) {
      String fileName = file.getFileName().toString().toLowerCase();

      //check for all image files
      for (int i = 0; i < imgList().length; i++) {
        String ext = imgList()[i].trim().toLowerCase();
        if (ext.isEmpty()) {
          continue;
        }
        if (!ext.startsWith(".")) {
          ext = "." + ext;
        }
        if (fileName.endsWith(ext)) {
          try{
            Path destination = createImageFolder().resolve(file.getFileName());
            Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved image to: " + destination);
            return;
          }
          catch (IOException e) {
            System.out.println("Error moving image file: " + e.getMessage());
          }
        }
      }

      //check for all doc files
      for (int i = 0; i < docList().length; i++) {
        String ext = docList()[i].trim().toLowerCase();
        if (ext.isEmpty()) {
          continue;
        }
        if (!ext.startsWith(".")) {
          ext = "." + ext;
        }
        if (fileName.endsWith(ext)) {
          try{
            Path destination = createDocFolder().resolve(file.getFileName());
            Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved document to: " + destination);
            return;
          }
          catch (IOException e) {
            System.out.println("Error moving document file: " + e.getMessage());
          }
        }
      }

      //check for all music files
      for (int i = 0; i < musicList().length; i++) {
        String ext = musicList()[i].trim().toLowerCase();
        if (ext.isEmpty()) {
          continue;
        }
        if (!ext.startsWith(".")) {
          ext = "." + ext;
        }
        if (fileName.endsWith(ext)) {
          try{
            Path destination = createMusicFolder().resolve(file.getFileName());
            Files.move(file, destination, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Moved music to: " + destination);
            return;
          }
          catch (IOException e) {
            System.out.println("Error moving music file: " + e.getMessage());
          }
        }
      }
    }
    
  }