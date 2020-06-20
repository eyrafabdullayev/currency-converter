package com.eyrafabdullayev.currencyconventer.Adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eyrafabdullayev.currencyconventer.Model.Currency
import com.eyrafabdullayev.currencyconventer.R

class CurrencyAdapter : ArrayAdapter<Currency> {

    private var currencyList: ArrayList<Currency>? = null
    private var context: Activity? = null

    constructor(currencyList: ArrayList<Currency>, context: Activity) : super(
        context,
        R.layout.currency_layout,
        currencyList
    ) {
        this.currencyList = currencyList
        this.context = context
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val layoutInflater = context?.layoutInflater
        var currencyView: View? = layoutInflater?.inflate(R.layout.currency_layout, null, true)

        val flagView: ImageView? = currencyView?.findViewById(R.id.flag)
        val nameView: TextView? = currencyView?.findViewById(R.id.name)
        val shortNameView: TextView? = currencyView?.findViewById(R.id.shortName)
        val rateView: TextView? = currencyView?.findViewById(R.id.rateText)

        val bitmap: Bitmap? = currencyList?.get(position)?.getF()?.let {
            BitmapFactory.decodeResource(
                context?.resources,
                it
            )
        }

        flagView?.setImageBitmap(bitmap)
        nameView?.setText(currencyList?.get(position)?.getN())
        shortNameView?.setText(currencyList?.get(position)?.getSN())
        rateView?.setText(currencyList?.get(position)?.getR())

        return currencyView!!
    }
}