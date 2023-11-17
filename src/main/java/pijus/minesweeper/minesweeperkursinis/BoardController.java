package pijus.minesweeper.minesweeperkursinis;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.GridPane;

public class BoardController {
    @FXML
    public Label FlagsLeft;
    @FXML
    public Label GameText;
    @FXML
    private GridPane gridPane;

    private Board board;
    private int width;
    private int height;
    public void initialize() {

        width= 16;
        height = 16;

        board = new Board(width, height, 40);
        FlagsLeft.setText("FlagsLeft:  " + board.getFlagsLeft());
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Button button = new Button();
                button.setPrefSize(60, 60);
                int finalX = x;
                int finalY = y;
                button.setOnMouseClicked(e -> handleTileAction(e, finalX, finalY));
                gridPane.add(button, x, y);
            }
        }
    }

    private void handleTileAction(MouseEvent event, int x, int y) {
        if (event.getButton() == MouseButton.PRIMARY) {
            board.openTile(x, y);
        } else if (event.getButton() == MouseButton.SECONDARY) {
            board.flagTile(x, y);
            FlagsLeft.setText("FlagsLeft:  " + board.getFlagsLeft());
        }

        updateUI();
        if (board.isGameOver()) {
            if (board.hasWon()) {
                GameText.setText("Congratulations");
                GameText.setStyle("-fx-background-color: #14f500;");
            } else {
                GameText.setText("Game Over");
                GameText.setStyle("-fx-background-color: #f50000;");

            }
        }
    }

    private void updateUI() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Button button = (Button) gridPane.getChildren().get(x * height + y);
                Tile tile = board.getTile(x, y);

                if (board.isGameOver()) {
                    if (tile.isMined()) {
                        button.setText("X");
                        button.setStyle("-fx-background-color: #ff5555; -fx-font-size: 20;");
                    }
                }


                if (tile.isOpened()) {
                    if (tile.isMined()) {
                        button.setText("X");
                        button.setStyle("-fx-background-color: #ff5555; -fx-font-size: 20;");
                    } else {
                        if (tile.getAdjacentMines() > 0) {
                            button.setText(String.valueOf(tile.getAdjacentMines()));
                            button.setStyle("-fx-background-color:#b0b0b0 ;-fx-font-size: 20");
                        } else {
                            button.setText("");
                            button.setStyle("-fx-background-color:#e0dddd ;-fx-font-size: 20");
                        }
                    }
                } else if (tile.isFlagged()) {
                    button.setText("F");
                    button.setStyle("-fx-background-color: #f88500;");
                } else {
                    button.setText("");
                    button.setStyle(null);
                }

                if (board.isGameOver()) {
                    button.setDisable(true);

                }
            }
        }
    }

}

