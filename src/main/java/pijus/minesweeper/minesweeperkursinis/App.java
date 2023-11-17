package pijus.minesweeper.minesweeperkursinis;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//Beginner is usually on an 8x8 or 9x9 board containing 10 mines, Intermediate is usually on a 16x16 board with 40 mines and expert is usually on a 30x16 board with 99 mines, however there is usually an option to customise board size and mine count.
public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("Board.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1050);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
   }


}