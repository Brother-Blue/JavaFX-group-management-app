package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import member_manager.Member;
import member_manager.Milestone;

import java.util.ArrayList;

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

        milestoneMemberRegister.getText();


      //  Milestone milestone = new Milestone(milestoneNameRegister.getText(), milestoneDescripRegister.getText(), startDateRegister.getValue(), endDateRegister.getValue(), "0000-00-00", );

    }


}
