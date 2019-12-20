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

    //Checks for valid ID and updates member information.
    public void saveInfo(ActionEvent actionEvent) {
        int searchedID = 0;
        boolean IDValid = true;
        Window owner = saveButton.getScene().getWindow();

        if (searchForID.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.INFORMATION, owner, "Info", "Search field must be filled in!");
            System.out.println("Search failed (Empty search).");
            IDValid = false;
        } else if (!GeneratorMain.isParsable(searchForID.getText())) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "ID's must be 5-digit integers!");
            System.out.println("Search failed (Invalid ID format, non-number characters).");
            IDValid = false;
        } else if (Integer.parseInt(searchForID.getText()) > 99999) {
                AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error", "ID's are positive 5-digit integers!");
                System.out.println("Search failed (Invalid ID format, more than 5 digits");
                IDValid = false;
        } else if (Integer.parseInt(searchForID.getText()) < 10000) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Error", "ID's are positive 5-digit integers!");
            System.out.println("Search failed (Invalid ID format, less than 5 digits OR negative input).");
            IDValid = false;

        } else if (GeneratorMain.isParsable(searchForID.getText())) {
                searchedID = Integer.parseInt(searchForID.getText());

                for (Member member : planner.members) {
                    if (searchedID == member.getId()) {
                        if (IDValid) {
                            if (updateName.getText().isEmpty()) {
                                //keeps same firstname if no new name is entered
                            } else {
                                member.setFirstName(updateName.getText());
                            }
                            if (updateLastName.getText().isEmpty()) {
                                //keeps same lastname if no new lastname is entered
                            } else {
                                member.setLastName(updateLastName.getText());
                            }
                            if (updateSalary.getText().isEmpty()) {
                                //keeps same salary if no new salary is entered
                            } else {
                                double updatedSalary = Double.parseDouble(updateSalary.getText());
                                member.setSalary(updatedSalary);
                            }
                            //Sets updated information to "Updated member information" textfields and clears input-textfields.
                            showID.setText(Integer.toString(member.getId()));
                            updatedFirstName.setText(member.getFirstName());
                            updatedLastName.setText(member.getLastName());
                            updatedSalary.setText(String.valueOf(member.getSalary()));
                            updateName.clear();
                            updateLastName.clear();
                            updateSalary.clear();

                        }
                    }
                }
            }
        }

        public void initialize (){
            ObservableList<String> memberDropdownItems = FXCollections.observableArrayList();
            for (Member member : planner.members) {
                memberDropdownItems.add(Integer.toString(member.getId()) + " : " + member.getFirstName() + " " + member.getLastName());
            }
            memberIdDropdown.setItems(memberDropdownItems);
        }

        public void setText (ActionEvent actionEvent){
            searchForID.setText(memberIdDropdown.getValue().toString().substring(0, 5));
        }


        @Override
        public void back (ActionEvent event) throws IOException {

            Parent backMemberParent = FXMLLoader.load(getClass().getResource("../fxml-files/userInfo.fxml"));
            Scene backMemberScene = new Scene(backMemberParent);

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

            window.setScene(backMemberScene);
            window.show();
        }

    }
