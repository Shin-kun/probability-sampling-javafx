/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package samplingmethods;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 *
 * @author Juan Carlos
 */
public class FXMLDocumentController implements Initializable {
//    auxiliary components and strings    
    Stage mainMenu;
    Parent root;    
    boolean proceed1;
    boolean proceed2;
    boolean proceed3;
    
//    main menu buttons
    @FXML
    private Button rndbtn1;
    @FXML
    private Button sysbtn2;
    @FXML
    private Button sttbtn3;
    @FXML
    private Button quit;
        
//    First information buttons
    @FXML
    private Button backFromFirstInfo;
    @FXML
    private Button proceedInputFrame;    
    @FXML
    private TextField populationSize;
    @FXML
    private RadioButton charBox;
    @FXML
    private RadioButton numBox;
    @FXML
    private Label errorMessage1;
//    Second samplingframe buttons
    @FXML
    private Button proceedSampling;
    @FXML
    private Button backFromInputFrame;
    @FXML
    private Button enterInputButton;
    @FXML
    private TextField inputPopulationFrame;
    @FXML
    private Label inputsRemaining;
    @FXML
    private TextArea samplingFrameArea;
    @FXML
    private Label errorMessage2;
//    final Frame buttons
    @FXML
    private Button returnToHome;
    @FXML
    private Button generateSample;
    @FXML
    private TextField sampleSizeBox;
    @FXML
    private TextArea sample;
    @FXML
    private Label errorMessage3;
    
    @FXML
    private void handleEntryButtonAction(ActionEvent event) throws IOException {               
        if(event.getSource() == rndbtn1){
            GlobalContext.methodChoice = "Random Sampling";
        } else if(event.getSource() == sysbtn2) {
            GlobalContext.methodChoice = "Systematic Sampling";
        } else {
            GlobalContext.methodChoice = "Stratified Sampling";
        }
        //get reference to the button's stage
        mainMenu = (Stage) rndbtn1.getScene().getWindow();
        System.out.println(GlobalContext.methodChoice);
        //load up other FXML Document
        root = FXMLLoader.load(getClass().getResource("FirstStageInformation.fxml"));        
        
        Scene scene = new Scene(root);
        mainMenu.setScene(scene);
        mainMenu.show();
        proceed1 = false;
    }
    
    @FXML
    private void returnMainMenu(ActionEvent e) throws IOException {
        mainMenu = (Stage) backFromFirstInfo.getScene().getWindow();
        
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        mainMenu.setScene(scene);
        mainMenu.show();        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        
    }    
    
    @FXML
    private void proceedInputFrame(ActionEvent e) throws IOException {        
        if(isNumeric(populationSize.getText())){
            GlobalContext.N = Integer.parseInt(populationSize.getText());
            
            if(proceed1 == false || GlobalContext.N < 25) {
                errorMessage1.setText("Check an option or lacking min requirements (N>=25)");
                errorMessage1.setVisible(true);
            } else {
                errorMessage1.setVisible(false);
                System.out.println("Changed" + GlobalContext.N);
                mainMenu = (Stage) proceedInputFrame.getScene().getWindow();

                root = FXMLLoader.load(getClass().getResource("InputPopulation.fxml"));

                Scene scene = new Scene(root);
                mainMenu.setScene(scene);
                mainMenu.show();                                            
                
                GlobalContext.counter = 0;
                GlobalContext.arr = new String[GlobalContext.N];
            }
        } else{
            errorMessage1.setText("Something wrong with input");
            errorMessage1.setVisible(true);
        }        
    }
        
    @FXML
    private void returnFromInputFrame(ActionEvent e) throws IOException {
        mainMenu = (Stage) backFromInputFrame.getScene().getWindow();
        //load up other FXML Document
        root = FXMLLoader.load(getClass().getResource("FirstStageInformation.fxml"));
        
        Scene scene = new Scene(root);
        mainMenu.setScene(scene);
        mainMenu.show();        
    }
    
