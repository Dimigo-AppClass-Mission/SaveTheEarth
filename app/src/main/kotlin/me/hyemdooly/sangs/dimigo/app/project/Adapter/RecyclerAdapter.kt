package me.hyemdooly.sangs.dimigo.app.project.Adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import me.hyemdooly.sangs.dimigo.app.project.R

/**
 * Created by songhyemin on 2017. 8. 22..
 */
class RecyclerAdapter(context: Context, items: List<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var context: Context = context
    var items: List<Item> = items
    var palette: List<String> = listOf("#7ADBD1", "#78B2D9","#9070D9")

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        var item: Item = items[position]
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            holder?.titleLayout?.backgroundTintList = ColorStateList.valueOf(Color.parseColor(palette[position%3]))
        }
        holder?.titleText?.text = item.titleText
        holder?.purposeText?.text = item.purposeText
        holder?.counterText?.text = (item.counterText/60000).toString()+"분"


    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder{
        var view: View = LayoutInflater.from(parent?.context).inflate(R.layout.item_cardview, null)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var titleLayout: RelativeLayout = itemView.findViewById(R.id.layout_title)
        var titleText: TextView = itemView.findViewById(R.id.card_title)
        var purposeText: TextView = itemView.findViewById(R.id.card_purpose)
        var counterText: TextView = itemView.findViewById(R.id.card_counter)

    }

}