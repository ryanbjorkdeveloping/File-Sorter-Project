import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SortRunner extends Application 
{
    private static final String FOLDER_DESKTOP = "Desktop";
    private static final String FOLDER_DOWNLOADS = "Downloads";
    private static final String FOLDER_DOCUMENTS = "Documents";
    private static final String FOLDER_PICTURES = "Pictures";
    private static final String FOLDER_MUSIC = "Music";

    private Path getOneDrivePath() {
        String oneDrive = System.getenv("OneDrive");
        if (oneDrive == null || oneDrive.isBlank()) {
            oneDrive = System.getenv("OneDriveConsumer");
        }
        if (oneDrive == null || oneDrive.isBlank()) {
            oneDrive = System.getenv("OneDriveCommercial");
        }
        if (oneDrive == null || oneDrive.isBlank()) {
            return null;
        }
        return Paths.get(oneDrive);
    }

    private Path resolveUserFolder(String folderName) {
        String normalized = folderName == null ? "" : folderName.trim();

        Path oneDrive = getOneDrivePath();
        if (oneDrive != null) {
            Path candidate = oneDrive.resolve(normalized);
            if (Files.exists(candidate)) {
                return candidate;
            }
        }

        Path local = Paths.get(System.getProperty("user.home"), normalized);
        if (Files.exists(local)) {
            return local;
        }

        return oneDrive != null ? oneDrive.resolve(normalized) : local;
    }

    @Override
    public void start(Stage primaryStage) {
        // Define your keyword list
        Button btn = new Button("Sort Files Within Folder");
        TextArea logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefRowCount(12);

        btn.setOnAction(e -> {
            logArea.clear();
            logArea.appendText("Starting sort...\n");
            // Define the paths for the folders and files
            Path imageFiles = resolveUserFolder(FOLDER_PICTURES).resolve("UnspecifiedImages");
            Path docFiles = resolveUserFolder(FOLDER_DOCUMENTS).resolve("UnspecifiedDocFiles");
            Path musicFiles = resolveUserFolder(FOLDER_MUSIC).resolve("UnspecifiedMusicFiles");
            Path allFilesFolder = resolveUserFolder(FOLDER_DOWNLOADS);

            logArea.appendText("Source folder: " + allFilesFolder + "\n");
            logArea.appendText("Image folder: " + imageFiles + "\n");
            logArea.appendText("Doc folder: " + docFiles + "\n");
            logArea.appendText("Music folder: " + musicFiles + "\n");

            if (!Files.exists(allFilesFolder)) {
                logArea.appendText("Downloads folder not found. Update the path in SortRunner.\n");
                return;
            }

            // Define the keyword list for sorting by name
            List<String> keywordList = new ArrayList<>();
            List<Path> specifiedFilePath = new ArrayList<>();

            // Add keywords to the list
            keywordList.add("project");
            keywordList.add("school");
            keywordList.add("homework");

            //create folders in desktop using keywords array
            for (String keyword : keywordList) {
                specifiedFilePath.add(resolveUserFolder(FOLDER_DESKTOP).resolve(keyword));
            }

            for (Path keywordPath : specifiedFilePath) {
                logArea.appendText("Keyword folder: " + keywordPath + "\n");
            }

            AllFiles sortFolderFiles = new AllFiles(imageFiles, docFiles, musicFiles, specifiedFilePath, keywordList, allFilesFolder);
            sortFolderFiles.createDocTypeFolders();

            sortFolderFiles.sortAllFiles();
            logArea.appendText("Sorting complete.\n");
        });

        VBox root = new VBox(10);
        root.getChildren().addAll(btn, logArea);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("File Sorter Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
