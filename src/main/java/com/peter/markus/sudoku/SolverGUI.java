package com.peter.markus.sudoku;

import java.io.File;
import java.io.IOException;

import com.peter.markus.sudoku.strategy.BruteForceStrategy;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * JavaFX Application
 */
public class SolverGUI extends Application {
	
	CellController[][] puzzleView = new CellController[Puzzle.ROWS][Puzzle.COLUMNS];
	Puzzle puzzle;
	Stage stage;
	Button loadButton;
	Button solveButton;
    
    @Override
    public void start(Stage stage) {        
        VBox frame = new VBox(5, createPuzzleArea(), createButtonArea());
        
        Scene scene = new Scene(frame, 600, 600);
        scene.getStylesheets().add("game.css");
		stage.setTitle("Sudoku Puzzle Solver");
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
    
    private Node createPuzzleArea() {
        GridPane outerGrid = new GridPane();
        outerGrid.getStyleClass().add("game-grid");
        makeGrid(outerGrid);
        
    	for(int outerCol=0; outerCol < 3; outerCol++) {
    		for(int outerRow=0; outerRow < 3; outerRow++) {
        		GridPane innerGrid = new GridPane();
        		innerGrid.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        		innerGrid.getStyleClass().add("game-block");
        		makeGrid(innerGrid);

        		for(int innerCol=0; innerCol<3; innerCol++) {
        			for(int innerRow=0; innerRow<3; innerRow++) {
        				CellController cellController = new CellController("");
        				puzzleView[outerRow*3+innerRow][outerCol*3+innerCol] = cellController;
        				innerGrid.add(cellController.view, innerCol, innerRow);
        			}
         		}
        		outerGrid.add(innerGrid, outerCol, outerRow);
        	}
        }
    	VBox.setVgrow(outerGrid, Priority.ALWAYS);
        return outerGrid;
    }
    
    private void makeGrid(GridPane grid) {
        for(int col = 0; col < 3; col++) {
        	ColumnConstraints column = new ColumnConstraints();
        	column.setHgrow(Priority.ALWAYS);
        	grid.getColumnConstraints().add(column);
        }
        for(int row = 0; row < 3; row++) {
        	RowConstraints rowConstraint = new RowConstraints();
        	rowConstraint.setVgrow(Priority.ALWAYS);
        	grid.getRowConstraints().add(rowConstraint);
        }   	
    }
    
 
    private Node createButtonArea() {
        loadButton = new Button("Load Sudoku");
        loadButton.setAlignment(Pos.CENTER);
        loadButton.setOnAction(evt -> this.loadPuzzle());
        VBox setUpButtonArea = new VBox(5,loadButton);
        setUpButtonArea.setAlignment(Pos.CENTER);
        setUpButtonArea.setPadding(new Insets(5,5,10,5));
        HBox.setHgrow(setUpButtonArea, Priority.ALWAYS);
        
        solveButton = new Button("Solve Sudoku");
        solveButton.setAlignment(Pos.CENTER);
        solveButton.setOnAction(evt -> this.solveBruteForce());
        solveButton.setDisable(true);
        VBox solveButtonArea = new VBox(5,solveButton);
        solveButtonArea.setAlignment(Pos.CENTER);
        solveButtonArea.setPadding(new Insets(5,5,10,5));
        HBox.setHgrow(solveButtonArea, Priority.ALWAYS);
        
    	return new HBox(5,setUpButtonArea,solveButtonArea);   	
    }

	private void solveBruteForce() {
		loadButton.setDisable(true);
		solveButton.setDisable(true);
		BruteForceStrategy solver = new BruteForceStrategy(puzzle);

		Task<Void> task = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				solver.solve();
				loadButton.setDisable(false);
				return null;
			}
		};
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
			
	}

	private void loadPuzzle() {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
			File selectedFile = fileChooser.showOpenDialog(stage);
			if(selectedFile!=null) {
				puzzle = Puzzle.fromFile(selectedFile.getAbsolutePath());
				for(int row=0; row<Puzzle.ROWS; row++) {
					for(int col=0; col<Puzzle.COLUMNS; col++) {
						CellController cellController = puzzleView[row][col];
						Cell cell = puzzle.getCellAt(new Coordinate(row,col));
						cellController.bind(cell);
					}
				}
			}
			solveButton.setDisable(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private class CellController implements CellListener {
		
		Cell model;
		Label view;
		
		CellController(String labelText) {
			view = new Label(labelText);
			view.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
			view.setAlignment(Pos.CENTER);
	    	VBox.setVgrow(view, Priority.ALWAYS);
	    	HBox.setHgrow(view,Priority.ALWAYS);
	    	view.getStyleClass().add("game-cell");
		}
		
		@Override
		public void onValueChange(Integer oldValue, Integer newValue) {
			Integer value = newValue;
			Platform.runLater(() -> setText(value));
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
		
		public void bind(Cell cell) {
			model = cell;
			model.addListener(this);
			Integer value = model.getValue();
			if( value==null ) {
				view.getStyleClass().removeAll("game-cell");
				view.getStyleClass().add("game-cell-solved");
			} else {
				view.getStyleClass().removeAll("game-cell-solved");
				view.getStyleClass().add("game-cell");
			}	
			setText(value);
		}
		
		private void setText(Integer value) {
			view.setText( value==null ? "?" : value.toString());
			view.getParent().requestLayout();
		}
	}

}