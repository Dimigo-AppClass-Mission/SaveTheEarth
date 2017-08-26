package me.hyemdooly.sangs.dimigo.app.project.fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatSpinner
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import lecho.lib.hellocharts.view.LineChartView

import me.hyemdooly.sangs.dimigo.app.project.R
import android.R.attr.data
import lecho.lib.hellocharts.model.*


class StatsFragment : Fragment() {
    private var rootView: View? = null

    private var dateFilter: AppCompatSpinner? = null
    private var timeUsedFilter: AppCompatSpinner? = null
    private var chart: LineChartView? = null

    private var dateFilterListObj: ArrayList<String> = ArrayList()
    private var timeUsedFilterListObj: ArrayList<String> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        rootView = inflater!!.inflate(R.layout.fragment_stats, null, false)

        dateFilter = rootView!!.findViewById(R.id.stats_fragment_time_filter)
        timeUsedFilter = rootView!!.findViewById(R.id.stats_fragment_usedtime_filter)
        chart = rootView!!.findViewById(R.id.stats_fragment_chart)

        val dateFilterAdapter = ArrayAdapter<String>(activity, R.layout.item_spinner, dateFilterListObj)
        val timeUsedFilterAdapter = ArrayAdapter<String>(activity, R.layout.item_spinner, timeUsedFilterListObj)
        initDateFilter()
        initTimeUsedFilter()
        dateFilter!!.adapter = dateFilterAdapter
        timeUsedFilter!!.adapter = timeUsedFilterAdapter

        initChart()

        return rootView!!
    }

    private fun initDateFilter() {
        dateFilterListObj.add("오늘")
        dateFilterListObj.add("8월 26일")
        dateFilterListObj.add("8월 25일")
        dateFilterListObj.add("8월 24일")
    }

    private fun initTimeUsedFilter() {
        timeUsedFilterListObj.add("사용한 시간")
        timeUsedFilterListObj.add("사용안한 시간")
    }

    private fun initChart() {
        val axisXValue = ArrayList<AxisValue>()
        axisXValue.add(AxisValue(1.toFloat()).setLabel("7시"))
        axisXValue.add(AxisValue(2.toFloat()).setLabel("10시"))
        axisXValue.add(AxisValue(3.toFloat()).setLabel("13시"))
        axisXValue.add(AxisValue(4.toFloat()).setLabel("16시"))
        axisXValue.add(AxisValue(5.toFloat()).setLabel("19시"))
        axisXValue.add(AxisValue(6.toFloat()).setLabel("22시"))
        axisXValue.add(AxisValue(7.toFloat()).setLabel("~7시"))
        axisXValue.add(AxisValue(8.toFloat()).setLabel(""))

        val axisXTopValue = ArrayList<AxisValue>()
        axisXTopValue.add(AxisValue(1.toFloat()).setLabel("0분"))
        axisXTopValue.add(AxisValue(2.toFloat()).setLabel("27분"))
        axisXTopValue.add(AxisValue(3.toFloat()).setLabel("43분"))
        axisXTopValue.add(AxisValue(4.toFloat()).setLabel("47분"))
        axisXTopValue.add(AxisValue(5.toFloat()).setLabel("31분"))
        axisXTopValue.add(AxisValue(6.toFloat()).setLabel("34분"))
        axisXTopValue.add(AxisValue(7.toFloat()).setLabel("37분"))
        axisXTopValue.add(AxisValue(8.toFloat()).setLabel(""))

        val values = ArrayList<PointValue>()
        values.add(PointValue(0f, 0f))
        values.add(PointValue(1f, 8f))
        values.add(PointValue(2f, 27f))
        values.add(PointValue(3f, 43f))
        values.add(PointValue(4f, 47f))
        values.add(PointValue(5f, 31f))
        values.add(PointValue(6f, 34f))
        values.add(PointValue(7f, 37f))
        values.add(PointValue(8f, 50f))

        val compareValues = ArrayList<PointValue>()
        compareValues.add(PointValue(0f, 0f))
        compareValues.add(PointValue(1f, 4f))
        compareValues.add(PointValue(2f, 13.5f))
        compareValues.add(PointValue(3f, 21.5f))
        compareValues.add(PointValue(4f, 23.5f))
        compareValues.add(PointValue(5f, 15.5f))
        compareValues.add(PointValue(6f, 17f))
        compareValues.add(PointValue(7f, 18.5f))
        compareValues.add(PointValue(8f, 25f))

        val line = Line(values).setColor(Color.parseColor("#29C9BD")).setCubic(true).setHasLabelsOnlyForSelected(true).setStrokeWidth(2).setPointRadius(4)
        val line2 = Line(compareValues).setColor(Color.parseColor("#C0C9CF")).setCubic(true).setHasLabelsOnlyForSelected(true).setStrokeWidth(1).setPointRadius(2)
        val lines = ArrayList<Line>()
        lines.add(line)
        lines.add(line2)

        val data = LineChartData()
        data.lines = lines
        data.axisXBottom = Axis(axisXValue).setHasLines(true).setLineColor(Color.parseColor("#4029C9BD")).setTextColor(Color.parseColor("#29C9BD"))
        data.axisXTop = Axis(axisXTopValue).setHasLines(false).setTextColor(Color.parseColor("#29C9BD"))

        chart!!.isZoomEnabled = false
        chart!!.isInteractive = true
        chart!!.lineChartData = data
    }
}