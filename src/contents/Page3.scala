package contents

import java.net.URL
import java.util.ResourceBundle
import com.bugworm.sfx.simplepresenter.PageController
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.image.ImageView
import javafx.scene.layout.AnchorPane
import javafx.scene.text.Text
import javafx.util.Duration
import scalafx.Includes._
import scalafx.animation.FadeTransition.sfxFadeTransition2jfx
import scalafx.animation.FadeTransition
import scalafx.animation.SequentialTransition.sfxSequentialTransition2jfx
import scalafx.animation.SequentialTransition
import scalafx.animation.FillTransition
import scalafx.scene.shape.Rectangle
import com.bugworm.sfx.simplepresenter.SimplePresenter
import javafx.scene.paint.Color
import javafx.scene.Node

class Page3 extends PageController with Initializable {

    @FXML
    var pane : AnchorPane = _

    @FXML
    var p0 : ImageView = _

    @FXML
    var p1 : Text = _

    @FXML
    var p2 : Text = _

    var index = 0

    override def initialize(url: URL, rb: ResourceBundle): Unit = {

        p1.setOpacity(0.0)
        p2.setOpacity(0.0)
    }

    override def doAction(fw : => Unit): Unit = {

        def fade(n : Node) : Unit = {
            new FadeTransition{
                duration = new Duration(1000L)
                node = n
                toValue = 1.0
            }.play
            index += 1
        }
        index match {
            case 0 => fade(p1)

            case 1 => fade(p2)

//            case 2 =>
//                val rect = new Rectangle{
//        	        x = 0.0
//        	        y = 0.0
//        	        width = SimplePresenter.scenewidth
//        	        height = SimplePresenter.sceneheight
//        	        fill = null
//        	    }
//                pane.getChildren.add(rect)
//
//                new SequentialTransition{
//                    children = Seq(
//                    	new FillTransition{
//                    	    duration = new Duration(5000L)
//                    	    shape = rect
//                    	    fromValue = Color.rgb(0x00, 0x00, 0x00, 0.0)
//                    	    toValue = Color.web("#000033")
//                    	},
//                    	new FadeTransition{
//                    	    duration = new Duration(1000L)
//                    	    node = pane
//                    	    toValue = 0.1
//                    	}
//                    )
//                }.play
//                index += 1

            case _ => fw
        }
    }
}