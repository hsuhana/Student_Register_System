package com.example.lab2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class StudentController {

    @FXML
    private TableView<Student> table;

    //Name columns, one column with one declaration

    @FXML
    //column track number
    private TableColumn<Student, Integer> trackNumberColumn;

    @FXML
    //column id
    private TableColumn<Student, Integer> idColumn;

    @FXML
    //column name
    private TableColumn<Student, String> nameColumn;

    @FXML
    //column major
    private TableColumn<Student, String> majorColumn;

    @FXML
    //id input field
    private TextField idInput;

    @FXML
    //name input field
    private TextField nameInput;

    @FXML
    //major input field
    private TextField majorInput;

    @FXML
    //add button
    private Button addButton;

    @FXML
    //delete button
    private Button deleteButton;

    @FXML
    //unselect button
    private Button unselectButton;

    private int currentTrackNumber = 0;

    private int lastTrackNumber = 0;

    public void initialize(){

        //for initial width of column
        trackNumberColumn.setMinWidth(50.0);
        //show hard code value, value need to the same as Student class
        trackNumberColumn.setCellValueFactory(new PropertyValueFactory<>("trackNumber"));

        //for initial width of column
        idColumn.setMinWidth(200.0);
        //show hard code value, value need to the same as Student class
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        //for initial width of column
        nameColumn.setMinWidth(200.0);
        //show hard code value, value need to the same as Student class
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //for initial width of column
        majorColumn.setMinWidth(200.0);
        //show hard code value, value need to the same as Student class
        majorColumn.setCellValueFactory(new PropertyValueFactory<>("major"));

        //use method to fill in table
        table.setItems(getStudentInfo());
        //fill in columns
        table.getColumns().addAll(trackNumberColumn, idColumn, nameColumn, majorColumn);

    }

    //Get all default(hard code) student information
    public ObservableList<Student> getStudentInfo(){
        ObservableList<Student> students = FXCollections.observableArrayList();
        students.add(new Student(1, 200548417, "Fang-Ting Hsu", "Computer Programming"));
        students.add(new Student(2, 200133456, "Megumi Kabasawa", "Information Technology"));
        students.add(new Student(3, 205634297, "May Hirata", "Global Management"));
        students.add(new Student(4, 202455672, "Kaori Kumura", "Global Business"));
        return students;
    }

    //add function
    public void addButtonClicked(){

        //get all data from the table
        ObservableList<Student> tableAllData = table.getItems();
        int size = tableAllData.size();
        boolean findTrackNumber = false;

        for (Student student : tableAllData) {
            if (student.getTrackNumber() == currentTrackNumber && size > 0) {
                student.setTrackNumber(currentTrackNumber);
                student.setId(Integer.parseInt(idInput.getText()));
                student.setName(nameInput.getText());
                student.setMajor(majorInput.getText());
                findTrackNumber = true;

                //save to table
                table.getItems().add(student);
                table.refresh();
                break;
            }
        }

        if(!findTrackNumber && size > 0){
            Student lastOne = tableAllData.get(size - 1);
            lastTrackNumber = lastOne.getTrackNumber();
            Student newStudent = new Student();
            newStudent.setTrackNumber(lastTrackNumber + 1);
            newStudent.setId(Integer.parseInt(idInput.getText()));
            newStudent.setName(nameInput.getText());
            newStudent.setMajor(majorInput.getText());
            table.getItems().add(newStudent);

        }else if(!findTrackNumber && size == 0){
            Student newStudent = new Student();
            newStudent.setTrackNumber(1);
            newStudent.setId(Integer.parseInt(idInput.getText()));
            newStudent.setName(nameInput.getText());
            newStudent.setMajor(majorInput.getText());
            table.getItems().add(newStudent);
        }

        //clear all data in the input field
        idInput.clear();
        nameInput.clear();
        majorInput.clear();

        deleteButtonClicked();
    }



//       Student student = new Student();
//       //getText function will get String
//       student.setId(Integer.parseInt(idInput.getText()));
//       student.setName(nameInput.getText());
//       student.setMajor(majorInput.getText());
//       //save to table
//       table.getItems().add(student);
//       //clear all data in the input field
//       idInput.clear();
//       nameInput.clear();
//       majorInput.clear();

    //delete function
    public void deleteButtonClicked(){
        ObservableList<Student> studentSelected;
        ObservableList<Student> allStudent;

        allStudent = table.getItems();
        studentSelected = table.getSelectionModel().getSelectedItems();

        //studentSelected.forEach(allStudent::remove);
        for (Student student : studentSelected) {
            allStudent.remove(student);
        }
    }

    //edit function
    public void mouseClickEdit(){
        Student studentSelected = (Student) table.getSelectionModel().getSelectedItem();

        currentTrackNumber = studentSelected.getTrackNumber();
        idInput.setText(String.valueOf(studentSelected.getId()));
        nameInput.setText(studentSelected.getName());
        majorInput.setText(studentSelected.getMajor());
    }

    //unselect function
    public void unselectClicked(){
        table.getSelectionModel().clearSelection();

        currentTrackNumber = 0;
        idInput.clear();
        nameInput.clear();
        majorInput.clear();
    }
}