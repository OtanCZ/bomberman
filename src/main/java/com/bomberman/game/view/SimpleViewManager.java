package com.bomberman.game.view;
import javafx.scene.Group;
import javafx.scene.Node;
import java.util.List;
public class SimpleViewManager extends Group implements SceneManager {

    public SimpleViewManager() {

    }

    @Override
    public void showOnlySceneCollection(List<Node> nodeList) {
        super.getChildren().clear();
        super.getChildren().addAll(nodeList);
    }

    @Override
    public void showOnlySceneCollection(Node... nodeArray) {
        super.getChildren().clear();
        super.getChildren().addAll(nodeArray);
    }

    @Override
    public void replace(Node oldNode, Node newNode) {
        int indexOfOldOne = super.getChildren().indexOf(oldNode);
        if (indexOfOldOne == -1) {
            return;
        }
        super.getChildren().remove(oldNode);
        super.getChildren().add(indexOfOldOne, newNode);
    }

    @Override
    public Group getParentScene() {
        return this;
    }

    @Override
    public void addBefore(Node beforeNode, Node... nodeArray) {
        int indexOfOldOne = super.getChildren().indexOf(beforeNode);
        if (indexOfOldOne == -1) {
            return;
        }
        for (Node node : nodeArray) {
            super.getChildren().add(indexOfOldOne, node);
        }
    }

    @Override
    public void remove(Node node) {
        super.getChildren().remove(node);
    }

    @Override
    public void update() {
        super.updateBounds();
    }
}