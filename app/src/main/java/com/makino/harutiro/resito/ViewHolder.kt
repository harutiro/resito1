package com.makino.harutiro.resito

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val context: Context):
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    //リサイクラービューに表示するリストを宣言する
    val items: MutableList<NedanDateRecycleView> = mutableListOf()

    //データをcourseDateと結びつける？？
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val hizuke_V: TextView = view.findViewById(R.id.hizukeId)
        val nedan_V: TextView = view.findViewById(R.id.nedanId)
        val saihu_V: TextView = view.findViewById(R.id.saihuId)
        val saihuIcon_V: ImageView = view.findViewById(R.id.saihuIconId)
        val zyanru_V: ImageView = view.findViewById(R.id.zyanruIconId)

    }

    //はめ込むものを指定
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_nedan_date_call,parent,false)
        return ViewHolder(view)
    }

    //itemsのposition番目の要素をviewに表示するコード
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.saihuIcon_V.setImageResource(item.saihuIcon)
        holder.zyanru_V.setImageResource(item.zyanruIcon)
        holder.hizuke_V.text = item.hizuke.toString()
        holder.nedan_V.text = item.nedan.toString()
        holder.saihu_V.text = item.saihu.toString()

    }

    //引数にとったリストをadapterに追加するメソッド
    fun addAll(items: List<NedanDateRecycleView>){
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    //リストの要素数を返すメソッド
    override fun getItemCount(): Int {

        return items.size
    }
}