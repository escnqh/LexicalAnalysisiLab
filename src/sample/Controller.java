package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import sample.utils.ScanUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{
    @FXML
    private Button button_start;
    @FXML
    private Button button_clear;
    @FXML
    private TextArea inputArea;
    @FXML
    private TextArea outputArea;

    private String outputString=null;
    private String inputString=null;

    public void start(){
        inputString=inputArea.getText();
        if (inputString!=null){
            System.out.print("===================input not null");
            ScanUtil scanUtil=new ScanUtil();
            outputString=scanUtil.scanner(inputString);
            outputArea.setText(outputString);
        }else {
            System.out.print("===================input is null");
        }
    }

    public void clear(){
        inputString=null;
        outputString=null;
        inputArea.setText("");
        outputArea.setText("      重新输入吧！");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
