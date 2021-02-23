package com.olukoye.hannah.calcdemoapp

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.lang.String


class MainActivity : AppCompatActivity(), ServiceConnection {

    var value1 = 0
    var value2 = 0
    var service: ICalcService? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var add_btn: Button? = findViewById(R.id.add)
        var divide_btn: Button? = findViewById(R.id.divide)
        var subtract_btn: Button? = findViewById(R.id.subtract)
        var multiply_btn: Button? = findViewById(R.id.multiply)
        var value1_txt: EditText? = findViewById(R.id.value1)
        var value2_txt: EditText? = findViewById(R.id.value2)
        var result_txt: EditText? = findViewById(R.id.result)

            add_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value1_txt != null) {
                    value1 = value1_txt.getText().toString().toInt()
                    value2 = value2_txt?.getText().toString().toInt()
                    try {
                        result_txt?.setText(String.valueOf(+service!!.Add(value1, value2)))
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })

            divide_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value1_txt != null) {
                    value1 = value1_txt?.getText().toString().toInt()
                    value2 = value2_txt?.getText().toString().toInt()
                    try {
                        result_txt?.setText(String.valueOf(service!!.Divide(value1, value2)))
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })

            subtract_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value1_txt != null) {
                    value1 = value1_txt.getText().toString().toInt()
                    value2 = value2_txt?.getText().toString().toInt()
                    try {
                        result_txt?.setText(
                            String.valueOf(
                                service!!.Subtract(
                                    value1,
                                    value2
                                )
                            )
                        )
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })

            multiply_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value1_txt != null) {
                    value1 = value1_txt?.getText().toString().toInt()
                    value2 = value2_txt?.getText().toString().toInt()
                    try {
                        result_txt?.setText(
                            String.valueOf(
                                service!!.Multiply(
                                    value1,
                                    value2
                                )
                            )
                        )
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun onStart() {
        super.onStart()
        bindService(
            Intent("com.olukoye.hannah.calcdemoapp.MyCalcService").setPackage("com.olukoye.hannah.calcdemoapp"),
            this@MainActivity,
            Context.BIND_AUTO_CREATE
        )
    }

    override fun onServiceConnected(
        componentName: ComponentName?,
        iBinder: IBinder?
    ) {
        service = ICalcService.Stub.asInterface(iBinder)
    }

    override fun onServiceDisconnected(componentName: ComponentName?) {
        service = null
    }
}