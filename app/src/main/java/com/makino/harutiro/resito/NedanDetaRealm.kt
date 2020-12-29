package com.makino.harutiro.resito

import io.realm.RealmObject

open class NedanDateRealm ( //メモのクラスを定義 //openを書くのはRealmを使う際に必要

    //保存するデータの要素となる変数を定義する

    open var hizuke_Re:String = "0",
    open var nedan_Re:Int = 0,
    open var saihu_Re:Int = 0,
    open var saihuIcon_Re:Int = 0,
    open var zyanruIcon_Re:Int = 0

): RealmObject() //RealmObjectという方を継承している部分 メモというクラスをRealmで保存できる型にすることができる