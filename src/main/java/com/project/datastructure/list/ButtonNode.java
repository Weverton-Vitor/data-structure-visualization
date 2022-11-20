package com.project.datastructure.list;

import javafx.scene.control.Button;

public class ButtonNode extends Button {
    private int index;

    public ButtonNode(String s, int index) {
        super(s);
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
