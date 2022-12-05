package com.project.datastructurevisualization;
import com.project.datastructure.queue.FilaEnc;
import com.project.datastructure.stack.PilhaEnc;
import com.project.datastructure.tree.Node;
import com.project.datastructure.tree.Tree;
import com.project.shapes.ButtonNode;
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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

import java.net.URL;
import java.util.ResourceBundle;

import static com.project.datastructure.tree.Type.*;


public class MainController implements Initializable {
    private SimplyLinkedList simplyLinkedList = new SimplyLinkedList();
    private DoublyLinkedList doublyLinkedList = new DoublyLinkedList();

    private PilhaEnc stack = new PilhaEnc();

    private FilaEnc queue = new FilaEnc();


    private Tree tree = new Tree();


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
    private ScrollPane scrollContainer = new ScrollPane();

    @FXML
    private Group shapesGroup = new Group();

    @FXML
    private Pane containerTree = new Pane();


    @FXML
    private HBox controlsHBox = new HBox();
    private VBox informations = new VBox();


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

    // Renderiza a tela de acordo com a estrutura selecionada
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
        } else if (currentType.equals("Pilha")) {
            renderStack();
        } else if (currentType.equals("Fila")) {
            renderQueue();
            System.out.println("fila");
        }

        // Redefinindo os controles para se adequear a estruturas de dados selecionada
        this.switchControls();


    }

    // Call-Back para o evento de click no botão de remover um novo elemento na lista
    public void onBtnDeleteClick(javafx.scene.input.MouseEvent mouseEvent) {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2) { // Double click
                try {
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
                } catch (Exception e){}
            }
        }
    }

    // Call-Back para o evento de click no botão de adicionar um novo elemento na lista
    public void onBtnAddClick(ActionEvent event) {
        if (currentType.equals("Lista Simplesmente Encadeada")) {
            if (this.simplyLinkedList.getLength() == 0) {
                this.simplyLinkedList.add(Integer.parseInt(this.inputValue.getText()));
                updatePositionsInput(simplyLinkedList.getLength());

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1, "list");
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

                ButtonNode newRectangle = new ButtonNode(this.inputValue.getText(), 1, "list");
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
        } else if (currentType.equals("Pilha")) {
                int value = Integer.parseInt(this.inputValue.getText());
                this.stack.push(value);
                shapesGroup.getChildren().clear();
                renderStack();
        } else if (currentType.equals("Fila")) {
            int value = Integer.parseInt(this.inputValue.getText());
            this.queue.insere(value);
            shapesGroup.getChildren().clear();
            renderQueue();

        }else if (currentType.equals("Árvore De Pesquisa")) {
            int value = Integer.parseInt(this.inputValue.getText());
            this.tree.add(value);
            shapesGroup.getChildren().clear();
            tree.print(INORDER);
            renderTree();
//        System.out.println(this.simplyLinkedList.getLength());
        }
    }

    public void onBtnPopclick(ActionEvent event) {
        this.stack.pop();
        renderStack();
    }

    public void onBtnUnqueue(ActionEvent event){
        this.queue.remove();
        renderQueue();
    }

    public void renderSimpleLinkedList() {

        // Ajustando o padding do scroll pane
        this.scrollContainer.setPadding(new Insets(200, 0, 0, 0));
        for (int i = 1; i <= this.simplyLinkedList.getLength(); i++) {
            Integer value = this.simplyLinkedList.searchByPosition(i);

            if (i == 1) {
                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), 1, "list");
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                ButtonNode lastRectangle = (ButtonNode) shapesGroup.getChildren().get(shapesGroup.getChildren().size() - 1);

                Arrow arrow = new Arrow();
                arrow.setStartY(25);
                arrow.setEndY(25);

                int start = 30;
                for (int j = 0; j < lastRectangle.getText().length(); j++) {
                    start+=15;
                }


                arrow.setStartX(lastRectangle.getTranslateX() + start);
                arrow.setEndX(lastRectangle.getTranslateX() + 55 + start);

                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i, "list");
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                newRectangle.setTranslateX(lastRectangle.getTranslateX() + 55 + start);

                shapesGroup.getChildren().add(arrow);
                shapesGroup.getChildren().add(newRectangle);
                lastRectangle = newRectangle;
            }

        }

    }

    public void renderDoublyLinkedList() {
        // Ajustando o padding do scroll pane
        this.scrollContainer.setPadding(new Insets(200, 0, 0, 0));
        for (int i = 1; i <= this.doublyLinkedList.getLength(); i++) {
            Integer value = this.doublyLinkedList.searchByPosition(i);

            if (i == 1) {
                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), 1, "list");
                newRectangle.setOnMouseClicked(this::onBtnDeleteClick);
                shapesGroup.getChildren().add(newRectangle);
            } else {
                ButtonNode lastRectangle = (ButtonNode) shapesGroup.getChildren().get(shapesGroup.getChildren().size() - 1);

                Arrow arrow = new Arrow();
                arrow.setStartY(10);
                arrow.setEndY(10);

                Arrow arrowBack = new Arrow();
                arrowBack.setStartY(40);
                arrowBack.setEndY(40);

                int start = 30;
                for (int j = 0; j < lastRectangle.getText().length(); j++) {
                    start+=15;
                }


                arrow.setStartX(lastRectangle.getTranslateX() + start);
                arrow.setEndX(lastRectangle.getTranslateX() + 40 + start);

                arrowBack.setEndX(lastRectangle.getTranslateX() + start);
                arrowBack.setStartX(lastRectangle.getTranslateX() + 40 + start);

                ButtonNode newRectangle = new ButtonNode(String.valueOf(value), i, "list");
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

        // Ajustando o padding do scroll pane
        this.scrollContainer.setPadding(new Insets(50, 0, 0, 0));
        VBox containerStack = new VBox();

        if (!this.stack.vazia()) {

            Arrow arrow = new Arrow();
            arrow.setStartY(60);
            arrow.setEndY(10);

            arrow.setTranslateX(615);


            containerStack.getChildren().add(arrow);
            int elements[] = this.stack.caminhar();

            for (int i = 1; i <= elements.length; i++) {
                ButtonNode newRectangle = new ButtonNode(Integer.toString(elements[i-1]), i, "queue");
                VBox.setMargin(newRectangle, new Insets(0, 0, 5, 600));

                containerStack.getChildren().add(newRectangle);
            }
        }
        shapesGroup.getChildren().clear();
        shapesGroup.getChildren().add(containerStack);
    }

    public void renderQueue(){
        // Ajustando o padding do scroll pane
        this.scrollContainer.setPadding(new Insets(200, 0, 0, 0));
        HBox containerQueue = new HBox();

        if (!this.queue.vazia()){

//        Arrow arrow = new Arrow();
//        arrow.setStartY(60);
//        arrow.setEndY(10);
//
//
//        arrow.setStartX(600);
//        arrow.setEndX(900);
//
//        arrow.setTranslateX(615);


//        containerStack.getChildren().add(arrow);
            int elements[] = this.queue.caminhar();

            for (int i = 1; i <= elements.length; i++) {
                ButtonNode newRectangle = new ButtonNode(Integer.toString(elements[i-1]), i, "stack");

                HBox.setMargin(newRectangle, new Insets(0, 0, 0, 5));

                containerQueue.getChildren().add(newRectangle);
            }
        }

        shapesGroup.getChildren().clear();
        shapesGroup.getChildren().add(containerQueue);
    }

    public void renderTree(){
        this.informations.getChildren().clear();
        Label inOrder = new Label("In ordem: " + this.tree.walk(INORDER));
        Label preOrder = new Label("Pre ordem: " + this.tree.walk(PREORDER));
        Label postOrder = new Label("Pos ordem: " + this.tree.walk(POSTORDER));
        postOrder.setMinWidth(150);
        this.informations.getChildren().add(inOrder);
        this.informations.getChildren().add(preOrder);
        this.informations.getChildren().add(postOrder);
//      Group  shapesGroup = new Group();
        containerTree.getChildren().clear();
        renderNodeTree(tree.getRoot(), "root", null, 1);

        System.out.println(shapesGroup.getChildren());
        shapesGroup.getChildren().add(containerTree);
//      this.scrollContainer.
    }

    public void renderNodeTree(Node node, String nodeType, ButtonNode parent, int nivel){
        int height = tree.getHeigth(tree.getRoot());
        int acrescimoInicial = 300;
        double taxaDeVariacaoPorNivel = 0.5;
        int potencia = nivel - 1;
        double taxaDeVariacaoDaBase = 1.5;
        int potenciaBase = (int) Math.floor(height / 2);
        System.out.println("Base: " + Math.pow(2, potenciaBase));
        double acrescimoBase = acrescimoInicial * Math.pow(1.8, potenciaBase);

        double totalAcrescimo =
          (acrescimoBase) * Math.pow(taxaDeVariacaoPorNivel, potencia);

        if (node != null) {
            ButtonNode newRectangle = new ButtonNode(Integer.toString(node.getValue()), 0, "tree");

            if (nodeType.equals("left")){
                newRectangle.setMarginTop(parent.getMarginTop() + 110);
                newRectangle.setMarginLeft((int) Math.round(parent.getMarginLeft() - totalAcrescimo));

                Line arrow = new Line();
                arrow.setStartY(parent.getMarginTop() + parent.getHeight() / 2);
                arrow.setEndY(newRectangle.getMarginTop());
                arrow.setStartX(parent.getMarginLeft());
                arrow.setEndX(newRectangle.getMarginLeft() + newRectangle.getWidth() / 2);
                containerTree.getChildren().add(arrow);
            } else if (nodeType.equals("right")){
                newRectangle.setMarginLeft((int) Math.round(parent.getMarginLeft() + totalAcrescimo));
                newRectangle.setMarginTop(parent.getMarginTop() + 110);

                Line arrow = new Line();
                arrow.setStartY(parent.getMarginTop() + parent.getHeight() / 2);
                arrow.setEndY(newRectangle.getMarginTop());
                arrow.setStartX(parent.getMarginLeft() + parent.getWidth());
                arrow.setEndX(newRectangle.getMarginLeft() + newRectangle.getWidth() / 2);
                containerTree.getChildren().add(arrow);
            } else {
                newRectangle.setMarginLeft(600);
                newRectangle.setMarginTop(0);
            }



            containerTree.getChildren().add(newRectangle);


            newRectangle.relocate(newRectangle.getMarginLeft(), newRectangle.getMarginTop());
            System.out.println();
            System.out.println("Margem top: "+newRectangle.getMarginTop());
            System.out.println("Margem left: "+newRectangle.getMarginLeft());
            System.out.println();

            this.renderNodeTree(node.getLeft(), "left", newRectangle, nivel+1);
            this.renderNodeTree(node.getRight(), "right", newRectangle, nivel+1);
        }
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
                if (this.currentType.equals("Lista Simplesmente Encadeada"))
                    renderSimpleLinkedList();
                else
                    renderDoublyLinkedList();
                break;
            case "Pilha":

                Button btnPile = new Button("Empilhar");
                btnPile.setOnAction(this::onBtnAddClick);
                this.controlsHBox.getChildren().add(btnPile);
                btnPile.setAlignment(Pos.CENTER_LEFT);

                HBox.setMargin(btnPile, new Insets(0, 10, -7, 0));

                Button btnPop = new Button("Desempilhar");
                btnPop.setOnAction(this::onBtnPopclick);
                btnPop.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(btnPop, new Insets(0, 10, -7, 0));
                this.controlsHBox.getChildren().add(btnPop);

                this.controlsHBox.setAlignment(Pos.CENTER_LEFT);
                renderStack();

                break;
            case "Fila":

                Button btnQueue = new Button("Enfileirar");
                btnQueue.setOnAction(this::onBtnAddClick);
                this.controlsHBox.getChildren().add(btnQueue);
                btnQueue.setAlignment(Pos.CENTER_LEFT);

                HBox.setMargin(btnQueue, new Insets(0, 10, -7, 0));

                Button btnUnqueue = new Button("Desenfileirar");
                btnUnqueue.setOnAction(this::onBtnUnqueue);
                btnUnqueue.setAlignment(Pos.CENTER_LEFT);
                HBox.setMargin(btnUnqueue, new Insets(0, 10, -7, 0));
                this.controlsHBox.getChildren().add(btnUnqueue);

                this.controlsHBox.setAlignment(Pos.CENTER_LEFT);
                renderQueue();
                break;
            case "Árvore De Pesquisa":
                Button btnTree = new Button("Adicionar");
                btnTree.setOnAction(this::onBtnAddClick);
                this.controlsHBox.getChildren().add(btnTree);
                btnTree.setAlignment(Pos.CENTER_LEFT);

                this.tree.add(50);
                this.tree.add(90);
                this.tree.add(10);
                VBox.setMargin(btnTree, new Insets(0, 10, -7, 0));

                this.informations = new VBox();
                Label inOrder = new Label("In ordem: " + this.tree.walk(INORDER));
                Label preOrder = new Label("Pre ordem: " + this.tree.walk(PREORDER));
                Label postOrder = new Label("Post ordem: " + this.tree.walk(POSTORDER));

                informations.getChildren().add(inOrder);
                informations.getChildren().add(preOrder);
                informations.getChildren().add(postOrder);

                this.controlsHBox.getChildren().add(informations);

                HBox.setMargin(informations, new Insets(0, 10, 0, 50));
                renderTree();
                break;
        }
//        currentType.equals("Lista Simplesmente Encadeada")
    }
}