    @FXML
    private void proceedSamplingFrame(ActionEvent e) throws IOException {                
        if(proceed2){
            mainMenu = (Stage) proceedSampling.getScene().getWindow();
        
            //load up other FXML Document
            root = FXMLLoader.load(getClass().getResource("FinalFrame.fxml"));

            Scene scene = new Scene(root);
            mainMenu.setScene(scene);
            mainMenu.show();
        } else{
            errorMessage2.setText("Lacking inputs");
            errorMessage2.setVisible(true);
        }
    }
    
    @FXML
    private void returnHome(ActionEvent e) throws IOException{
        mainMenu = (Stage) returnToHome.getScene().getWindow();
        
        //load up other FXML Document
        root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        mainMenu.setScene(scene);
        mainMenu.show();
    }
    
    @FXML
    private void quit(ActionEvent e) {
        System.exit(0);
    }    
    
    @FXML
    public void numSelected(ActionEvent e) {
        proceed1 = true;
        GlobalContext.option = 1;
        System.out.println("Changed " + GlobalContext.option);
    }
    
    @FXML
    public void charSelected(ActionEvent e) {
        proceed1 = true;
        GlobalContext.option = 2;
        System.out.println("Changed " + GlobalContext.option);
    }
    
    public static boolean isNumeric(String str) {
        if(str.length() < 1)
            return false;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        return true;
        // return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
    
    public static boolean isFloat(String str) {
        boolean isValid = true;

        try {
            Float.parseFloat(str);
        } catch (NumberFormatException e) {
            isValid = false;
        }

        return isValid;
    }
    
    @FXML
    private void enterInput(ActionEvent e){        
        System.out.println("Went in " + GlobalContext.methodChoice);
        String input = inputPopulationFrame.getText();
        errorMessage2.setVisible(false);
        
        if(input.equals("")){
            errorMessage2.setText("Input never empty");
            errorMessage2.setVisible(true);
        }
        else if(GlobalContext.counter == GlobalContext.N) {
            proceed2 = true;            
        }
        else {
            if ((input.length() > 1 || ( !(input.charAt(0) >= 65 && input.charAt(0) <= 90 ) && !(input.charAt(0) >= 97 && input.charAt(0) <= 122) ))  && GlobalContext.option == 2) {
                errorMessage2.setText("Invalid character input");
                errorMessage2.setVisible(true);
                System.out.println("option = " + GlobalContext.option);
            } 
            else if ((!isNumeric(input) || input.equals("")) && GlobalContext.option == 1) {
                errorMessage2.setText("Invalid numerical input");
                errorMessage2.setVisible(true);
            }
            else {                
                System.out.println("Enterred input");
                GlobalContext.arr[GlobalContext.counter] = new String();
                GlobalContext.arr[GlobalContext.counter++] = input;
                inputsRemaining.setText((GlobalContext.N-GlobalContext.counter)+" inputs remaining");                
                if(GlobalContext.methodChoice.equals("Random Sampling") || GlobalContext.methodChoice.equals("Systematic Sampling")){
//                    setScreen += input + "          ";
                    samplingFrameArea.setText(samplingFrameArea.getText()+"Index [" + GlobalContext.counter+ "] =" +input+"\n");
                } 
                    
                inputPopulationFrame.setText("");
                
                if(GlobalContext.counter == GlobalContext.N){
                    enterInputButton.setVisible(false);
                    if(GlobalContext.methodChoice.equals("Stratified Sampling")){
                        String[] arrCpy = GlobalContext.arr.clone();
                        Arrays.sort(arrCpy);
                        
                        String current = arrCpy[0];
                        String sFrame = "";
                        int counter = 1;
                        boolean toggle = true;
                        for (int i = 0; i < GlobalContext.N;) {
                            if(toggle) {
                                sFrame += String.format("Stratum %d { \n", counter);
                                for (int j = 0; j < GlobalContext.N; j++) {
                                    if (GlobalContext.arr[j].equals(arrCpy[i])) {
                                        sFrame += String.format("Index %d = [%s]\n", j, arrCpy[i]);
                                    }
                                }
                                sFrame += "";
                                toggle = false;
                                counter++;
                            }
                            else{
                                if(current.equals(arrCpy[i]))
                                    i++;
                                else{
                                    current = arrCpy[i];
                                    toggle = true;
                                    sFrame += "  }\n";
                                }
                            }                                                        
                        }                        
                        samplingFrameArea.setText(sFrame);
                    }
                    proceed2 = true;                    
                }
                    
            }
        }                
        proceed3 = false;
    }
    
    @FXML
    public void enterListener(KeyEvent e){
        if(e.getCode() == KeyCode.ENTER){
            enterInputButton.fire();            
        }
    }
    
    @FXML
    public void enterSample(){
        String inp = sampleSizeBox.getText();
        boolean pass = false;
        if(GlobalContext.methodChoice == "Random Sampling"){
            System.out.println("New Random Sample");                        
            if(isNumeric(inp)){
                int n = Integer.parseInt(inp);
                if(n <= 0){
                    errorMessage3.setText("No zero or negative numbers");
                    errorMessage3.setVisible(true);
                } else if(n >= GlobalContext.N) {
                    errorMessage3.setText("Unsatisfied: n < N");
                    errorMessage3.setVisible(true);
                } else {
                    pass = true;
                }
            }
            else if(inp.length()<1){
                pass = true;
            }
            else {
                errorMessage3.setText("Invalid Sample Size");
                errorMessage3.setVisible(true);
            }
            if(pass){
                errorMessage3.setVisible(false);
                sample.setText(GlobalContext.doRandomSampling(inp));                        
                proceed3 = true;
            }
        }
        else if(GlobalContext.methodChoice == "Stratified Sampling"){
            System.out.println("New Stratified Sample");                        
            if(isFloat(inp)){
                float percentage = Float.parseFloat(inp);
                if (percentage >= 100) {
                    errorMessage3.setText("Cannot have a value greater than or equal to 100%");
                    errorMessage3.setVisible(true);
                } else if (percentage <= 0) {
                    errorMessage3.setText("Cannot have a value lesser than or equal to 0");
                    errorMessage3.setVisible(true);
                } else {
                    pass = true;
                } 
            } else if(inp.length() < 1) {
                pass = true;
            }
            else {
                errorMessage3.setText("Invalid Sample Size");
                errorMessage3.setVisible(true);
            }            
            if(pass){
                errorMessage3.setVisible(false);
                sample.setText(GlobalContext.doStratifiedSampling(inp));
                proceed3 = true;
            }
        }
        else {
            System.out.println("New Systematic Sample");            
            if(isNumeric(inp)){
                int n = Integer.parseInt(inp);
                if(n <= 0){
                    errorMessage3.setText("No zero or negative sample sizes allowed");
                    errorMessage3.setVisible(true);                        
                } else if(n >= GlobalContext.N){
                    errorMessage3.setText("Sample size cannot be greater than population size");
                    errorMessage3.setVisible(true);
                } else if(n == 1){
                    errorMessage3.setText("Sample size cannot be equal to 1");
                    errorMessage3.setVisible(true);
                } else { 
                    pass = true;
                }
            }
            else if(inp.length() < 1) {
                pass = true;
            }             
            else {
                errorMessage3.setText("Invalid Sample Size");
                errorMessage3.setVisible(true);
            }
            
            if(pass){
                errorMessage3.setVisible(false);
                sample.setText(GlobalContext.doSystematicSampling(inp));
                proceed3 = true;
            }
        }
        if(proceed3){
            sample.setEditable(false);
            returnToHome.setVisible(true);
            generateSample.setVisible(false);
            GlobalContext.initialize();
        }
    }
}