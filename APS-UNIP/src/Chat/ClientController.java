package Chat;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import gui.Person;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

// esse código é bem simples de entender já que voce deve ter entendido o controller do Server (Servidor)

public class ClientController implements Initializable {

	private Stage stage;

	private Connection conn;
	@FXML
    private ImageView imagemsalasopolis;
	@FXML
	private TextArea txtmsg;
	@FXML
	private Label usuarionome;
	@FXML
	private TextArea txtinput;

	@FXML
	private Button btndesconnect;

	@FXML
	private Button btnconnect;

	@FXML
	private Button btnexit;

	@FXML
	private Button btnsend;

	public ClientController() {

	}

	public ClientController(Stage stage) {
		this.stage = stage;

		//this.stage.setTitle("TESTEEEEEEEEEEEEEE" + " esta off-line - Chat Client");

		this.stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			public void handle(WindowEvent event) {
				System.exit(0);
			}
		});
	}

	public void iniciar(String nomelogin, Person comboBoxPerson) {

		conn = new Connection("["+comboBoxPerson + "]" + ":" + nomelogin + "\r\n", txtinput, txtmsg);
		
		

		usuarionome.setText(comboBoxPerson + "--->" + nomelogin + "\r\n");

		txtmsg.setFont(new Font("Sans serif", 16));

		conn.setButtons(btnconnect, btndesconnect, btnsend);

		btndesconnect.setDisable(true);
		btnsend.setDisable(true);

		txtmsg.setEditable(false);
		txtinput.setFont(new Font("Sans serif", 16));
		txtinput.setEditable(false);
	}

	@FXML
	void handleconnection(ActionEvent event) {
		if (event.getSource().equals(btnconnect)) {
			Executor service = Executors.newFixedThreadPool(1);
			service.execute(conn);
		} else if (event.getSource().equals(btndesconnect)) {
			try {
				conn.desconnect();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	@FXML
	void handledexit(ActionEvent event) {
		System.exit(0);
	}
	@FXML
	void Keyexit(KeyEvent event) throws IOException {// foi criado para utilizar o enter ou espaço para entrar na aplicação sem utilizar o mouse.
														// pensando na acessibilidade.	
		if(event.getCode()== KeyCode.ENTER) {
		
			System.exit(0);
	
	}}

	@FXML
	void handlesend(ActionEvent event) throws IOException {
		
		
		if (txtinput.getText().trim().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("ÁREA de mensagem vazia");
			alert.setContentText("O erro acontece devido o usuario não  digitar alguma mensagem.");
			alert.show();

		}else {

			conn.sendMessage(txtinput.getText());
			}
}
	
	
	
	@FXML
	void KEYMSG(KeyEvent event) throws IOException {// foi criado para utilizar o enter ou espaço para entrar na aplicação sem utilizar o mouse.
													// pensando na acessibilidade.	
		if(event.getCode()== KeyCode.ENTER) {
			
			if (txtinput.getText().trim().isEmpty()) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("ÁREA de mensagem  vazia");
				alert.setContentText("O erro acontece devido o usuario não  digitar alguma mensagem.");
				alert.show();

			}else {

				conn.sendMessage(txtinput.getText());
				}
	}
		}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

}
