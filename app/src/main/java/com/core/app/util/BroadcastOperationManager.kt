package com.core.app.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import com.raxdenstudios.commons.util.Utils
import java.util.*

class BroadcastOperationManager(val mActivity: AppCompatActivity) {

    companion object {
        const val OPERATION_ACTION = ".ACTIVITY_OPERATION"
        const val OPERATION = "operation"
        const val ACTIVITY_NAME = "name"
    }

    enum class Operation {
        FINISH, FINISH_ALL
    }

    fun sendOperation(operation: Operation) {
        val intent = Intent(Utils.getPackageName(mActivity) + OPERATION_ACTION)
        when (operation) {
            Operation.FINISH_ALL -> intent.putExtra(OPERATION, Operation.FINISH_ALL.ordinal)
            Operation.FINISH -> {
//                TODO
            }
        }
        mActivity.sendBroadcast(intent)
    }

    fun registerReceiver() {
        mActivity.registerReceiver(mOperationReceiver, IntentFilter(Utils.getPackageName(mActivity) + OPERATION_ACTION))
    }

    fun unregisterReceiver() {
        mActivity.unregisterReceiver(mOperationReceiver)
    }

    private val mOperationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (!intent.hasExtra(OPERATION)) return
            when (Operation.values()[intent.getIntExtra(OPERATION, 0)]) {
                Operation.FINISH -> if (intent.hasExtra(ACTIVITY_NAME)) {
                    var activitiesToFinish: MutableList<String> = ArrayList()
                    val activityName = intent.getStringExtra(ACTIVITY_NAME)
                    if (activityName.contains(",")) {
                        activitiesToFinish = Arrays.asList(*activityName.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())
                    } else {
                        activitiesToFinish.add(activityName)
                    }
                    for (activityToFinish in activitiesToFinish) {
                        if (activityToFinish == this.javaClass.name) mActivity.finish()
                    }
                }
                Operation.FINISH_ALL -> mActivity.finish()
            }
        }
    }

}