package gui;

import algorithms.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class Menu {

    public int numElements = 100;

    private final static int MENU_PADDING = 12;

    private GraphDisplay graphDisplay = new GraphDisplay(numElements);
    private final Group mainGroup = new Group();

    private boolean randomHeights = false;

    public Menu() {
        mainGroup.getChildren().addAll(
                graphDisplay.displayGroup(randomHeights), menuHBox()
        );
    }

    public HBox menuHBox() {
        HBox menu = createHBox();


        ComboBox<String> sortSelection = new ComboBox<>();
        sortSelection.getItems().addAll("Insertion Sort",
                                                    "Selection Sort",
                                                    "Bubble Sort",
                                                    "Merge Sort",
                                                    "Quick Sort",
                                                    "Heap Sort");
        sortSelection.setValue(sortSelection.getItems().get(0));
        menu.getChildren().add(sortSelection);


        Label fieldLabel = new Label("Number of bars:");
        fieldLabel.setStyle("-fx-font-size: 12");
        menu.getChildren().add(fieldLabel);
        TextField numBarsField = new TextField(String.valueOf(numElements));
        numBarsField.setPrefWidth(50);
        menu.getChildren().add(numBarsField);


        CheckBox cb = new CheckBox("Random height");
        cb.setOnMouseClicked(event -> randomHeights = cb.isSelected());
        menu.getChildren().add(cb);


        Button sortButton = new Button("sort");
        sortButton.setOnAction(action -> runSort(sortSelection.getValue(), numBarsField));
        menu.getChildren().add(sortButton);


        Button shuffleButton = new Button("shuffle");
        shuffleButton.setOnMouseClicked(mouseEvent -> graphDisplay.shuffle());
        menu.getChildren().add(shuffleButton);


        // application shortcuts
        menu.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode() == KeyCode.ENTER) {
                sortButton.requestFocus();
                runSort(sortSelection.getValue(), numBarsField);
            } else if (keyEvent.getCode().isDigitKey()) {
                numBarsField.requestFocus();
            } else if (keyEvent.getText().equalsIgnoreCase("r")){
                cb.requestFocus();
                cb.setSelected(!cb.isSelected());
            } else if (keyEvent.getCode().isLetterKey()) {
                sortSelection.requestFocus();
                sortSelection.show();
            } else if (keyEvent.getCode() == KeyCode.BACK_SPACE
                    || keyEvent.getCode() == KeyCode.ESCAPE) {
                shuffleButton.requestFocus();
            }
        });
        return menu;
    }

    public Group getGroup() { return mainGroup; }

    private HBox createHBox() {
        HBox menu = new HBox();
        menu.setPrefWidth(Main.DISPLAY_WIDTH);
        menu.setAlignment(Pos.CENTER);
        menu.setPadding(new Insets(MENU_PADDING));
        menu.setSpacing(MENU_PADDING);
        menu.setFillHeight(true);
        return menu;
    }

    // TODO
    private void createDurationSlider() {
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        hBox.relocate(Main.DISPLAY_WIDTH / 3, 40);

        Text text = new Text("Sort speed:");
        hBox.getChildren().add(text);

        Slider slider = new Slider();
        slider.setMin(50);
        slider.setMax(3000);
        slider.setValue(200);
        hBox.getChildren().add(slider);

    }


    private void runSort(String selection, TextField numBarsField) {
        try {
            // gets number of bars and sets graphDisplay accordingly
            numElements = Integer.parseInt(numBarsField.getText());
            graphDisplay = new GraphDisplay(numElements);
            mainGroup.getChildren().set(0, graphDisplay.displayGroup(randomHeights));

            // calls sort() on selected algorithm
            switch (selection) {
                case "Insertion Sort" -> {
                    InsertionSort insertionSort = new InsertionSort(graphDisplay);
                    insertionSort.sort();
                }
                case "Selection Sort" -> {
                    SelectionSort selectionSort = new SelectionSort(graphDisplay);
                    selectionSort.sort();
                }
                case "Bubble Sort" -> {
                    BubbleSort bubbleSort = new BubbleSort(graphDisplay);
                    bubbleSort.sort();
                }
                case "Merge Sort" -> {
                    MergeSort mergeSort = new MergeSort(graphDisplay);
                    mergeSort.sort();
                }
                case "Quick Sort" -> {
                    QuickSort quickSort = new QuickSort(graphDisplay);
                    quickSort.sort();
                }
                case "Heap Sort" -> {
                    HeapSort heapSort = new HeapSort(graphDisplay);
                    heapSort.sort();
                }
            }
        } catch (NumberFormatException n) {
            n.printStackTrace();
        }

    }
}
