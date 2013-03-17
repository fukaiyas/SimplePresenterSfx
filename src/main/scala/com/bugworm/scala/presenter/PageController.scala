package com.bugworm.scala.presenter

/**
 * ページごとのアクションを実装するための親クラス
 */
abstract class PageController {

    /** 実行クラス */
    var sfxPresenter : SFXPresenter = _

    /** 対象のノード */
    var target : javafx.scene.Node = _

    /** 現在のアクション回数 */
    var actionNumber : Int = 0

    /**
     * このメソッドにアクションを実装する。
     */
    def action() : Unit

    /**
     * このコントローラが対象としているNodeを破棄する。
     */
    def dispose(){
        sfxPresenter.rootpane.children.remove(target)
    }
}

/**
 * 単に次のページに進むデフォルトのPageController
 */
class DefaultController extends PageController{

    /**
     * 次のページに進む。
     */
    def action(){
        sfxPresenter.movePage(1)
    }
}