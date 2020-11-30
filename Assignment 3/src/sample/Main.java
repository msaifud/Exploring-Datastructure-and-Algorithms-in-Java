package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.*;

// How to create a basic JavaFX window:
// 1. Create Stage (Window)
// 2. Rename the window
// 3. Create a GridPane Object to facilitate the creating of a layout
// 4. Define the components on the window
// 5. Add the components to the layout
// 6. Create a scene with the layout we created
// 7. link the scene to the stage
// 8. show the stage to display the window

public class Main extends Application {
    static Graph graph = new Graph();
    static ArrayList<Node> array = new ArrayList<Node>();
    static int count = 0;
    static String inputNode = "";
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Creating a GridPane object (layout)
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        // Setting the padding around the window - 10 pixels all around
        grid.setPadding(new Insets(10,10,10,10));

        // Creating Labels
        Label selectionLabel = new Label("Select your algorithm");

        // Creating radio buttons for DFS and BFS
        ToggleGroup group = new ToggleGroup();
        RadioButton dfs = new RadioButton("DFS");
        RadioButton bfs = new RadioButton("BFS");
        dfs.setToggleGroup(group);
        bfs.setToggleGroup(group);

        // Creating "Results" Label
        Label resultsLabel = new Label("Results:");
        Label results2Label = new Label();

        // Creating "Run" button
        Button runButton = new Button();
        runButton.setText("Run");

        runButton.setOnAction(e -> {
            if (dfs.isSelected()){
                int index = 0;
                for(int i = 0; i < array.size(); i++) {
                    if((array.get(i).name).equals(inputNode)) {
                        index = i;
                    }
                }
                String out = graph.DFS(array.get(index));
                results2Label.setText(out);
                graph.resetNodesVisited();
                ////////////////////////////////
                // Code when DFS is selected ///
                ////////////////////////////////
            }else{
                int index = 0;
                for(int i = 0; i < array.size(); i++) {
                    if((array.get(i).name).equals(inputNode)) {
                        index = i;
                    }
                }
                String out = graph.BFS(array.get(index));
                results2Label.setText(out);
                graph.resetNodesVisited();
                ////////////////////////////////
                // Code when BFS is selected ///
                ////////////////////////////////
            }
        });

        // Creating "Add Node" button
        Button addNodeButton = new Button();
        addNodeButton.setText("Add Node");
        addNodeButton.setOnAction(e -> {
            addNodeWindow();
        });

        // Creating "Add Edge" button
        Button addEdgeButton = new Button();
        addEdgeButton.setText("Add Edge");
        addEdgeButton.setOnAction(e -> {
            addEdgeWindow();
        });

        // Creating "Print Edges" button
        Label outputResultsLabel = new Label();
        Button printEdgeButton = new Button();
        printEdgeButton.setText("Print Edges");
        printEdgeButton.setOnAction(e -> {
            String output = "";
            for (int j = 0; j < array.size(); j++) {
                output = output + "Node " + array.get(j).name + " has an edge towards: ";
                for (int k = 0; k < array.size(); k++) {
                    if(graph.hasEdge(array.get(j), array.get(k))) {
                        output = output + array.get(k).name + " ";
                    }
                }
                output = output + "\n";
            }
            outputResultsLabel.setText(output);

            //////////////////////////////////////
            // Place code here to print results///
            //////////////////////////////////////
        });

        // Constraining GUI components to grid
        grid.setConstraints(selectionLabel, 0,0);
        grid.setConstraints(dfs, 0,1);
        grid.setConstraints(bfs, 1,1);
        grid.setConstraints(runButton, 0,2);
        grid.setConstraints(addNodeButton, 8,0);
        grid.setConstraints(addEdgeButton, 8,1);
        grid.setConstraints(printEdgeButton, 8,2);
        grid.setConstraints(resultsLabel, 0,5);
        grid.setConstraints(results2Label, 0,6);
        grid.setConstraints(outputResultsLabel, 0,7);


        // Adding components to grid
        grid.getChildren().addAll(selectionLabel,dfs,bfs,runButton,addNodeButton,addEdgeButton,printEdgeButton,
                resultsLabel, results2Label,outputResultsLabel);

        Scene homeScene = new Scene(grid, 600,400);
        primaryStage.setTitle("Graph");
        primaryStage.setScene(homeScene);
        primaryStage.show();
    }

    private static void addEdgeWindow(){
        Stage window = new Stage();
        window.setTitle("Add Edge");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label sourceLabel = new Label("Source");
        Label destinationLabel = new Label("Destination");

        // Creating Text Fields
        TextField sourceField = new TextField();
        // sourceField.getText() to get text entered by the user
        TextField destinationField = new TextField();
        // destinationField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            Node source = null;
            Node destination = null;
            for(int i = 0; i < array.size(); i++) {
                if((array.get(i).name).equals(sourceField.getText())) {
                    source = array.get(i);
                }
                if((array.get(i).name).equals(destinationField.getText())) {
                    destination = array.get(i);
                }
            }
            graph.addEdge(source, destination);
            window.close();
            //////////////////////////////////
            // Place code here to add nodes///
            //////////////////////////////////
        });

        grid.getChildren().addAll(sourceLabel,destinationLabel,sourceField,destinationField,addButton);

        grid.setConstraints(sourceLabel, 0,0);
        grid.setConstraints(destinationLabel, 0,1);
        grid.setConstraints(sourceField, 3,0);
        grid.setConstraints(destinationField, 3,1);
        grid.setConstraints(addButton, 1,2);

        Scene addNodeScene = new Scene(grid);
        window.setScene(addNodeScene);
        window.show();
    }

    private static void addNodeWindow(){
        Stage window = new Stage();
        window.setTitle("Add Node");
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(10);
        grid.setHgap(10);

        // Creating Labels
        Label nodeNameLabel = new Label("Node Name");

        // Creating Text Fields
        TextField nodeNameField = new TextField();
        // nodeNameField.getText() to get text entered by the user

        // Creating "Add" button
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            array.add(new Node(count, nodeNameField.getText()));
            count++;
            window.close();
            ////////////////////////////////////////
            // Place code here to add nodes names///
            ////////////////////////////////////////
        });

        grid.getChildren().addAll(nodeNameLabel,nodeNameField,addButton);

        grid.setConstraints(nodeNameLabel, 0,0);
        grid.setConstraints(nodeNameField, 3,0);
        grid.setConstraints(addButton, 1,2);

        grid.getChildren().addAll();
        Scene addEdgeScene = new Scene(grid);
        window.setScene(addEdgeScene);
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
