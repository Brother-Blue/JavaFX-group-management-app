package controllers;

import id_generator.GeneratorMain;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Window;
import json_reader_writer.JsonReader;
import json_reader_writer.JsonWriter;
import member_manager.Member;
import member_manager.Planner;

public class newMemberController extends MainController {

    @FXML
    private TextField firstNameRegister;
    @FXML
    private TextField lastNameRegister;
    @FXML
    private TextField dobRegister;
    @FXML
    private TextField salaryRegister;
    @FXML
    private Button registerButton;



    public void registerMember(ActionEvent event) throws NumberFormatException {
        Window owner = registerButton.getScene().getWindow(); //setup the popup
        boolean success = true;

        if (firstNameRegister.getText().isEmpty()) { //checks if first name field is empty
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "First name field can not be empty!");
            System.out.println("First name field failed.");
            success = false;

        } if (lastNameRegister.getText().isEmpty()) { //checks if last name field is empty
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Last name field can not be empty!");
            System.out.println("Last name field failed.");
            success = false;
        } if (GeneratorMain.isParsable(dobRegister.getText())) { //checks if birthday field valid but < 0 or > 991231
            int tempInt = Integer.parseInt(dobRegister.getText());
            if (tempInt <= 0) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Age can not be less than 0!");
                System.out.println("Birthday field failed (negative input).");
                success = false;
            } else if (tempInt > 20181212) { //The highest accepted value as of the day implemented.
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please follow the appropriate format YYMMDD!");
                System.out.println("Birthday field failed (input exceeds max accepted input).");
                success = false;
            }
        } if (!GeneratorMain.isParsable(dobRegister.getText())) { //checks if birthday field is empty or invalid
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Please follow the appropriate format YYMMDD!");
            System.out.println("Birthday field failed (invalid input).");
            success = false;
        } if (GeneratorMain.isParsable(salaryRegister.getText())) {
            double tempSalary = Double.parseDouble(salaryRegister.getText());
            if (tempSalary < 80) {
                AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Salary must be at least minimum wage! (Currently 80SEK)");
                System.out.println("Salary field failed (invalid salary amount).");
                success = false;
            }
        } if (!GeneratorMain.isParsable(salaryRegister.getText()) || salaryRegister.getText().isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "Salary must be a number greater than minimum wage (80SEK).");
            System.out.println("Salary field failed (invalid salary input).");
            success = false;
        } else {
            int genID = GeneratorMain.generateID(firstNameRegister.getText(), lastNameRegister.getText(), Integer.parseInt(dobRegister.getText()));
            System.out.println("ABOVE TEXT IS JUST CONSOLE DEBUGGING FOR ID GEN. IF NO TEXT FOLLOWS THEN NO MEMBER WAS CREATED.");
            for (Member member : MainController.planner.members) {
                if (genID == member.getId()) {
                    AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Error", "User with ID: " + genID + " already exists. Please proceed to the contact page if you believe this is an error.");
                    success = false;
                }
            }
        }

        if (success) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Success", "Member successfully registered! \n" + firstNameRegister.getText() + "'s generated ID is: " + GeneratorMain.generateID(firstNameRegister.getText(), lastNameRegister.getText(), Integer.parseInt(dobRegister.getText())));
            System.out.println("Member registration successful.");
            //Add the member to the Planner
            planner.addMember(firstNameRegister.getText(), lastNameRegister.getText(), Integer.parseInt(dobRegister.getText()), Double.parseDouble(salaryRegister.getText()));
            System.out.println(firstNameRegister.getText() + " was added to the planner.");
            System.out.println(planner.members);
            JsonWriter writer = new JsonWriter();
            writer.savePlanner(planner);
        }
    }
}
