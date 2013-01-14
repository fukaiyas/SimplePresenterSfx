package contents

import java.net.URL
import java.util.ResourceBundle

import com.bugworm.sfx.simplepresenter.PageController

import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.Label

class Duration extends PageController with Initializable {

    @FXML
    var duration1 : Label = _

    @FXML
    var duration2 : Label = _

    @FXML
    var duration3 : Label = _

    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
        duration1.setOpacity(0.0)
        duration2.setOpacity(0.0)
        duration3.setOpacity(0.0)
    }

    override def doAction(fw: => Unit): Unit = {
        duration1.getOpacity match {
            case 0.0 => {
                duration1.setOpacity(1.0)
                duration2.setOpacity(1.0)
                duration3.setOpacity(1.0)
            }
            case _ => fw
        }
    }
}