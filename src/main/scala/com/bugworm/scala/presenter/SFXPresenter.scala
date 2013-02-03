package com.bugworm.scala.presenter

import com.bugworm.sfx.application._
import scalafx.scene._
import scalafx.scene.layout.StackPane
import scalafx.stage._
import scalafx.scene.input._
import scalafx.Includes._
import javafx.fxml.FXMLLoader

/**
 * デフォルト起動用object
 */
object SFXPresenter extends SFXAppMain(classOf[SFXPresenter])

/**
 * ScalaFXを使ったプレゼンテーション実行クラス
 */
class SFXPresenter extends SFXApplication {

    /** ルートとして使うStackPane */
    val rootpane = new StackPane

    /** 表示するインデックス */
    var index = 0;

    /** 現在のコントローラ */
    var controller : PageController = _

    /**
     * 使用するページのURL文字列をArrayとして返す。
     * 
     * デフォルトでは TODO
     * 
     * @return ページのURL文字列Array
     */
    def pages() : Array[String] = {
        //TODO default : load "/contents/pages.txt"
        Array("/contents/sample1.fxml", "/contents/sample2.fxml", "/contents/sample3.fxml")
    }

    /**
     * ページを移動する。
     * 
     * @param d 移動するページ数(負なら戻る)
     */
    def movePage(d : Int){
        if(pages.isDefinedAt(index + d)){
            controller.dispose()
            index += d
            loadCurrentPage()
        }
    }

    /**
     * 現在のインデックスで新しいページをロードしてrootpaneの子としてaddする。
     */
    def loadCurrentPage(){

        val url = getClass.getResource(pages()(index))
        val loader = new FXMLLoader(url)
        val next = loader.load match {
            case x : javafx.scene.Node => x
            case _ => throw new ClassCastException
        }
        val lcon = loader.getController[PageController]
        controller = Option(lcon).getOrElse(new DefaultController)
        controller.sfxPresenter = this
        rootpane.children.add(next)
        controller.target = next
        rootpane.onMouseClicked = {event : MouseEvent =>
            controller.actionNumber += 1
            controller.action()
        }
    }

    new Stage{
        initStyle(StageStyle.TRANSPARENT)
        scene = new Scene(rootpane){
            fill = null
            onKeyPressed = {event : KeyEvent =>
		        event.code match{
			        case KeyCode.LEFT => movePage(-1)
			        case KeyCode.RIGHT => movePage(1)
			        case KeyCode.Q | KeyCode.ESCAPE => close
			        case _ =>
		        }
            }
        }
    }
    loadCurrentPage()
}