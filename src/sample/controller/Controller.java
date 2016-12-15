package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Main;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {


    @FXML
    Button open;
    @FXML
    Button save;
    @FXML
    Button exit;
    @FXML
    TextArea textarea;

    public void initialize() throws IOException {
        open.setOnAction(Event -> {
            String currentDir = System.getProperty("user.dir") + File.separator;
            StringBuilder sb = null;
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fc.getExtensionFilters().add(extFilter);
            File selectedFile = fc.showOpenDialog(null);
            sb = readFile(selectedFile);
            textarea.setText(sb.toString());
        });
        save.setOnAction((ActionEvent event) -> {
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                SaveFile(textarea.getText(), file);
            }
        });
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Stage stage = (Stage) exit.getScene().getWindow();
            stage.close();
        });
    }
    private StringBuilder readFile(File selectedFile){
        StringBuilder sb = new StringBuilder();
        String curLine = " " ;
        try{
            FileReader fr = new FileReader(selectedFile);
            BufferedReader br = new BufferedReader(fr);

            while(curLine !=  null){
                curLine = br.readLine();
                sb.append(curLine).append("\n");
            }
        } catch (Exception e){
            e.getMessage();
        }
        return sb;
    }

    private void SaveFile(String text, File file) {
        try {
            FileWriter fileWriter;
            fileWriter = new FileWriter(file);
            fileWriter.write(text);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Main.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}