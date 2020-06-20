package com.eyrafabdullayev.currencyconventer.Model

import java.io.Serializable

class Currency : Serializable{

    private lateinit var name: String
    private lateinit var shortName: String
    private lateinit var rate: String
    private var flagId: Int = 0

    constructor(name: String,shortName: String,rate: String,flagId: Int){
        this.name = name
        this.shortName = shortName
        this.rate = rate
        this.flagId = flagId
    }

    fun getN(): String{
        return name
    }

    fun getSN(): String{
        return shortName
    }

    fun getR(): String{
        return rate
    }

    fun getF(): Int{
        return flagId
    }
}