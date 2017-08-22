package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import me.hyemdooly.sangs.dimigo.app.project.model.Item
import me.hyemdooly.sangs.dimigo.app.project.adapter.RecyclerAdapter
import me.hyemdooly.sangs.dimigo.app.project.R


class AchieveFragment : Fragment() {

    private var mListener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        var rootView :View = inflater!!.inflate(R.layout.fragment_achieve, container, false)

        var recyclerView: RecyclerView = rootView.findViewById(R.id.recycler_view)

        var layoutManager: LinearLayoutManager = LinearLayoutManager(rootView.context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        var items: List<Item> = listOf(Item("시간", "1시간동안 휴대폰 사용하지 않기", 600000),
                Item("레벨", "레벨2 달성하기", 550000),
                Item("앱 사용 제약조건", "오늘 사용하는 앱 10개 이하로 사용하기", 400000))

        recyclerView.adapter = RecyclerAdapter(context, items)
        return rootView
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(context!!.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
