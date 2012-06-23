package contents

import java.net.URL
import java.util.ResourceBundle
import com.bugworm.sfx.simplepresenter.PageController
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.text.Text
import javafx.util.Duration
import scalafx.Includes._
import scalafx.animation.TranslateTransition.sfxTranslateTransition2jfx
import scalafx.animation.TranslateTransition
import com.bugworm.sfx.simplepresenter.SimplePresenter

class Page2 extends PageController with Initializable {

    @FXML
    var p0 : ImageView = _

    @FXML
    var p1 : Text = _

    override def initialize(url : URL, rb : ResourceBundle) : Unit = {
        p1.setOpacity(0.0)
    }

    override def doAction(fw : => Unit) : Unit = {

        p1.getOpacity match {
            case 0.0 =>
                p1.setOpacity(1.0)
                new TranslateTransition{
                    duration = new Duration(1000L)
                    node = p1
                    fromY = SimplePresenter.sceneheight
                    toY = 0.0
                }.play
            case _ => fw
        }
    }
}