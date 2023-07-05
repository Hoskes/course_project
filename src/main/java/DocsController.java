import Models.TableModels.Profile;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class DocsController {
    public TextField pass_ser;
    public TextField pass_num;
    public Button save;

    public void send_docs(ActionEvent actionEvent) {
        if(pass_ser.getText().matches("([0-9]{4})")& pass_num.getText().matches("([0-9]{6})") ){
            pass_num.setStyle("-fx-text-fill: green; ");
            pass_ser.setStyle("-fx-text-fill: green; ");
            Profile.sendDocs(pass_ser.getText(),pass_num.getText());
        }else{
            pass_num.setStyle("-fx-text-fill: red; ");
            pass_ser.setStyle("-fx-text-fill: red; ");

        }
    }
}
