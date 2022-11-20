package com.project.datastructurevisualization;

import com.project.datastructure.list.ButtonNode;
import com.project.datastructure.list.DoublyLinkedListNode;
import com.project.datastructure.list.SimplyLinkedList;
import com.project.datastructure.list.DoublyLinkedList;
import com.project.shapes.Arrow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;

import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private SimplyLinkedList simplyLinkedList = new SimplyLinkedList();
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    private int currentLength = 1;

    @FXML
    private Label welcomeText;

    @FXML
    private ComboBox<String> structureTypeSelect = new ComboBox<>();

    @FXML
    private TextField inputValue = new TextField();

    @FXML
    private ComboBox<Integer> positionList = new ComboBox<>();

    @FXML
    private Button btnAdd = new Button();

    @FXML
    private ScrollPane shapesPane = new ScrollPane();

    @FXML
    private Group shapesGroup = new Group();

    private String currentType = "";

    String[] types = {"Lista Simplesmente Encadeada", "Lista Duplamente Encadeada"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ComboBox structure type config
        this.structureTypeSelect.getItems().addAll(this.types);
        this.structureTypeSelect.setOnAction(this::OnstructureTypeSelect);

        //
        this.positionList.getItems().add(1);

        //Add button config
        this.btnAdd.setOnAction(this::onBtnAddClick);

    }

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("aa");
    }

    public void OnstructureTypeSelect(ActionEvent event) {
        this.currentType = this.structureTypeSelect.valueProperty().getValue();
        shapesGroup.getChildren().clear();
        if (currentType.equals("Lista Simplesmente Encadeada")) {
            renderSimpleLinkedList();
            this.currentLength = simplyLinkedList.getLength();
            updatePositionsInput();
        }
        else if (currentType.equals("Lista Duplamente Encadeada")) {
            renderDoublyLinkedList();
            this.currentLength = doublyLinkedList.getLength();
            updatePositionsInput();
        }
    }

    public void onBtnDeleteClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) {
                ButtonNode target = (ButtonNode) mouseEvent.getTarget();
                if (currentType.equals("Lista Simplesmente Encadeada")) {
                    if (this.simplyLinkedList.remove(target.getIndex())) {
                        shapesGroup.getChildren().clear();
                        renderSimpleLinkedList();
                        updatePositionsInput();
                    }
                } else if (currentType.equals("Lista Duplamente Encadeada")) {
                    
                }
            }
        }
    }

    public void onBtnAddClick(ActionEvent event) {
        if (currentType.equals("Lista Simplesmente Encadeada")) {
            if (this.simplyLinkedList.getLength() == 0) {
                this.simplyLinkedList.add(Integer.parseInt(this.inputValue.getText()));
                this.currentLength = simplyLinkedList.getLength();
                updatePositionsInput();

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                Integer value = Integer.parseInt(this.inputValue.getText());
                Integer position = Integer.parseInt(String.valueOf(this.positionList.valueProperty().getValue()));

                this.simplyLinkedList.insert(value, position);
                this.currentLength = simplyLinkedList.getLength();
                updatePositionsInput();

                shapesGroup.getChildren().clear();
                renderSimpleLinkedList();
            }

        } else if (currentType.equals("Lista Duplamente Encadeada")) {
            if (this.doublyLinkedList.getLength() == 0) {
                this.doublyLinkedList.add(Integer.parseInt(this.inputValue.getText()), 1);
                this.currentLength = doublyLinkedList.getLength();

                updatePositionsInput();

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                Integer value = Integer.parseInt(this.inputValue.getText());
                Integer position = Integer.parseInt(String.valueOf(this.positionList.valueProperty().getValue()));

                this.doublyLinkedList.add(value, position);
                this.currentLength = doublyLinkedList.getLength();

                updatePositionsInput();

                shapesGroup.getChildren().clear();
                renderDoublyLinkedList();

            }
        }
//        System.out.println(this.simplyLinkedList.getLength());
    }


    public void renderSimpleLinkedList() {
        for (int i = 1; i <= this.simplyLinkedList.getLength(); i++) {
            Integer value = this.simplyLinkedList.searchByPosition(i);

            if (i == 1) {
                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                ButtonNode lastRectangle = (ButtonNode) shapesGroup.getChildren().get(shapesGroup.getChildren().size() - 1);

                Arrow arrow = new Arrow();
                arrow.setStartY(13);
                arrow.setEndY(14);

                int start = 25;
                if (lastRectangle.getText().length() == 2)
                    start = 30;
                else if (lastRectangle.getText().length() == 3) {
                    start = 35;
                } else if (lastRectangle.getText().length() == 4) {
                    start = 42;
                } else if (lastRectangle.getText().length() == 5) {
                    start = 47;
                }

                arrow.setStartX(lastRectangle.getTranslateX() + start);
                arrow.setEndX(lastRectangle.getTranslateX() + 40 + start);

                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                newRectangle.setTranslateX(lastRectangle.getTranslateX() + 40 + start);

                shapesGroup.getChildren().add(arrow);
                shapesGroup.getChildren().add(newRectangle);
                lastRectangle = newRectangle;
            }

        }

    }

    public void renderDoublyLinkedList() {
        for (int i = 1; i <= this.doublyLinkedList.getLength(); i++) {
            Integer value = this.doublyLinkedList.searchByPosition(i);

            if (i == 1) {
                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                ButtonNode lastRectangle = (ButtonNode) shapesGroup.getChildren().get(shapesGroup.getChildren().size() - 1);

                Arrow arrow = new Arrow();
                arrow.setStartY(2);
                arrow.setEndY(3);

                Arrow arrowBack = new Arrow();
                arrowBack.setStartY(25);
                arrowBack.setEndY(25);

                int start = 25;
                if (lastRectangle.getText().length() == 2)
                    start = 30;
                else if (lastRectangle.getText().length() == 3) {
                    start = 35;
                } else if (lastRectangle.getText().length() == 4) {
                    start = 42;
                } else if (lastRectangle.getText().length() == 5) {
                    start = 47;
                }

                arrow.setStartX(lastRectangle.getTranslateX() + start);
                arrow.setEndX(lastRectangle.getTranslateX() + 40 + start);

                arrowBack.setEndX(lastRectangle.getTranslateX() + start);
                arrowBack.setStartX(lastRectangle.getTranslateX() + 40 + start);

                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                newRectangle.setTranslateX(lastRectangle.getTranslateX() + 40 + start);

                shapesGroup.getChildren().add(arrow);
                shapesGroup.getChildren().add(arrowBack);
                shapesGroup.getChildren().add(newRectangle);
                lastRectangle = newRectangle;
            }

        }

    }


    public void updatePositionsInput() {
        this.positionList.getItems().clear();

        for (int i = 1; i <= this.currentLength + 1; i++) {
            this.positionList.getItems().add(i);
        }
        this.positionList.setValue(this.currentLength + 1);

    }
}