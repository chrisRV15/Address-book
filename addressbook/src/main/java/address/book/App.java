package address.book;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
//import java.io.FileDescriptor;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



/**
 * JavaFX App
 */
public class App extends Application {

    //private static Scene scene;
    FileChooser fileChooser = new FileChooser();
    private TableView<Person> table = new TableView<Person>();
    private final ObservableList<Person> data = FXCollections.observableArrayList();
    private final ObservableList test = FXCollections.observableArrayList();

    final HBox hb = new HBox();
    final GridPane tp = new GridPane();
    //ListView list = new ListView(test);

    @Override
    public void start(Stage stage) {
        
        Scene scene = new Scene(new Group());
        tp.setPadding(new Insets(10,10,10,10)); 
        tp.setVgap(5);
        tp.setHgap(5); 
        tp.setAlignment(Pos.TOP_CENTER); 
  
        Scene scene2 = new Scene(tp);
        stage.setTitle("Address Book");
        stage.setWidth(840);
        stage.setHeight(600);

        Label label = new Label("Address Book");
        label.setFont(new Font("Times New Roman", 30));
        table.setEditable(true);

        TableColumn firstName = new TableColumn("FirstName");
        firstName.setMinWidth(100);
        firstName.setCellValueFactory(new PropertyValueFactory<Person,String>("firstName"));

        firstName.setCellFactory(TextFieldTableCell.forTableColumn());
        firstName.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setFirstName(table.getNewValue());
                }
             }
        );

        TableColumn lastName = new TableColumn("Last Name");
        lastName.setMinWidth(100);
        lastName.setCellValueFactory(new PropertyValueFactory<Person,String>("lastName"));

        lastName.setCellFactory(TextFieldTableCell.forTableColumn());
        lastName.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setLastName(table.getNewValue());
                }
             }
        );

        TableColumn email = new TableColumn("Email");
        email.setMinWidth(200);
        email.setCellValueFactory(new PropertyValueFactory<Person,String>("email"));

        email.setCellFactory(TextFieldTableCell.forTableColumn());
        email.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setEmail(table.getNewValue());
                }
             }
        );

        TableColumn street = new TableColumn("Street");
        street.setMinWidth(200);
        street.setCellValueFactory(new PropertyValueFactory<Person,String>("street"));

        street.setCellFactory(TextFieldTableCell.forTableColumn());
        street.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setStreet(table.getNewValue());
                }
             }
        );

        TableColumn city = new TableColumn("City");
        city.setMinWidth(100);
        city.setCellValueFactory(new PropertyValueFactory<Person,String>("city"));

        city.setCellFactory(TextFieldTableCell.forTableColumn());
        city.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setCity(table.getNewValue());
                }
             }
        );

        TableColumn state = new TableColumn("State");
        state.setMinWidth(100);
        state.setCellValueFactory(new PropertyValueFactory<Person,String>("state"));

        state.setCellFactory(TextFieldTableCell.forTableColumn());
        state.setOnEditCommit(
            new EventHandler<CellEditEvent<Person, String>>() {
                @Override
                public void handle(CellEditEvent<Person, String> table) {
                ((Person) table.getTableView().getItems().get(
                     table.getTablePosition().getRow())
                    ).setState(table.getNewValue());
                }
             }
        );
        
        //Stting the data in the table
        table.setItems(data);
        table.getColumns().addAll(firstName,lastName,email,street,city,state);
       // email.getColumns().addAll(city,state);

        //TextFields for addning new data
        TextField addFirstName = new TextField();
        addFirstName.setPromptText("FirstName");
        //addFirstName.setMaxWidth(firstName.getPrefWidth());
        TextField addLastName = new TextField();
        addLastName.setPromptText("Last Name");
        //addLastName.setMaxWidth(lastName.getPrefWidth());
        TextField addEmail = new TextField();
        addEmail.setPromptText("Email");
        //addEmail.setMaxWidth(email.getPrefWidth());
        TextField addStreet = new TextField();
        addStreet.setPromptText("Street");
        //addStreet.setMinWidth(street.getPrefWidth());
        TextField addCity = new TextField();
        addCity.setPromptText("City");
        //addCity.setMaxWidth(city.getPrefWidth());
        TextField addState = new TextField();
        addState.setPromptText("State");
        //addState.setMaxWidth(state.getPrefWidth());
        TextField searchField = new TextField();
        searchField.setPromptText("Search by name");


        //Adding new contact button
        Button bn = new Button("Add Contact");
        bn.setOnAction( new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e){
                if(addFirstName.getText().isEmpty()|| addLastName.getText().isEmpty()|| addStreet.getText().isEmpty()|| addCity.getText().isEmpty()
                || addState.getText().isEmpty() || addEmail.getText().isEmpty()){
                    Alert textAlert = new Alert(Alert.AlertType.ERROR, "Empty TextField?", ButtonType.OK);
                    textAlert.setContentText("You miss a field");
                    textAlert.showAndWait();
                    if(textAlert.getResult() == ButtonType.OK){
                        textAlert.close();
                    }
                }
                else{
                data.add(new Person(addFirstName.getText(),addLastName.getText(),addEmail.getText(),
                addStreet.getText(),addCity.getText(),addState.getText()));
                test.addAll(addLastName.getText());
                addFirstName.clear();
                addLastName.clear();
                addEmail.clear();
                addStreet.clear();
                addCity.clear();
                addState.clear();
                }
            }
        });

        //Delete contact button
        Button delBn = new Button("Delete");
        delBn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                Alert delAlert = new Alert(Alert.AlertType.CONFIRMATION);
                delAlert.setTitle("Confirmation dialog");
                delAlert.setContentText("Are you sure to delete contact?");
                Optional <ButtonType> action = delAlert.showAndWait();
                if(action.get() == ButtonType.OK){
                table.setItems(data);
                table.getItems().removeAll(table.getSelectionModel().getSelectedItems());
                }
            }
        });

        //Saving book to file button
        Button saveBN = new Button("Save to File");
        saveBN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                if(data.isEmpty()){
                    Alert tableAlert = new Alert(Alert.AlertType.ERROR, "Empty table?", ButtonType.OK);
                    tableAlert.setContentText("You have an empty table");
                    tableAlert.showAndWait();
                    if(tableAlert.getResult() == ButtonType.OK){
                        tableAlert.close();
                    }
                }else{
                    File file = fileChooser.showSaveDialog(stage);
                    saveFile(data, file);
                }   
            }
        });

        //Return to address book scene button
        Button switch2Bn = new Button("Return");
        switch2Bn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                stage.setScene(scene);
                stage.setWidth(840);
                stage.setHeight(600);
            }
            
        });

        //Adding new contact scene button
        Button switchBn = new Button("New contact");
        switchBn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                stage.setScene(scene2);
                stage.setWidth(600);
                stage.setHeight(300);
            }
        });

        //Load File button
        Button loadF = new Button("Load");
        loadF.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e){
                List<Person> inputPerson = null;
                File file = fileChooser.showOpenDialog(stage);
                try {
                    inputPerson = readPersons(file);
                    table.getItems().setAll(inputPerson);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        
         //Searching contact by first name or last name
         FilteredList<Person> filteredData = new FilteredList<>(data, e -> true);
         searchField.setOnKeyReleased(new EventHandler<Event>() {
             @Override
             public void handle(Event e){
                 searchField.textProperty().addListener((ObservableValue,oldValue, newValue) -> {
                     filteredData.setPredicate((Predicate<? super Person>) person->{
                         if(newValue == null || newValue.isEmpty()){
                             return true;
                         }
                         String lowerCaseFilter = newValue.toLowerCase();
                         if(person.getFirstName().toLowerCase().contains(lowerCaseFilter)){
                             return true;
                         }else if(person.getLastName().toLowerCase().contains(lowerCaseFilter)){
                             return true;
                         }
                         return false;
                     });
                     SortedList<Person> sortedData = new SortedList<>(filteredData);
                     sortedData.comparatorProperty().bind(table.comparatorProperty());
                     table.setItems(sortedData);
                 });
             }
         });
         

       


        //tp.getChildren().addAll(addFirstName,addLastName,addEmail,addStreet,addCity,addState,bn,switch2Bn);
        //Adding new contact Texts,TextFields, and LIstView
        //list.setMaxSize(150, 250);
        Text text1 = new Text("First Name");
        Text text2 = new Text("Last Name");
        Text text3 = new Text("Email");
        Text text4 = new Text("Street");    
        Text text5 = new Text("City");
        Text text6 = new Text("State");
        tp.add(text1,0,0);
        tp.add(addFirstName, 1, 0);
        tp.add(text2,3,0);
        tp.add(addLastName,4,0);
        tp.add(text4,0,1);
        tp.add(addStreet,1,1);
        tp.add(text5,0,2);
        tp.add(addCity,1,2);
        tp.add(text6,3,2);
        tp.add(addState,4,2);
        tp.add(text3,0,3);
        tp.add(addEmail,1,3);
       // tp.add(list,1,4);
        tp.add(bn,0,4);
        tp.add(switch2Bn,1,4);       



        hb.getChildren().addAll(delBn,saveBN,switchBn,loadF,searchField);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, hb);
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
        stage.setScene(scene);
        stage.show();
    }


    //Save file method(start bufferedWriteer and for each loop to write in the file)
    public static void saveFile(ObservableList<Person> observableListPerson, File file){
        try{
            BufferedWriter outWriter = new BufferedWriter(new FileWriter(file));
            //observableListPerson = FXCollections.observableArrayList();

            for(Person persons : observableListPerson){
                outWriter.write(persons.getFirstName() + " " + persons.getLastName() + " " + persons.getEmail() + " " +
                persons.getStreet() + " " + persons.getCity() + " " + persons.getState());
                outWriter.newLine();
            }
            System.out.println(observableListPerson.toString());
            outWriter.close();
        }catch (IOException ev){
            Alert ioAlert = new Alert(Alert.AlertType.ERROR, "What?", ButtonType.OK);
            ioAlert.setContentText("Sorry, an error has ocurred");
            ioAlert.showAndWait();
            if(ioAlert.getResult() == ButtonType.OK){
                ioAlert.close();
            }
        }
    }

    
    //Load File 
    public static List<Person> readPersons(File file) throws IOException{
        List<Person> personsF = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while((line = reader.readLine()) != null){
            String[] info = line.split(" ");
            personsF.add(new Person(info[0],info[1],info[2],info[3],info[4],info[5]));
            
        }
        reader.close(); 
        return personsF;
    } 

       public static void main(String[] args) {
        launch();
    }

}