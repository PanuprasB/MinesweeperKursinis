module pijus.minesweeper.minesweeperkursinis {
    requires javafx.controls;
    requires javafx.fxml;


    opens pijus.minesweeper.minesweeperkursinis to javafx.fxml;
    exports pijus.minesweeper.minesweeperkursinis;
}