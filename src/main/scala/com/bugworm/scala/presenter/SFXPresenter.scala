package com.bugworm.scala.presenter

import com.bugworm.sfx.application._
import scalafx.scene._
import scalafx.scene.layout.StackPane
import scalafx.stage._
import scalafx.scene.input._
import scalafx.Includes._

object SFXPresenter extends SFXAppMain(classOf[SFXPresenter])

class SFXPresenter extends SFXApplication {

    val rootpane = new StackPane

    var index = 0;

    def pages() : Array[String] = {
        //TODO default : load "/contents/pages.txt"
        Array()
    }

    val stage = new Stage{
        initStyle(StageStyle.TRANSPARENT)
        scene = new Scene(rootpane, 1024, 768){
            fill = null
            onKeyPressed = {e : KeyEvent => typed(e, rootpane)}
        }
    }

    def typed(event : KeyEvent, root : StackPane) : Unit = {
        event.code match{
//	        case KeyCode.LEFT => prePage(root)
//	        case KeyCode.RIGHT => postPage(root)
	        case KeyCode.Q => stage.close
	        case _ =>
        }
    }
}