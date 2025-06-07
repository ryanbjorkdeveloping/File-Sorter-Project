import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AllFiles extends Functionality {

  Path allFilesFolder;

  public AllFiles (Path imageFiles, Path docFiles, Path musicFiles, Path allFilesFolder) {
    super(imageFiles, docFiles, musicFiles);
    this.allFilesFolder = allFilesFolder;
  }

  //list all the files in the folders to use to sort them in another method. FOR TESTING PURPOSES BEFORE ACTUAL PROGRAM
  public static List<Path> listSpecificFiles(Path path) {
    List<Path> fileList = new ArrayList<>();

    try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
      for (Path entry : stream) {
        if (Files.isRegularFile(entry)) {
          fileList.add(entry);
        }
      }
    }
    catch (IOException e) {
      System.out.println("Error listing files");
    }
    
    return fileList;
  }

  //what we're actually going to use to list all the files in the computer. ACTUAL PROGRAM. DO NOT TOUCH UNTIL CODE WORKS CORRECTLY ALL OTHER PLACES
  // public static List<Path> listFiles() {

  //   List<Path> fileList = new ArrayList<>();
  //   Iterator<Path> iterator = Files.walk(Paths.get("C:\\")).iterator();

  //   try(Stream<Path> stream = Files.walk(root)) {
  //     for (Path entry : stream) {
  //       if (Files.isRegularFile(entry)) {
  //         fileList.add(entry);
  //       }
  //     }
  //   }
  //   catch (IOException e) {
  //     System.out.println("Error listing files");
  //   }

  //   return fileList;
  //}

// public void sortAllFiles(){
//   sortAllFilesByFileType();
//   sortAllFilesByKeyword();
// }
  
  // public void sortAllFilesByKeyword(String keyword, String[] keywords) {
  //   for (int i = 0; i < keywords.length; i++) {
  //     if (keyword.contains(keywords[i])) {
  //       //move the file to the corresponding folder
  //     }
  //   }
  // }
  
  //for now, make this function so that it only sorts out all the files within a certain folder for TESTING PURPOSES
  public void sortByFileType() {

    List<Path> allFiles = listSpecificFiles(allFilesFolder);

    for (int j = 0; j < allFiles.size(); j++) {
      Path file = allFiles.get(j);

      //check for all image files
      for (int i = 0; i < imgList().length; i++) {
        if (file.toString().contains(imgList()[i])) {
          try{
            Files.move(file, createImageFolder().resolve(file.getFileName()), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
          }
          catch (IOException e) {
            System.out.println("Error moving image file: " + e.getMessage());
          }
        }
      }

      //check for all doc files
      for (int i = 0; i < docList().length; i++) {
        if (file.toString().contains(docList()[i])) {
          try{
            Files.move(file, createDocFolder().resolve(file.getFileName()), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
          }
          catch (IOException e) {
            System.out.println("Error moving image file: " + e.getMessage());
          }
        }
      }

      //check for all music files
      for (int i = 0; i < musicList().length; i++) {
        if (file.toString().contains(musicList()[i])) {
          try{
            Files.move(file, createMusicFolder().resolve(file.getFileName()), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
          }
          catch (IOException e) {
            System.out.println("Error moving image file: " + e.getMessage());
          }
        }
      }
    }
    
  }
}