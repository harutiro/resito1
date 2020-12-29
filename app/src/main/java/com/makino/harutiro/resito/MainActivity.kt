package com.makino.harutiro.resito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.realm.Realm
import java.time.LocalDate
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    val realm :Realm = Realm.getDefaultInstance()
    var namberid = 2


    var nedanDateView: List <NedanDateRecycleView> = listOf(
        NedanDateRecycleView("20xx-aa-bb",1,R.drawable.image1,R.drawable.image2,1),
        NedanDateRecycleView("20xx-aa-bb",1,R.drawable.image1,R.drawable.image2,1),
        NedanDateRecycleView("20xx-aa-bb",1,R.drawable.image1,R.drawable.image2,1)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*RecycleView Start*/

        val adapter = RecyclerViewAdapter(this)

        val recycleView = findViewById<RecyclerView>(R.id.nedanRV)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

        //リサイクラービューアダプターで宣言したaddAllメソッドを呼んであげてデータも渡している
        adapter.addAll(nedanDateView)

        /*RecycleView End*/


        /*Realm Start*/

        //read()というメソッドを使ってすでに保存されているメモのデータを取得してmemoという変数に代入
        val nedanDateRealmInstans_M:NedanDateRealm? = read()

        //データベースから取得したMemoをテキストに表示する処理
        //nullが保存されるとえらるので分岐処理をする
        if(nedanDateRealmInstans_M != null){
            titleEditText.setText(nedanDateRealmInstans_M.title)
            contentEditText.setText(nedanDateRealmInstans_M.content)
        }

        saveButton.setOnClickListener {
            val title:String = titleEditText.text.toString()
            val content:String = contentEditText.text.toString()
            //保存ボタンを押したときに、titleTextとcontentEditTextに入力されたテキストを取得しsave() メソッドに値を渡す
            save(title,content)
        }

        /*Realm End*/


    }

    //画面（Activity）が終了したときに実行される部分
    override fun onDestroy() {
        super.onDestroy()

        /*Realm strat*/

        //画面終了時にRealmを閉じる
        //パフォーマンスを良くするためにやる
        realm.close()

        /*Realm End*/

    }


    /*Realm Start*/

    //画面を起動したときにすでに保存されているnedanDateのデータを取得する関数
    //メソッド名（read）と返り値の型を指定（nedanDate? ）]
    //返り値の？はnullが帰ってくる可能性があるから
    fun read(): NedanDateRealm? {

        //realmを使ってデータベース中のMemoリストから最初のデータを一つ取り出している
        //※findFirst()の他にfindAll()というリストを取得する機能もある
        return realm.where(NedanDateRealm::class.java).findFirst()
    }

    fun save(title :String,content :String){

        //すでに保存されているメモを取得
        // すでに保存されたデータがあればそのデータの更新。なければ新しくデータを作成するようにする
        val nedanDateRealmInstans_S :NedanDateRealm? = read()

        //データベースへの書き込み（データの作成、更新、消去.etc）
        realm.executeTransaction{

            //データの存在によって、更新処理もしくは新規作成の処理をする
            if(nedanDateRealmInstans_S != null) {
                //memoの更新をしている
                nedanDateRealmInstans_S.title = title
                nedanDateRealmInstans_S.content = content
            }else{
                //メモの新規作成
                //保存するデータの作成「it.createObuject(データ型::class.java)」
                //データベースに新しいオブジェクトを作って保存する

                //作ったオブジェクトをnewMemoという変数に代入してtitleとcontentを更新する
                //realm.executeTransaction{}ないだとitをrealmの変数として扱う→高階関数
                val newNedanDate :NedanDateRealm = it.createObject(NedanDateRealm::class.java)
                newNedanDate.title = title
                newNedanDate.content = content
            }

            //スナックバーの表示
            Snackbar.make(container,"保存しました！！",Snackbar.LENGTH_SHORT).show()

        }
    }
    /*Realm End*/

}
