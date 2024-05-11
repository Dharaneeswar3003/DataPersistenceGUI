 import java.io.BufferedWriter;
 import java.io.FileWriter;
 import java.io.IOException;
 import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
  import javafx.event.ActionEvent;
  import javafx.fxml.FXML;
 import javafx.scene.control.Alert;
 import javafx.scene.control.Alert.AlertType;
 import javafx.scene.control.Button;
 import javafx.scene.control.ButtonType;
 import javafx.scene.control.Label;
  import javafx.scene.control.ListView;
  import javafx.scene.control.TextField;
  
  public class Controller {
  
      @FXML
      private TextField ageField;
  
      @FXML
      private Label categoryLabel;
  
      @FXML
      private Button createButton;
  
      @FXML
      private TextField fnameTextField;
 
  
      @FXML
      private TextField lnameTextField;
  
      @FXML
      private Button loadButton;
  
      @FXML
      private Button loadCloseFriends;
  
      @FXML
      private Button loadFriends;
  
      @FXML
      private Button loadSchoolFriends;
  
      @FXML
      private Button loadWorkFriends;
  
      @FXML
      private TextField phoneNumTextField;
  
      @FXML
      private Button quitButton;

      @FXML
      private Button deleteButton;
  
      @FXML
      private Button saveButton;
  
      @FXML
      private Button saveCloseFriends;
  
      @FXML
      private Button saveFriends;
  
      @FXML
      private Button saveSchoolFriends;
  
     @FXML
      private Button saveWorkFriends;

     @FXML
     private Button clearButton;

     @FXML
     public ListView<Friends> friendsList = new ListView<>();

     private boolean friendsLoaded = false;

  
      @FXML
      public void initialize() {
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);
          loadCloseFriends.setDisable(true);
          loadWorkFriends.setDisable(true);
          loadFriends.setDisable(true);
          loadSchoolFriends.setDisable(true);
          deleteButton.setDisable(true);
          clearButton.setDisable(true);
      }
  
      @FXML
      void createFriend(ActionEvent event) {

        // Clear the existing loaded friends only if friends were loaded
    if (friendsLoaded) {
        friendsList.getItems().clear();
        friendsLoaded = false; // Reset the flag
    }

         String firstName = fnameTextField.getText();
         String lastName = lnameTextField.getText();
         String phoneNum = phoneNumTextField.getText();
     
         // Check if first name is empty or contains non-alphabetical characters
         if (firstName.isBlank()) {
             showAlert(Alert.AlertType.ERROR, "Invalid Name", "First name field cannot be blank.");
             return;
         }
         if (!firstName.matches("[a-zA-Z]+")) {
             showAlert(Alert.AlertType.ERROR, "Invalid Name", "Name must contain only alphabetical characters.");
             return;
         }
     
         // Check if last name is empty or contains non-alphabetical characters
         if (lastName.isBlank()) {
             showAlert(Alert.AlertType.ERROR, "Invalid Name", "Last name field is blank.");
             return;
         }
         if (lastName.isBlank() || !lastName.matches("[a-zA-Z]+")) {
             showAlert(Alert.AlertType.ERROR, "Invalid Name", "Name must contain only alphabetical characters.");
             return;
         }
     
         // Check if age is empty or contains non-numeric characters
         if (!ageField.getText().matches("\\d+")) {
             showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age must be a number.");
             return;
         }
         if (ageField.getText().isBlank()) {
             showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age field cannot be blank.");
             return;
         }
     
         // Convert age to integer
         int age = Integer.parseInt(ageField.getText());
     
         // Check if age is within a valid range
         if (age < 0 || age > 99) {
             showAlert(Alert.AlertType.ERROR, "Invalid Age", "Age must be between 0 and 99.");
             return;
         }
     
         // Check if phone number is empty or contains non-numeric characters
         if (phoneNumTextField.getText().isBlank()) {
             showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone Number field cannot be blank.");
             return;
         }
         if (!phoneNumTextField.getText().matches("\\d+")) {
             showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number must contain only numeric characters.");
             return;
         }
     
         // Check if phone number is of valid length
         if (phoneNumTextField.getText().length() != 10) {
             showAlert(Alert.AlertType.ERROR, "Invalid Phone Number", "Phone number must be 10 digits long.");
             return;
         }
         // Check if phone number already exists in the list
         for (Friends friend : friendsList.getItems()) {
             if (friend.getPhoneNumber().equals(phoneNum)) {
                 showAlert(Alert.AlertType.ERROR, "Duplicate Entry", "Phone number already exists.");
                 return;
             }
         }
         // Check if the combination of first name and last name already exists in the list
         for (Friends friend : friendsList.getItems()) {
              if (friend.getfirstName().equalsIgnoreCase(firstName) && friend.getlastName().equalsIgnoreCase(lastName)) {
                 showAlert(Alert.AlertType.ERROR, "Duplicate Entry", "Friend already exists.");
                 return;
             }
         }
     
         Friends newFriend = new Friends(firstName, lastName, age, phoneNum);
         friendsList.getItems().add(newFriend);
             fnameTextField.clear();
             lnameTextField.clear();
             ageField.clear();
             phoneNumTextField.clear();
  
      }
  
      @FXML
      void load(ActionEvent event) {

          loadCloseFriends.setDisable(false);
          loadFriends.setDisable(false);
          loadSchoolFriends.setDisable(false);
          loadWorkFriends.setDisable(false);
          friendsLoaded = true; // Friends are loaded
  
      }
  
      @FXML
      void loadCloseFriends(ActionEvent event) {
        friendsList.getItems().clear(); // Clear the existing items in the ListView

         try {
             ArrayList<Friends> CloseloadedFriends = CreateFriend.createAllFriends("CloseFriends.txt");
             friendsList.getItems().addAll(CloseloadedFriends); // Add loaded friends to the ListView
             CloseloadedFriends.clear();
         } catch (IOException e) {
             showAlert(Alert.AlertType.ERROR, "Error", "Failed to load friends from file.");
             e.printStackTrace();
         }
 
         loadCloseFriends.setDisable(true);
         loadFriends.setDisable(true);
         loadSchoolFriends.setDisable(true);
         loadWorkFriends.setDisable(true);
        }

  
      @FXML
      void loadFriends(ActionEvent event) {
         friendsList.getItems().clear(); // Clear the existing items in the ListView
         try {
             ArrayList<Friends> loadedFriends = CreateFriend.createAllFriends("Friends.txt");
             friendsList.getItems().addAll(loadedFriends); // Add loaded friends to the ListView
             loadedFriends.clear();

         } catch (IOException e) {
             showAlert(Alert.AlertType.ERROR, "Error", "Failed to load friends from file.");
             e.printStackTrace();
         }
         loadCloseFriends.setDisable(true);
         loadFriends.setDisable(true);
         loadSchoolFriends.setDisable(true);
         loadWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void loadSchoolFriends(ActionEvent event) {
         friendsList.getItems().clear(); // Clear the existing items in the ListView
         try {
             ArrayList<Friends> SchoolloadedFriends = CreateFriend.createAllFriends("SchoolFriends.txt");
             friendsList.getItems().addAll(SchoolloadedFriends); // Add loaded friends to the ListView
             SchoolloadedFriends.clear();

         } catch (IOException e) {
             showAlert(Alert.AlertType.ERROR, "Error", "Failed to load friends from file.");
             e.printStackTrace();
         }
         loadCloseFriends.setDisable(true);
         loadFriends.setDisable(true);
         loadSchoolFriends.setDisable(true);
         loadWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void loadWorkFriends(ActionEvent event) {
         friendsList.getItems().clear(); // Clear the existing items in the ListView
         try {
             ArrayList<Friends> WorkloadedFriends = CreateFriend.createAllFriends("WorkFriends.txt");
             friendsList.getItems().addAll(WorkloadedFriends); // Add loaded friends to the ListView
             WorkloadedFriends.clear();

         } catch (IOException e) {
             showAlert(Alert.AlertType.ERROR, "Error", "Failed to load friends from file.");
             e.printStackTrace();
         }
         loadCloseFriends.setDisable(true);
         loadFriends.setDisable(true);
         loadSchoolFriends.setDisable(true);
         loadWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void quit(ActionEvent event) {
 
         // Opens a confirmation alert box...
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
         alert.setTitle("Confirmation Dialog");
         alert.setHeaderText("Quit");
         alert.setContentText("Are you sure you want to quit?");
         ButtonType yesButton = new ButtonType("Yes");
         ButtonType noButton = new ButtonType("No");
         alert.getButtonTypes().setAll(yesButton, noButton);
         alert.showAndWait().ifPresent(response -> {
             if (response == yesButton) {
                 System.out.println("Exited the program successfully!");
                 // If user clicks on yes button, the program will close.
                 System.exit(0);
             }
             // If user clicks on no button then the program will pick up where it left off.
         });
  
      }

      @FXML
void save(ActionEvent event) {
    ObservableList<Friends> flist = friendsList.getItems();

    if (flist.isEmpty()) {
        showAlert(AlertType.INFORMATION, "No Friends", "There are no friends to save.");
        return;
    }

    List<Friends> uniqueFriends = new ArrayList<>();
    List<Friends> duplicates = new ArrayList<>();
    
    // Check for duplicates and add unique friends to the list
    for (Friends friend : flist) {
        if (isDuplicateFriend(friend)) {
            duplicates.add(friend);
        } else {
            uniqueFriends.add(friend); 
            System.out.println("Duplicates not found");
        }
    }

    // Clear the ListView and add unique friends back
    friendsList.getItems().clear();
    friendsList.getItems().addAll(uniqueFriends);

    // Show alert for duplicates
    if (!duplicates.isEmpty()) {
        String message = "The following friends cannot be saved because they are duplicates:\n";
        for (Friends duplicate : duplicates) {
            message += duplicate.getfirstName() + " " + duplicate.getlastName() + "\n";
        }
        showAlert(AlertType.WARNING, "Duplicate Friends", message);
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);

    } else {

    // Enable saving buttons
    saveCloseFriends.setDisable(false);
    saveFriends.setDisable(false);
    saveSchoolFriends.setDisable(false);
    saveWorkFriends.setDisable(false);
    }
}

      @FXML
      void saveToCloseFriends(ActionEvent event) {
         ObservableList<Friends> flist = friendsList.getItems();
         try {
             for (Friends friend : flist) {
                 friend.writeToFile("CloseFriends.txt");  //Writes the existing friends to the file
             }
             showAlert(AlertType.INFORMATION, "Success", "Saved close friends successfully.");
             friendsList.getItems().clear();
         } catch (IOException e) {
             showAlert(AlertType.ERROR, "Error", "Failed to save close friends. Please try again.");
         }
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void saveToFriends(ActionEvent event) {
         ObservableList<Friends> flist = friendsList.getItems();
         try {
             for (Friends friend : flist) {
                 friend.writeToFile("Friends.txt");  //Writes the existing friends to the file
             }
             showAlert(AlertType.INFORMATION, "Success", "Saved close friends successfully.");
             friendsList.getItems().clear();
         } catch (IOException e) {
             showAlert(AlertType.ERROR, "Error", "Failed to save close friends. Please try again.");
         }
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void saveToSchoolFriends(ActionEvent event) {
         ObservableList<Friends> flist = friendsList.getItems();
         try {
             for (Friends friend : flist) {
                 friend.writeToFile("SchoolFriends.txt");  //Writes the existing friends to the file
             }
             showAlert(AlertType.INFORMATION, "Success", "Saved close friends successfully.");
             friendsList.getItems().clear();
         } catch (IOException e) {
             showAlert(AlertType.ERROR, "Error", "Failed to save close friends. Please try again.");
         }
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);
  
      }
  
      @FXML
      void saveToWorkFriends(ActionEvent event) {
         ObservableList<Friends> flist = friendsList.getItems();
         try {
             for (Friends friend : flist) {
                 friend.writeToFile("WorkFriends.txt"); //Writes the existing friends to the file
             }
             showAlert(AlertType.INFORMATION, "Success", "Saved close friends successfully.");
             friendsList.getItems().clear();
         } catch (IOException e) {
             showAlert(AlertType.ERROR, "Error", "Failed to save close friends. Please try again.");
         }
          saveCloseFriends.setDisable(true);
          saveFriends.setDisable(true);
          saveSchoolFriends.setDisable(true);
          saveWorkFriends.setDisable(true);
 
  
      }

      //Method to show Alert messages...
      private void showAlert(Alert.AlertType type, String title, String message) {
         Alert alert = new Alert(type);
         alert.setTitle(title);
         alert.setHeaderText(null);
         alert.setContentText(message);
         alert.showAndWait();
     }

     //Method to check for duplicate friends...
     private boolean isDuplicateFriend(Friends newFriend) {
        ObservableList<Friends> existingFriends = friendsList.getItems();
        List<Friends> loadedFriends = new ArrayList<>();
        try {
            //Check for duplicate friends in the files...
            loadedFriends.addAll(CreateFriend.createAllFriends("CloseFriends.txt"));
            loadedFriends.addAll(CreateFriend.createAllFriends("Friends.txt"));
            loadedFriends.addAll(CreateFriend.createAllFriends("SchoolFriends.txt"));
            loadedFriends.addAll(CreateFriend.createAllFriends("WorkFriends.txt"));
        } catch (IOException e) {
            showAlert(AlertType.ERROR, "Error", "Failed to load friends from files.");
            e.printStackTrace();
        }

        for (Friends friend : existingFriends) {
            if (friend == newFriend) {
                continue; // Skip comparing with itself
            }
            if ((friend.getfirstName().equalsIgnoreCase(newFriend.getfirstName()) && friend.getlastName().equalsIgnoreCase(newFriend.getlastName())) ||
            friend.getPhoneNumber().equals(newFriend.getPhoneNumber())) {
                return true; // Duplicate found
            }
        }

        for (Friends friend : loadedFriends) {
            if ((friend.getfirstName().equalsIgnoreCase(newFriend.getfirstName()) && friend.getlastName().equalsIgnoreCase(newFriend.getlastName())) ||
            friend.getPhoneNumber().equals(newFriend.getPhoneNumber())) {
                return true; // Duplicate found
            }
        }

        return false; // No duplicate found
    
  }

  @FXML
     void displayFriendDetails() {
         Friends selectedFriend = friendsList.getSelectionModel().getSelectedItem();
            if (selectedFriend != null) {
                createButton.setDisable(true);
                deleteButton.setDisable(false);
                clearButton.setDisable(false);
                fnameTextField.setText(selectedFriend.getfirstName());
                 lnameTextField.setText(selectedFriend.getlastName());
                 ageField.setText(String.valueOf(selectedFriend.getAge()));
                 phoneNumTextField.setText(selectedFriend.getPhoneNumber());
             }
      }

      @FXML
void delete(ActionEvent event) {
    Friends selectedFriend = friendsList.getSelectionModel().getSelectedItem();
    if (selectedFriend != null) {
        // Remove from the ListView
        friendsList.getItems().remove(selectedFriend);

        // Remove from the file it belongs to
        removeFriendFromFile(selectedFriend);
        fnameTextField.clear();
        lnameTextField.clear();
        ageField.clear();
        phoneNumTextField.clear();
        createButton.setDisable(false);
    }
}

private void removeFriendFromFile(Friends friendToRemove) {
    try {
        // Iterate through each file
        String[] files = {"CloseFriends.txt", "Friends.txt", "SchoolFriends.txt", "WorkFriends.txt"};
        for (String file : files) {
            ArrayList<Friends> friendsInFile = CreateFriend.createAllFriends(file);
            for (Friends friend : friendsInFile) {
                // Compare details to find the friend to remove
                if (friend.getfirstName().equalsIgnoreCase(friendToRemove.getfirstName()) &&
                    friend.getlastName().equalsIgnoreCase(friendToRemove.getlastName()) &&
                    friend.getAge() == friendToRemove.getAge() &&
                    friend.getPhoneNumber().equals(friendToRemove.getPhoneNumber())) {
                    // Remove the friend from the list
                    friendsInFile.remove(friend);
                    // Rewrite the file without the deleted friend
                    rewriteFile(file, friendsInFile);
                    return; // Exit after removing the friend
                }
            }
        }
    } catch (IOException e) {
        showAlert(AlertType.ERROR, "Error", "Failed to remove friend from file.");
        e.printStackTrace();
    }
}

private void rewriteFile(String fileName, ArrayList<Friends> friends) throws IOException {
    FileWriter fw = new FileWriter(fileName);
    BufferedWriter bw = new BufferedWriter(fw);
    for (Friends friend : friends) {
        bw.write(friend.getfirstName() + ", " + friend.getlastName() + ", " + friend.getAge() + ", " + friend.getPhoneNumber() + ";\n");
    }
    bw.close();
}

@FXML
void clear(ActionEvent event) {
    createButton.setDisable(false);
    fnameTextField.clear();
        lnameTextField.clear();
        ageField.clear();
        phoneNumTextField.clear();

}

}
