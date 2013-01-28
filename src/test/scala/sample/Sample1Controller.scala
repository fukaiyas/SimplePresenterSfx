package sample

import com.bugworm.scala.presenter.PageController
import javafx.fxml.Initializable
import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.FXML
import scalafx.Includes._
import scalafx.animation.Timeline

class Sample1Controller extends PageController with Initializable{

    @FXML
    var actionLabel : javafx.scene.control.Label = _

    def initialize(url : URL, rb : ResourceBundle){
        actionLabel.visible = false
    }

    def action(): Unit = {
        actionNumber match {
            case 1 =>
                actionLabel.visible = true
                actionLabel.scaleX = 10
                actionLabel.scaleY = 10
                Timeline(at (1.6 s){Set(
                    actionLabel.scaleX -> 1,
                    actionLabel.scaleY -> 1,
                    actionLabel.rotate -> 720
                )}).play()
            case _ => sfxPresenter.movePage(1)
        }
    }
}