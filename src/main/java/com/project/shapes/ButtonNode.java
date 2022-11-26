package com.project.shapes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ButtonNode extends Button {
    private int index;
    private String structureType;
    private int width = 30;

    public ButtonNode(String s, int index, String structureType) {
        super(s);
        this.index = index;
        this.structureType = structureType;

        // Adapatando o tamanho do no para a quantidade de digitos
        for (int i = 0; i < s.length(); i++) {
            this.width+=15;
        }

        String stringStyle = "-fx-pref-height: 50px; -fx-text-fill: white; -fx-font-size: 15pt; -fx-font-weight: bold; -fx-cursor: hand;";

        stringStyle+= "-fx-pref-width: " +Integer.toString(this.width) + ";";

        // Destaque na cor para o primeiro e ultimo item
        if (index == 1) {
            stringStyle += "-fx-background-color: #00ff00;";
        }
//        else if (index==){
//            stringStyle += "-fx-background-color: #9a98a6;";
//        }
        else {
            stringStyle += "-fx-background-color: #9a98a6;";
        }

        if (this.structureType.equals("list")) {
            stringStyle += "-fx-background-radius: 10px;";
        }

        this.setStyle(stringStyle);

    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
