package com.project.datastructurevisualization;
import com.project.datastructure.list.ButtonNode;
import com.project.datastructure.list.SimplyLinkedList;
import com.project.datastructure.list.DoublyLinkedList;
import com.project.shapes.Arrow;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.ResourceBundle;


public class MainController implements Initializable {
    private SimplyLinkedList simplyLinkedList = new SimplyLinkedList();
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    private String currentType = "";

    String[] types = {"Lista Simplesmente Encadeada", "Lista Duplamente Encadeada", "Pilha", "Fila", "Árvore De Pesquisa"};

    @FXML
    private ComboBox<String> structureTypeSelect = new ComboBox<>();

    @FXML
    private TextField inputValue = new TextField();

    @FXML
    private ComboBox<Integer> positionList = new ComboBox<>();

    @FXML
    private Button btnAdd = new Button();

    @FXML
    private Group shapesGroup = new Group();

    @FXML
    private HBox controlsHBox = new HBox();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // ComboBox structure type config
        this.structureTypeSelect.getItems().addAll(this.types);
        this.structureTypeSelect.setOnAction(this::OnStructureTypeSelect);

        //
        this.positionList.getItems().add(1);

        //Add button config
        this.btnAdd.setOnAction(this::onBtnAddClick);

    }

    @FXML
    protected void onHelloButtonClick() {
        System.out.println("aa");
    }

    public void OnStructureTypeSelect(ActionEvent event) {
        this.currentType = this.structureTypeSelect.valueProperty().getValue();
        // Limpando a tela de desenho
        shapesGroup.getChildren().clear();

        // Testando se é do tipo lista para selecionar a ultima posição que pode ser adicionada
        if (currentType.equals("Lista Simplesmente Encadeada")) {
            renderSimpleLinkedList();
            updatePositionsInput(simplyLinkedList.getLength());
        }
        else if (currentType.equals("Lista Duplamente Encadeada")) {
            renderDoublyLinkedList();
            updatePositionsInput(doublyLinkedList.getLength());
        }

        // Redefinindo os controles para se adequear a estruturas de dados selecionada
        this.switchControls();


    }

    // Call-Back para o evento de click no botão de remover um novo elemento na lista
    public void onBtnDeleteClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) { // Double click
                ButtonNode target = (ButtonNode) mouseEvent.getTarget();
                if (currentType.equals("Lista Simplesmente Encadeada")) {
                    if (this.simplyLinkedList.remove(target.getIndex())) {
                        shapesGroup.getChildren().clear();
                        renderSimpleLinkedList();
                        updatePositionsInput(simplyLinkedList.getLength());
                    }
                } else if (currentType.equals("Lista Duplamente Encadeada")) {
                    if (this.doublyLinkedList.remove(target.getIndex())) {
                        shapesGroup.getChildren().clear();
                        renderDoublyLinkedList();
                        updatePositionsInput(doublyLinkedList.getLength());
                    }
                }
            }
        }
    }

    // Call-Back para o evento de click no botão de adicionar um novo elemento na lista
    public void onBtnAddClick(ActionEvent event) {
        if (currentType.equals("Lista Simplesmente Encadeada")) {
            if (this.simplyLinkedList.getLength() == 0) {
                this.simplyLinkedList.add(Integer.parseInt(this.inputValue.getText()));
                updatePositionsInput(simplyLinkedList.getLength());

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                int value = Integer.parseInt(this.inputValue.getText());
                int position = Integer.parseInt(String.valueOf(this.positionList.valueProperty().getValue()));

                this.simplyLinkedList.insert(value, position);
                updatePositionsInput(simplyLinkedList.getLength());

                shapesGroup.getChildren().clear();
                renderSimpleLinkedList();
            }

        } else if (currentType.equals("Lista Duplamente Encadeada")) {
            if (this.doublyLinkedList.getLength() == 0) {
                this.doublyLinkedList.add(Integer.parseInt(this.inputValue.getText()), 1);
                updatePositionsInput(doublyLinkedList.getLength());

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1);
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                int value = Integer.parseInt(this.inputValue.getText());
                int position = Integer.parseInt(String.valueOf(this.positionList.valueProperty().getValue()));

                this.doublyLinkedList.add(value, position);
                updatePositionsInput(doublyLinkedList.getLength());

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

    public void renderStack(){
        // TODO
    }

    public void renderQueue(){
        // TODO
    }

    public void renderTree(){
        // TODO
    }

    // Atualiza as posições disponiveis para o caso de listas
    public void updatePositionsInput(int currentLength) {
        this.positionList.getItems().clear();
        System.out.println(currentLength);
        for (int i = 1; i <= currentLength + 1; i++) {
            this.positionList.getItems().add(i);
        }
        this.positionList.setValue(currentLength + 1);

    }


    // Muda os controles dependendo da estrutura de dados escolhida
    public void switchControls(){
        this.controlsHBox.getChildren().clear();
        switch (this.currentType) {
            case "Lista Simplesmente Encadeada":
            case "Lista Duplamente Encadeada":
                // Box vertical
                VBox containerPosition = new VBox();

                Label labelPosition = new Label("Posição");
                containerPosition.getChildren().add(labelPosition);

                if (this.currentType.equals("Lista Simplesmente Encadeada"))
                    this.updatePositionsInput(simplyLinkedList.getLength());
                else
                    this.updatePositionsInput(doublyLinkedList.getLength());

                // Ajustando a posição do input
                containerPosition.getChildren().add(this.positionList);
                containerPosition.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(containerPosition, new Insets(-7, 10, 0, 0));

                this.controlsHBox.getChildren().add(containerPosition);

                Button btnAdd = new Button("Adicionar");
                btnAdd.setOnAction(this::onBtnAddClick);
                HBox.setMargin(btnAdd, new Insets(5, 0, -7, 7));

                this.controlsHBox.getChildren().add(btnAdd);
                this.controlsHBox.setAlignment(Pos.CENTER_LEFT);

                break;
            case "Pilha":

                Button btnPile = new Button("Empilhar");
                //TODO criar e setar o método de click
                this.controlsHBox.getChildren().add(btnPile);
                btnPile.setAlignment(Pos.CENTER_LEFT);

                HBox.setMargin(btnPile, new Insets(0, 10, -7, 0));

                Button btnPop = new Button("Desempilhar");
                //TODO criar e setar o método de click
                btnPop.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(btnPop, new Insets(0, 10, -7, 0));
                this.controlsHBox.getChildren().add(btnPop);

                this.controlsHBox.setAlignment(Pos.CENTER_LEFT);


                break;
            case "Fila":
                //TODO contruir os controles para a fila
                break;
            case "Árvore De Pesquisa":
                //TODO contruir os controles para a árvore
                break;
        }
//        currentType.equals("Lista Simplesmente Encadeada")
    }
}