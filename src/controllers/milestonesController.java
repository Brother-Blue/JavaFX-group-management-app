package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class milestonesController extends MainController {

    @FXML
    private TextField milestoneNameRegister;
    @FXML
    private TextField milestoneDescripRegister;
    @FXML
    private TextField milestoneMemberRegister;
    @FXML
    private DatePicker startDateRegister;
    @FXML
    private DatePicker endDateRegister;


    @FXML
    public void registerMilestone(ActionEvent event) {
        //TODO: Fix this shiz
        /*
        milestoneMemberRegister.getText();
        ArrayList<Member> contribution = new ArrayList<>();
        Milestone milestone = new Milestone(milestoneNameRegister.getText(), startDateRegister.getValue(), endDateRegister.getValue(), milestoneDescripRegister.getText(), contribution);
        */
    }

}
