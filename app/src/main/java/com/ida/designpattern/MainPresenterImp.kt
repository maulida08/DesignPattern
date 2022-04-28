package com.ida.designpattern

import android.text.TextUtils

class MainPresenterImp(private val view: MainView) : MainPresenter {
    private val HASIL = mutableListOf<Hasil>()
    override fun tambahHasil(number1: String, number2: String) {
        if (TextUtils.isEmpty(number1) || TextUtils.isEmpty(number2)){
            view.showMessage("Cannot be empty")
        } else {
            val jumlah = number1.toInt() + number2.toInt()
            HASIL.run {
                add(Hasil(jumlah))
            }
            view.showMessage("Results : $jumlah")
            view.clearField()
        }
    }

    override fun loadHasil() {
        if (HASIL.size == 0){
            view.showMessage("Blank Data")
        } else {
            var allData = ""

            for (i in 0 until HASIL.size){
                allData += "Result : " + HASIL[i].hasilHitung + "\n\n"
            }

            allData += "Total : " + HASIL.size

            view.showData(allData)
        }
    }

    override fun clearHasil() {
        if (HASIL.size != 0){
            HASIL.clear()
            view.showMessage("Successfully Deleted")
        } else {
            view.showMessage("Data is empty")
        }
    }
}