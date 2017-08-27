package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.realm.Realm
import me.hyemdooly.sangs.dimigo.app.project.R
import me.hyemdooly.sangs.dimigo.app.project.adapter.RecyclerAdapter
import me.hyemdooly.sangs.dimigo.app.project.database.Achieve
import me.hyemdooly.sangs.dimigo.app.project.model.Item


class AchieveFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val realm: Realm = Realm.getDefaultInstance()
        val user = context.getSharedPreferences("User", Context.MODE_PRIVATE)

        var rootView :View = inflater!!.inflate(R.layout.fragment_achieve, container, false)

        var recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_view)

        var layoutManager: LinearLayoutManager = LinearLayoutManager(rootView.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        var firstAchieve: Achieve? = realm.where(Achieve::class.java).equalTo("categoryId", 1)
                                                    .equalTo("state",false).findFirst()
        var secondAchieve: Achieve? = realm.where(Achieve::class.java).equalTo("categoryId", 2)
                .equalTo("state",false).findFirst()
        var thirdAchieve: Achieve? = realm.where(Achieve::class.java).equalTo("categoryId", 3)
                .equalTo("state",false).findFirst()

        var items: List<Item> = listOf(Item("시간", firstAchieve!!.title.toString(),
                user.getLong("TotalTime", 0).div(60).toString()+"분/"+
                        firstAchieve!!.purpose!!.times(60).toString()+"분"),
                Item("레벨", secondAchieve!!.title.toString(),
                        user.getLong("TotalTime", 0).div(60).toString()+"분/"+secondAchieve!!.purpose!!.times(60).toString()+"분"),
                Item("앱 사용 제약조건", thirdAchieve!!.title.toString(), thirdAchieve!!.purpose.toString()))

        recyclerView.adapter = RecyclerAdapter(context, items)
        return rootView
    }
}
