package com.bugworm.scala.presenter
import javafx.fxml.FXML
import javafx.scene.Node

/**
 * ページごとのアクションを実装するための親クラス
 */
abstract class PageController {

    /** 実行クラス */
    var sfxPresenter : SFXPresenter = _

    /** 現在のアクション回数 */
    var actionNumber : Int = 0

    /**
     * このメソッドにアクションを実装する
     */
    def action() : Unit
}
