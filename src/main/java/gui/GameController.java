package gui;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.TextField;
import state.ChessState;
import state.Direction;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class GameController {

    @FXML
    private GridPane board;
    @FXML
    private TextField moveField;
    private ChessState state;
    private boolean firstClick = true;
    private int firstRow;
    private int firstCol;
    private IntegerProperty moveNumber = new SimpleIntegerProperty();
    @FXML
    public void initialize() {
        resetGame();
        loadImages();
        setupBoardClickHandlers();
        moveCounter();
    }

    private void resetGame() {
        state = new ChessState();
        moveNumber.set(0);
    }
    private void moveCounter() {
        moveField.textProperty().bind(moveNumber.asString());
    }


    public void loadImages() {
        try {

            ImageView wBishop = createImageView("/images/w-bishop.png");
            ImageView bBishop = createImageView("/images/bishop.png");
            ImageView wRook = createImageView("/images/w-rook.png");
            ImageView bRook = createImageView("/images/rook.png");
            ImageView kingView = createImageView("/images/king.png");

            wBishop.setOnMouseClicked(this::handlePieceClick);
            bBishop.setOnMouseClicked(this::handlePieceClick);
            wRook.setOnMouseClicked(this::handlePieceClick);
            bRook.setOnMouseClicked(this::handlePieceClick);
            kingView.setOnMouseClicked(this::handlePieceClick);

            board.add(kingView, 0, 0);
            board.add(wBishop, 1, 0);
            board.add(bBishop, 2, 0);
            board.add(wRook, 0, 1);
            board.add(bRook, 1, 1);


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error");
        }
    }
    @FXML
    private void pressButton(){
        clearBoard();
        resetGame();
        loadImages();
    }

    private ImageView createImageView(String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(100);
        imageView.setFitHeight(100);
        return imageView;
    }
    private void setupBoardClickHandlers() {
        for (int row = 0; row < 2; row++) {
            for (int col = 0; col < 3; col++) {
                int finalRow = row;
                int finalCol = col;
                StackPane cell = new StackPane();
                cell.setOnMouseClicked(event -> handleCellClick(event, finalRow, finalCol));
                board.add(cell, finalCol, finalRow);
            }
        }
    }

    private void handleCellClick(MouseEvent event, int row, int col) {
        //System.out.printf("Click on cell at (%d,%d)%n", row, col);

        if (firstClick) {

            firstRow = row;
            firstCol = col;
            firstClick = false;
        } else {

            Direction direction = getDirectionFromClickPosition(firstRow, firstCol, row, col);
            int pieceId = getPieceIdFromPosition(firstRow, firstCol);
            if (pieceId != -1 && direction != null && state.canMove(pieceId, direction)) {
                state.move(pieceId, direction);
                movePieceImage(firstRow, firstCol, row, col);
                moveNumber.set(moveNumber.get() + 1);
                if (state.isGoal()) {
                    System.out.println("You win!");
                    clearBoard();
                    resetGame();
                    loadImages();
                }

            }
            else {
                System.out.println("Invalid move!");
            }
            firstClick = true;
        }
    }

    private void handlePieceClick(MouseEvent event) {
        var piece = (ImageView) event.getSource();
        var row = GridPane.getRowIndex(piece);
        var col = GridPane.getColumnIndex(piece);
        if (row == null || col == null) {
            return;
        }
        handleCellClick(event, row, col);
    }

    private int getPieceIdFromPosition(int row, int col) {
        String strPiece = checkPieceAtPosition(row, col);
        return getIntFromPiece(strPiece);
    }


    private void movePieceImage(int fromRow, int fromCol, int toRow, int toCol) {
        Node targetNode = getPieceNodeAt(toRow, toCol);
        if (targetNode == null) {
            Node piece = getPieceNodeAt(fromRow, fromCol);
            if (piece != null) {
                board.getChildren().remove(piece);
                board.add(piece, toCol, toRow);
            }
        } else {
            System.out.println("invalid mv");
        }
    }

    private Node getPieceNodeAt(int row, int col) {
        for (Node node : board.getChildren()) {
            Integer nodeRow = GridPane.getRowIndex(node);
            Integer nodeCol = GridPane.getColumnIndex(node);
            if (nodeRow != null && nodeCol != null && nodeRow == row && nodeCol == col) {
                if (node instanceof ImageView) {
                    return node;
                }
            }
        }
        return null;
    }

    private String checkPieceAtPosition(int row, int col) {
        return state.getPiece(row, col);
    }

    private int getIntFromPiece(String piece) {
        switch (piece) {
            case "King":
                return 0;
            case "Bishop":
                return 1;
            case "Bishop1":
                return 2;
            case "Rock":
                return 3;
            case "Rock1":
                return 4;
            default:
                return -1;
        }
    }

    private Direction getDirectionFromClickPosition(int firstRow, int firstCol, int secondRow, int secondCol) {
        int rowDiff = secondRow - firstRow;
        int colDiff = secondCol - firstCol;

        if (rowDiff == -1 && colDiff == 0) {
            return Direction.Up;
        } else if (rowDiff == 1 && colDiff == 0) {
            return Direction.Down;
        } else if (rowDiff == 0 && colDiff == -1) {
            return Direction.Left;
        } else if (rowDiff == 0 && colDiff == 1) {
            return Direction.Right;
        } else if (rowDiff == -1 && colDiff == -1) {
            return Direction.DiagonalLeftUp;
        } else if (rowDiff == -1 && colDiff == 1) {
            return Direction.DiagonalRightUp;
        } else if (rowDiff == 1 && colDiff == -1) {
            return Direction.DiagonalLeftDown;
        } else if (rowDiff == 1 && colDiff == 1) {
            return Direction.DiagonalRightDown;
        }
        return null;
    }
    private void clearBoard() {
        board.getChildren().removeIf(t-> t instanceof ImageView);
    }

}
