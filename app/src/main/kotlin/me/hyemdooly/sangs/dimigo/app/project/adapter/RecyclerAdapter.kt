package me.hyemdooly.sangs.dimigo.app.project.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.model.Item

class RecyclerAdapter(context: Context, items: List<Item>) : RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    var context: Context = context
    var items: List<Item> = items
    var iconPalette: List<Int> = listOf(R.drawable.ic_watch, R.drawable.ic_level, R.drawable.ic_restricted_area)
    var palette: List<String> = listOf("#7ADBD1", "#78B2D9","#9070D9")


    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        val item: Item = items[position]

        with(holder!!) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                titleLayout.backgroundTintList = ColorStateList.valueOf(Color.parseColor(palette[position%3]))
            }
            icon.setImageResource(iconPalette[position])
            titleText.text = item.titleText
            purposeText.text = item.purposeText
            counterText.text = item.counterText

            if(position == 2) {
                blockContainer.visibility = View.VISIBLE
            } else {
                blockContainer.visibility = View.INVISIBLE
            }
        }
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
        var icon: ImageView = itemView.findViewById(R.id.icon)
        var blockContainer: RelativeLayout = itemView.findViewById(R.id.item_cardview_block_achieve)
    }

}