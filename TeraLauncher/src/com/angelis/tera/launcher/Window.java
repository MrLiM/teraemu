package com.angelis.tera.launcher;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import com.angelis.tera.launcher.actions.LaunchGame;
import com.angelis.tera.launcher.actions.Login;
import com.angelis.tera.launcher.model.Account;

public class Window extends Application {
    
    private Account account;
    
    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Launcher Tera");
        
        Group root = new Group();
        Scene scene = new Scene(root, 300, 250, Color.LIGHTBLUE);
        
        final TextField login = new TextField();
        login.setText("angelis");
        login.setLayoutX(0);
        login.setLayoutY(0);
        root.getChildren().add(login);
        
        final TextField password = new TextField();
        password.setText("apqxpyw86");
        password.setLayoutX(0);
        password.setLayoutY(30);
        root.getChildren().add(password);
        
        final Button btn = new Button();
        btn.setLayoutX(0);
        btn.setLayoutY(60);
        btn.setText("Login");
        root.getChildren().add(btn);
        
        btn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if (Window.this.account != null) {
                    new LaunchGame(Window.this.account).doAction();
                }
                else {
                    Window.this.account = new Login(login.getText(), password.getText()).doAction();
                    if (Window.this.account != null) {
                        btn.setText("Play");
                    }
                }
            }
        });

        stage.setScene(scene);
        stage.show();
    }
    
}
