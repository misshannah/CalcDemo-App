package com.olukoye.hannah.calcdemoapp

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.os.RemoteException
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var value1: Int = 0
    var value2: Int  = 0
    var service: ICalcService? = null

    private val connection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            service = ICalcService.Stub.asInterface(iBinder)

        }

        override fun onServiceDisconnected(componentName: ComponentName) {}
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val add_btn: Button? = findViewById(R.id.add)
        val divide_btn: Button? = findViewById(R.id.divide)
        val subtract_btn: Button? = findViewById(R.id.subtract)
        val multiply_btn: Button? = findViewById(R.id.multiply)
        val value1_txt: EditText? = findViewById(R.id.value1)
        val value2_txt: EditText? = findViewById(R.id.value2)
        val result_txt: EditText? = findViewById(R.id.result)

            add_btn?.setOnClickListener {
                if (!TextUtils.isEmpty(value1_txt?.text) && !TextUtils.isEmpty(value2_txt?.text)) {
                    value1 = value1_txt?.text.toString().toInt()
                    value2 = value2_txt?.text.toString().toInt()
                    try {
                        result_txt?.setText(service?.Add(value1, value2).toString())
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            }

        divide_btn?.setOnClickListener {
                if (value1_txt != null && value2_txt != null) {
                    value1 = value1_txt.getText().toString().toInt()
                    value2 = value2_txt.getText().toString().toInt()
                    try {
                        result_txt?.setText(service?.Divide(value1, value2).toString())
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            }

            subtract_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value2_txt != null) {
                    value1 = value1_txt.getText().toString().toInt()
                    value2 = value2_txt.getText().toString().toInt()
                    try {
                        result_txt?.setText(

                            service?.Subtract(
                                value1,
                                value2
                            ).toString()
                        )
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })

            multiply_btn?.setOnClickListener(View.OnClickListener {
                if (value1_txt != null && value2_txt != null) {
                    value1 = value1_txt.getText().toString().toInt()
                    value2 = value2_txt.getText().toString().toInt()
                    try {
                        result_txt?.setText(
                            service?.Multiply(
                                value1,
                                value2
                            ).toString()
                        )
                    } catch (e: RemoteException) {
                        e.printStackTrace()
                    }
                }
            })
    }

    override fun onStart() {
        super.onStart()
        val intent = Intent(this, MyCalcService::class.java)
        bindService(intent, connection, BIND_AUTO_CREATE)
    }

}