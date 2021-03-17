package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    @FXML
    private TableView<Flight> table;

    @FXML
    private TableView<Mimimal> table2;

    @FXML
    private TableColumn<Flight, Integer> c1;

    @FXML
    private TableColumn<Flight, String> c2;

    @FXML
    private TableColumn<Flight, String> c3;

    @FXML
    private TableColumn<Flight, String> c4;

    @FXML
    private TableColumn<Flight, Integer> c5;

    @FXML
    private TableColumn<Mimimal, String> c6;

    @FXML
    private TableColumn<Mimimal, Integer> c7;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btndlt1;

    @FXML
    private Button btndlt2;

    @FXML
    private TextField tf1;

    @FXML
    private TextField tf2;

    @FXML
    private TextField tf3;

    @FXML
    private TextField tf4;

    @FXML
    private TextField tf5;

    @FXML
    private TextField tf6;

    @FXML
    private TextField tf7;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu menu1;

    @FXML
    private MenuItem save;

    @FXML
    private MenuItem open;

    @FXML
    private Button btnUpdate1;

    @FXML
    private Button btnUpdate2;

    @FXML
    private Label lb1;

    @FXML
    private Label lb2;

    public void hours(){
        Map<String, Integer> type_minimalAt = new HashMap<String,Integer>();
        Set<String> flightUniqTypes = new HashSet<String>();
        ObservableList<Flight> allFlight = table.getItems();
        for (Flight flight: allFlight) {
            flightUniqTypes.add(flight.getType());
        }

        for (String type:flightUniqTypes) {
            int minimalAt = Integer.MAX_VALUE;
            for (Flight flight:allFlight) {
                if( flight.getType().equals(type) && flight.getAt() < minimalAt)
                    minimalAt=flight.getAt();
            }
            type_minimalAt.put(type, minimalAt);
        }
        table2.getItems().clear();
        for (Map.Entry<String, Integer> t_mAt : type_minimalAt.entrySet()) {
            table2.getItems().add(new Mimimal(t_mAt.getKey(), t_mAt.getValue()));
        }
    };

    public void reys(){
        lb1.setText(String.valueOf(table.getItems().size()));
    }

    public void cities(){
        ArrayList<String> c = new ArrayList<>();
        for (int i = 0; i<table.getItems().size(); i++){
            c.add(table.getItems().get(i).getCity());
        }
        Set<String> s = new HashSet<String>(c);
        lb2.setText(String.valueOf(s.size()));
    }

    public void editFlight(){
        int i = Integer.parseInt(tf1.getText());
        Flight todel = null;
        for (Flight f:table.getItems()) {
            if(f.getId()==i){
                todel=f;
                break;
            };
        }
        table.getItems().remove(todel);
        table.getItems().add(new Flight(i,tf2.getText(),tf3.getText(),tf4.getText(), Integer.parseInt(tf5.getText())));
        tf1.clear();tf2.clear();tf3.clear();tf4.clear();tf5.clear();
    };

    public void openFile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Загрузка таблицы");
        File file = fileChooser.showOpenDialog(new Stage());
        ObservableList<Flight> list = table.getItems();
        list.removeAll();

        String line;
        BufferedReader inp = null;
        String[] data = new String[4];
        try{
            inp = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            while ((line = inp.readLine()) != null){
                line = line.trim();
                if(line.equals("")) continue;
                data = line.split("\\s+");

                list.add(new Flight(Integer.parseInt(data[0]),data[1],data[2],data[3],Integer.parseInt(data[4])));
            }
        } catch (Exception e){
            System.out.println("Error - " + e.getMessage());
        }
    }
    public void saveFile(){
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Сохранение таблицы");
            File file = fileChooser.showSaveDialog(new Stage());
            ObservableList<Flight> list = table.getItems();
            PrintWriter out = null;
            try{
                out = new PrintWriter(new FileWriter(file.getAbsoluteFile()));
                for (Flight fligh:list) {
                    StringBuilder line = new StringBuilder();
                    line.append(String.format("%d %s %s %s %d", fligh.getId(), fligh.getType(), fligh.getCity(), fligh.getTt(), fligh.getAt()));
                    out.println(line.toString());
                }
            }
            catch (IOException e){
                System.out.println(e);
            }
            finally {
                if(out != null) out.close();
            }
    };

    public void addFlight(){
        table.getItems().add(new Flight(Integer.parseInt(tf1.getText()),tf2.getText(),tf3.getText(),tf4.getText(), Integer.parseInt(tf5.getText())));
        tf1.clear();tf2.clear();tf3.clear();tf4.clear();tf5.clear();
    };

    public void del1(){
        Flight todel = null;
        for (Flight f:table.getItems()) {
            if(f.getId()==Integer.parseInt(tf6.getText())){
                todel=f;
                break;
            };
        }
        table.getItems().remove(todel);
        tf6.clear();
    };

    public void del2(){
        ArrayList<Flight> todel = new ArrayList<Flight>();
        for (Flight f:table.getItems()) {
            if(f.getCity().equals(tf7.getText())){
                todel.add(f);
            };
        }
        table.getItems().removeAll(todel);
        tf7.clear();
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        c1.setCellValueFactory(new PropertyValueFactory<>("id"));
        c2.setCellValueFactory(new PropertyValueFactory<>("type"));
        c3.setCellValueFactory(new PropertyValueFactory<>("city"));
        c4.setCellValueFactory(new PropertyValueFactory<>("tt"));
        c5.setCellValueFactory(new PropertyValueFactory<>("at"));
        table.setItems(getFlightList());
        c6.setCellValueFactory(new PropertyValueFactory<>("type"));
        c7.setCellValueFactory(new PropertyValueFactory<>("at"));
        table2.setItems(getMimimalList());
    }

    ObservableList<Flight> getFlightList(){
        ObservableList<Flight> flights = FXCollections.observableArrayList();
        return flights;
    };

    ObservableList<Mimimal> getMimimalList(){
        ObservableList<Mimimal> mimimals = FXCollections.observableArrayList();
        return mimimals;
    };

}
