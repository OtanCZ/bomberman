package com.bomberman.game.view;

import javafx.scene.Group;
import javafx.scene.Node;

import java.util.List;
public interface SceneManager {

    void showOnlySceneCollection(List<Node> nodeList);

    void showOnlySceneCollection(Node... nodeArray);

    void replace(Node oldNode, Node newNode);

    Group getParentScene();

    void addBefore(Node beforeNode, Node... nodeArray);

    void remove(Node node);

    void update();
}