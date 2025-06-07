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
            Path imageFiles = Paths.get("D:\\File Sorter ProjectTesting Folder\\Images");
            Path docFiles = Paths.get("D:\\File Sorter ProjectTesting Folder\\Documents");
            Path musicFiles = Paths.get("D:\\File Sorter ProjectTesting Folder\\Music");
            Path allFilesFolder = Paths.get("D:\\File Sorter ProjectTesting Folder\\Specific Files Folder");

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
