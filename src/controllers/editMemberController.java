package controllers;


import id_generator.GeneratorMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import member_manager.Member;

import java.io.IOException;

public class editMemberController extends MainController {
    @FXML
    private ComboBox memberIdDropdown;
    @FXML
    private TextField searchForID;
    @FXML
    private Button saveButton;
    @FXML
    private TextField updateName;
    @FXML
    private TextField updateLastName;
    @FXML
    private TextField updateSalary;
    @FXML
    private TextField updatedFirstName;
    @FXML
    private TextField updatedLastName;
    @FXML
    private TextField updatedSalary;
    @FXML
    private TextField showID;



    //Functionalities in place but the code looks like shit, also needs some dummyproofing
    public void saveInfo(ActionEvent actionEvent) {
        int searchedID = 0;
        Window owner = saveButton.getScene().getWindow();

        if (searchForID.getText().isEmpty()){
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Info", "Search field must be filled in!");
            System.out.println("Search failed (Empty search).");
        } else if (GeneratorMain.isParsable(searchForID.getText()))
            searchedID = Integer.parseInt(searchForID.getText());

        for (Member member : planner.members) {
            if (searchedID == member.getId()){

                if(updateName.getText().isEmpty()){
                } else {
                    member.setFirstName(updateName.getText());
                }
                if(updateLastName.getText().isEmpty()){
                } else {
                    member.setLastName(updateLastName.getText());
                }
                if(updateSalary.getText().isEmpty()) {
                }else {
                    double updatedSalary = Double.parseDouble(updateSalary.getText());
                    member.setSalary(updatedSalary);
                }
                showID.setText(Integer.toString(member.getId()));
                updatedFirstName.setText(member.getFirstName());
                updatedLastName.setText(member.getLastName());
                updatedSalary.setText(String.valueOf(member.getSalary()));






            } else {
                System.out.println("Nay");
            }

        }
    }

    public void initialize() {
        ObservableList<String> memberDropdownItems = FXCollections.observableArrayList();
        for (Member member : planner.members) {
            memberDropdownItems.add(Integer.toString(member.getId()) + " : " + member.getFirstName() + " " + member.getLastName());
        }
        memberIdDropdown.setItems(memberDropdownItems);
    }

    public void setText(ActionEvent actionEvent) {
        searchForID.setText(memberIdDropdown.getValue().toString().substring(0, 5));
    }


    @Override
    public void back(ActionEvent event) throws IOException {

        Parent backMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/userInfo.fxml"));
        Scene backMemberScene = new Scene(backMemberParent);

        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();

        window.setScene(backMemberScene);
        window.show();
    }

}
