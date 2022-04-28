package com.ida.designpattern

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.ida.designpattern.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), MainView {
    private lateinit var presenterImp: MainPresenterImp
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenterImp = MainPresenterImp(this)

        binding.btnSubmit.setOnClickListener {
            presenterImp.tambahHasil(
                binding.etNumber1.text.toString(),
                binding.etNumber2.text.toString()
            )
        }

        binding.btnRiwayat.setOnClickListener {
            presenterImp.loadHasil()
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showData(data: String) {
        AlertDialog
            .Builder(this)
            .setNegativeButton("Close"){dialog, _ ->
                dialog.dismiss()
            }
            .setNeutralButton("Clear Data"){dialog, _ ->
                AlertDialog.Builder(this)
                    .setPositiveButton("Yes"){dialog, _ ->
                        presenterImp.clearHasil()
                        dialog.dismiss()
                    }
                    .setNegativeButton("No"){dialog, _ ->
                        dialog.dismiss()
                    }
                    .setTitle("Confirmation")
                    .setMessage("Are you sure want to delete history?")
                    .create()
                    .show()

                dialog.dismiss()
            }
            .setTitle("History")
            .setMessage(data)
            .create()
            .show()
    }

    override fun clearField() {
        binding.etNumber1.text!!.clear()
        binding.etNumber2.text!!.clear()
    }
}