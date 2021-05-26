package Login;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Chat.ChatClient;
import Chat.ClientController;
import gui.Constraints;
import gui.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

public class Logincontroller implements Initializable {
	@FXML
	private Button bconectar;
	@FXML
	private TextField nomelogin;
	@FXML
	private ComboBox<Person> comboBoxPerson;
	@FXML
	private ObservableList<Person> obsList;
	

	
	@FXML
	public void onComboBoxPersonAction() {
		 comboBoxPerson.getSelectionModel().getSelectedItem();
		 
		 
	}
	
	
	@FXML
	void conecta(ActionEvent event) throws IOException { // TODO troquei o nome do metodo pois estava com o mesmo nome
															// do objeto

		if (comboBoxPerson.getValue() == null | nomelogin.getText().trim().isEmpty())  {
			
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("login invalido");
			alert.setContentText("Por favor, verifique seu Nome ou Setor");
			alert.show();

		}else {

			((Node) (event.getSource())).getScene().getWindow().hide();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/chatCliente.fxml"));
			Parent root = (Parent) loader.load();
			ClientController ClienttController = loader.getController();
			ClienttController.iniciar(nomelogin.getText(),comboBoxPerson.getValue()); // TODO criei este metodo pra passar o usuario logado
			Stage stage = new Stage();
			Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Chat Client");
			stage.show();
				}
	};

	
	@FXML
    void Key(KeyEvent event) throws IOException { // foi criado para utilizar o enter ou espaço para entrar na aplicação sem utilizar o mouse.
													// pensando na acessibilidade.	
		
		if(event.getCode()== KeyCode.ENTER) { 
			
			if (comboBoxPerson.getValue() == null | nomelogin.getText().trim().isEmpty())  {
			
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("login invalido");
				alert.setContentText("O erro acontece devido o usuario ser inválido, por favor digite seu nome.");
				alert.show();

			}else {

				((Node) (event.getSource())).getScene().getWindow().hide();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/chat/chatCliente.fxml"));
				Parent root = (Parent) loader.load();
				ClientController ClienttController = loader.getController();
				ClienttController.iniciar(nomelogin.getText(),comboBoxPerson.getValue()); // TODO criei este metodo pra passar o usuario logado
				Stage stage = new Stage();
				Scene scene = new Scene(root);
				stage.setScene(scene);
				stage.setTitle("Chat Client");
				stage.show();
					}
		};
			
			
		}
	
	
	
	
	

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		
		List<Person> list = new ArrayList<>();
		list.add(new Person("Inspetor"));
		list.add(new Person("Secretaria"));

		obsList = FXCollections.observableArrayList(list);
		comboBoxPerson.setItems(obsList);

		Callback<ListView<Person>, ListCell<Person>> factory = lv -> new ListCell<Person>() {
			@Override
			protected void updateItem(Person item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		comboBoxPerson.setCellFactory(factory);
		comboBoxPerson.setButtonCell(factory.call(null));
		
		
	
	}


}

