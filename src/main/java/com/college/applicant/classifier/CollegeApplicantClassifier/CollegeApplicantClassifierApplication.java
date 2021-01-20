package com.college.applicant.classifier.CollegeApplicantClassifier;

import com.college.applicant.classifier.CollegeApplicantClassifier.data.ApplicantRepository;
import com.college.applicant.classifier.CollegeApplicantClassifier.data.impl.ApplicantRepositoryImpl;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Applicant;
import com.college.applicant.classifier.CollegeApplicantClassifier.model.Result;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.RulesCriteria;
import com.college.applicant.classifier.CollegeApplicantClassifier.rules.impl.RulesCriteriaImpl;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.ApplicantService;
import com.college.applicant.classifier.CollegeApplicantClassifier.service.impl.ApplicantServiceImpl;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.HashMap;
import java.util.Map;

public class CollegeApplicantClassifierApplication extends Application {

    RulesCriteria rulesCriteria = new RulesCriteriaImpl();
    ApplicantRepository applicantRepository = new ApplicantRepositoryImpl();
	ApplicantService applicantService = new ApplicantServiceImpl(rulesCriteria, applicantRepository);

	Map<String, String> applicantData = new HashMap<>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("College Applicant Classifier");

		// Create the registration form grid pane
		GridPane gridPane = createRegistrationFormPane();
		// Add UI controls to the registration form grid pane
		addUIControls(gridPane);
		// Create a scene with registration form grid pane as the root node
		Scene scene = new Scene(gridPane, 800, 500);
		// Set the scene in primary stage
		primaryStage.setScene(scene);

		primaryStage.show();
	}


	private GridPane createRegistrationFormPane() {
		// Instantiate a new Grid Pane
		GridPane gridPane = new GridPane();

		// Position the pane at the center of the screen, both vertically and horizontally
		gridPane.setAlignment(Pos.CENTER);

		// Set a padding of 20px on each side
		gridPane.setPadding(new Insets(40, 40, 40, 40));

		// Set the horizontal gap between columns
		gridPane.setHgap(10);

		// Set the vertical gap between rows
		gridPane.setVgap(10);

		// Add Column Constraints

		// columnOneConstraints will be applied to all the nodes placed in column one.
		ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
		columnOneConstraints.setHalignment(HPos.RIGHT);

		// columnTwoConstraints will be applied to all the nodes placed in column two.
		ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
		columnTwoConstrains.setHgrow(Priority.ALWAYS);

		gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

		return gridPane;
	}

	private void addUIControls(GridPane gridPane) {
		// Add Header
		Label headerLabel = new Label("Enter Applicant Information");
		headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
		gridPane.add(headerLabel, 0,0,2,1);
		GridPane.setHalignment(headerLabel, HPos.CENTER);
		GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

		// Add First Name Label
		Label firstNameLabel = new Label("First Name: ");
		gridPane.add(firstNameLabel, 0,1);

		// Add Last Name Label
		Label lastNameLabel = new Label("Last Name: ");
		gridPane.add(lastNameLabel, 0,2);

		// Add Age Label
		Label ageLabel = new Label("Age: ");
		gridPane.add(ageLabel, 0, 3);

		// Add State Label
		Label stateLabel = new Label("State: ");
		gridPane.add(stateLabel, 0, 4);

		// Add GPA Label
		Label gpaLabel = new Label("GPA: ");
		gridPane.add(gpaLabel, 0, 5);

		// Add GPA Scale Label
		Label gpaScaleLabel = new Label("GPA Scale: ");
		gridPane.add(gpaScaleLabel, 0, 6);

		// Add SAT Label
		Label satScoresLabel = new Label("SAT Score: ");
		gridPane.add(satScoresLabel, 0, 7);

		// Add ACT Label
		Label actScoresLabel = new Label("ACT Score: ");
		gridPane.add(actScoresLabel, 0, 8);

		// Add Felony Label
		Label feloniesLabel = new Label("# of Felonies: ");
		gridPane.add(feloniesLabel, 0, 9);

		// Add Most Recent Year of Felony Label
		Label mostRecentFelonyYearLabel = new Label("Felony Year: ");
		gridPane.add(mostRecentFelonyYearLabel, 0, 10);

		// Add First Name Text Field
		TextField firstNameField = new TextField();
		firstNameField.setPrefHeight(40);
		gridPane.add(firstNameField, 1, 1);

		// Add Last Name Text Field
		TextField lastNameField = new TextField();
		lastNameField.setPrefHeight(40);
		gridPane.add(lastNameField, 1, 2);

		// Add Age Text Field
		TextField ageField = new TextField();
		ageField.setPrefHeight(40);
		gridPane.add(ageField, 1, 3);

		// Add State Text Field
		TextField stateField = new TextField();
		stateField.setPrefHeight(40);
		gridPane.add(stateField, 1, 4);

		// Add GPA Text Field
		TextField gpaField = new TextField();
		gpaField.setPrefHeight(40);
		gridPane.add(gpaField, 1, 5);

		// Add GPA Scale Text Field
		TextField gpaScaleField = new TextField();
		gpaScaleField.setPrefHeight(40);
		gridPane.add(gpaScaleField, 1, 6);

		// Add SAT Text Field
		TextField satField = new TextField();
		satField.setPrefHeight(40);
		gridPane.add(satField, 1, 7);

		// Add ACT Text Field
		TextField actField = new TextField();
		actField.setPrefHeight(40);
		gridPane.add(actField, 1, 8);

		// Add Felony Text Field
		TextField felonyField = new TextField();
		felonyField.setPrefHeight(40);
		gridPane.add(felonyField, 1, 9);

		// Add Most Recent Felony Year Text Field
		TextField mostRecentFelonyYearField = new TextField();
		mostRecentFelonyYearField.setPrefHeight(40);
		gridPane.add(mostRecentFelonyYearField, 1, 10);

		// Add Submit Button
		Button submitButton = new Button("Submit");
		submitButton.setPrefHeight(40);
		submitButton.setDefaultButton(true);
		submitButton.setPrefWidth(100);
		gridPane.add(submitButton, 0, 11, 2, 1);
		GridPane.setHalignment(submitButton, HPos.CENTER);
		GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

		submitButton.setOnAction(event -> {
			applicantData.put("firstName", firstNameField.getText());
			applicantData.put("lastName", lastNameField.getText());
			applicantData.put("felonies", felonyField.getText());
			applicantData.put("actScores", actField.getText());
			applicantData.put("satScores", satField.getText());
			applicantData.put("gpaScale", gpaScaleField.getText());
			applicantData.put("gpa", gpaField.getText());
			applicantData.put("state", stateField.getText());
			applicantData.put("age", ageField.getText());
			applicantData.put("recentYearOfFelony", mostRecentFelonyYearField.getText());

			Applicant applicant = applicantService.convertToApplicantObj(applicantData);
			applicantService.saveToDatabase(applicant);
			Result applicantResult = applicantService.getApplicantClassifier(applicant.getId());

			showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Applicant Result", "Application Status: " + applicantResult.toString() + " Reason: " + applicantResult.getReason());
		});
	}

	private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
		Alert alert = new Alert(alertType);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.initOwner(owner);
		alert.show();
	}
}
