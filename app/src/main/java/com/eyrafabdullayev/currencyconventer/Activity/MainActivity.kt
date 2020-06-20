package com.eyrafabdullayev.currencyconventer.Activity

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eyrafabdullayev.currencyconventer.Model.Currency
import com.eyrafabdullayev.currencyconventer.Adapter.CurrencyAdapter
import com.eyrafabdullayev.currencyconventer.R
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    lateinit var azn: String
    lateinit var turkishLira: String
    lateinit var usd: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //get all rates
        getRates()
    }

    fun getRates(){
        var download = Download()

        try {

            val url = "http://data.fixer.io/api/latest?access_key=a39b8fb7999cc47666e55f4dd2a2f440"
            download.execute(url)

        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    inner class Download : AsyncTask<String, Void, String>() {

        override fun doInBackground(vararg params: String?): String {
            var result = ""

            var url: URL
            var httpURLConnection: HttpURLConnection

            try {

                url = URL(params[0])
                httpURLConnection = url.openConnection() as HttpURLConnection
                val inputStream = httpURLConnection.inputStream
                val inputStreamReader = InputStreamReader(inputStream)

                var data = inputStreamReader.read()

                while(data > 0){
                    val character = data.toChar()
                    result += character

                    data = inputStreamReader.read()
                }
            } catch (e: Exception){
                e.printStackTrace()
            } finally {
                return result
            }
        }

        override fun onPostExecute(result: String?) {
            try {

                val resultJSON = JSONObject(result)
                //base
                val base = resultJSON.getString("base")
                //date
                val date = resultJSON.getString("date");
                dateText.setText(date)

                //rates
                val rates = resultJSON.getString("rates")
                val ratesJSON = JSONObject(rates);

                azn = String.format("%.02f",ratesJSON.getString("AZN").toFloat())
                usd = String.format("%.02f",ratesJSON.getString("USD").toFloat())
                turkishLira = String.format("%.02f",ratesJSON.getString("TRY").toFloat())

                setAdapter()
            } catch (e:Exception){
                e.printStackTrace()
            }
        }
    }

    fun setAdapter(){
        val AZN = Currency(
            "Azerbaijani Manat",
            "AZN",
            azn,
            R.drawable.aze
        )

        val USD = Currency(
            "United States Dollar",
            "USD",
            usd,
            R.drawable.usa
        )


        val TRY = Currency(
            "Turkish Lira",
            "TRY",
            turkishLira,
            R.drawable.tr
        )

        val currencyList = arrayListOf<Currency>()
        currencyList.add(AZN)
        currencyList.add(USD)
        currencyList.add(TRY)

        val currencyAdapter =
            CurrencyAdapter(
                currencyList, this@MainActivity
            )

        listView.adapter = currencyAdapter
    }
}
