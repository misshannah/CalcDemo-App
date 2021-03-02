package com.olukoye.hannah.calcdemoapp

import android.app.IntentService
import android.content.Intent
import android.os.IBinder
import android.os.RemoteException
import android.util.Log

class MyCalcService : IntentService("MyCalcService") {
    private val TAG = "CalcService"

    override fun onBind(intent: Intent): IBinder {
        // Return the interface
        return binder
    }


    private val binder = object: ICalcService.Stub() {
        @Throws(RemoteException::class)
        override fun Add(a: Int, b: Int): Int {
            return a + b
        }

        override fun Divide(a: Int, b: Int): Int {
                return a / b
            }

        override fun Subtract(a: Int, b: Int): Int {
                return a - b
            }

        override fun Multiply(a: Int, b: Int): Int {
                return a * b
            }
    }

    override fun onHandleIntent(p0: Intent?) {
        TODO("Not yet implemented")
    }

}
