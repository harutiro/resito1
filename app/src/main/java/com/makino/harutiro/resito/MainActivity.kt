package com.makino.harutiro.resito

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    var NedanDate: List <nedanDate> = listof(
        nedanDate(1,1,1,1,1),
        nedanDate(1,1,1,1,1),
        nedanDate(1,1,1,1,1)

    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = RecyclerViewAdapter(this)

        val recycleView = findViewById<RecyclerView>(R.id.nedanRV)
        recycleView.layoutManager = LinearLayoutManager(this)
        recycleView.adapter = adapter

//リサイクラービューアダプターで宣言したaddAllメソッドを呼んであげてデータも渡している
        adapter.addAll(NedanDate)


    }
}