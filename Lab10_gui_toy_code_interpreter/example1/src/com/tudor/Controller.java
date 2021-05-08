package com.tudor;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class Controller {

    @FXML
    private ListView<Person> personList;

    @FXML
    private Label label1;

    @FXML
    public void initialize(){
        personList.setItems(getPersonList());
    }



    private ObservableList<Person> getPersonList(){
        Person p1 = new Person(23,"aa");
        Person p2 = new Person(24,"ab");
        Person p3 = new Person(25,"bb");

        ObservableList list = FXCollections.observableArrayList(p1,p2,p3);
        return list;
    }
}
