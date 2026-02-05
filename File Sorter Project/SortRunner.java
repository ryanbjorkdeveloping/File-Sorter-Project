import java.nio.file.Paths;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class SortRunner extends Application 
{
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button("Sort Files Within Folder");

        btn.setOnAction(e -> {
            Path imageFiles = Paths.get("C:\\Users\\ryanb\\OneDrive\\Pictures\\UnspecificedImages");
            Path docFiles = Paths.get("C:\\Users\\ryanb\\OneDrive\\Documents\\UnspecifiedDocFiles");
            Path musicFiles = Paths.get("C:\\Users\\ryanb\\OneDrive\\Music\\unspecifiedMusicFiles");
            Path allFilesFolder = Paths.get("C:\\Users\\ryanb\\Downloads");

            AllFiles sortFolderFiles = new AllFiles(imageFiles, docFiles, musicFiles, allFilesFolder);
            sortFolderFiles.createDocTypeFolders();

            sortFolderFiles.sortByFileType();

        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 200);

        primaryStage.setTitle("File Sorter Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
