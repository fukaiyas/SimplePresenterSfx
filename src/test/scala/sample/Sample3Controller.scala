package sample

import com.bugworm.scala.presenter.DefaultController
import javafx.scene.control.Control
import javafx.scene.input.MouseEvent
import scalafx.animation._
import scalafx.scene.effect._
import scalafx.util.Duration
import scalafx.Includes._

class Sample3Controller extends DefaultController{

    var fade : FadeTransition = _

    var rotate : RotateTransition = _

    def setBloom(event : MouseEvent){
        applyControl(event, _.setEffect(new Bloom))
    }

    def removeBloom(event : MouseEvent){
        applyControl(event, _.setEffect(null))
    }

    def startFadeOut(event : MouseEvent){
        applyControl(event, control =>
            fade = new FadeTransition{
	            duration = Duration(1000)
	            node = control
	            fromValue = 1.0
	            toValue = 0.0
            }
        )
        fade.play()
    }

    def stopFadeOut(event : MouseEvent){
        fade.stop()
        applyControl(event, _.setOpacity(1.0))
    }

    def startRotate(event : MouseEvent){
        applyControl(event, control =>
	        rotate = new RotateTransition{
	            duration = Duration(1000)
	            cycleCount = Animation.INDEFINITE
	            node = control
	            fromAngle = 0
	            toAngle = 360
	        }
        )
        rotate.play()
    }

    def stopRotate(event : MouseEvent){
        rotate.stop()
        applyControl(event, _.setRotate(0))
    }

    def applyControl(event : MouseEvent, action : Control => Unit){
        event.getSource match{
            case control : Control => action(control)
            case _ =>
        }
    }
}
