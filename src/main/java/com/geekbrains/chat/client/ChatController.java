package com.geekbrains.chat.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ChatController implements Initializable {
    private Path clientDir;
    public TextField input;
    public ListView<String> listView;
    private IoNet net;

    public void sendMsg(ActionEvent actionEvent) throws IOException {
        net.sendMsg(input.getText());
        input.clear();
        String item = listView.getSelectionModel().getSelectedItem();
    }
    private void addMessage(String msg){
        Platform.runLater(() -> listView.getItems().add(msg));

    }
    private void fillFileView() throws Exception{
        List<String> files = Files.list(clientDir)
                    .map(p -> p.getFileName().toString())
                    .collect(Collectors.toList());
        listView.getItems().addAll(files);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            clientDir = Paths.get("client");
            fillFileView();
            Socket socket = new Socket("localhost", 8189);
            net = new IoNet(this::addMessage, socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
