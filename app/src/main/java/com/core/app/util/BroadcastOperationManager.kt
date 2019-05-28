package com.core.app.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class BroadcastOperationManager(val activity: AppCompatActivity) {

    companion object {
        const val OPERATION_ACTION = ".ACTIVITY_OPERATION"
        const val OPERATION = "operation"
        const val ACTIVITY_NAME = "name"
    }

    enum class Operation {
        FINISH, FINISH_ALL
    }

    fun send(operation: Operation) {
        val intent = Intent(activity.packageName + OPERATION_ACTION)
        when (operation) {
            Operation.FINISH_ALL -> intent.putExtra(OPERATION, Operation.FINISH_ALL.ordinal)
            Operation.FINISH -> {
//                TODO
            }
        }
        activity.sendBroadcast(intent)
    }

    fun registerReceiver() {
        activity.registerReceiver(mOperationReceiver, IntentFilter(activity.packageName + OPERATION_ACTION))
    }

    fun unregisterReceiver() {
        activity.unregisterReceiver(mOperationReceiver)
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
                        if (activityToFinish == this.javaClass.name) activity.finish()
                    }
                }
                Operation.FINISH_ALL -> activity.finish()
            }
        }
    }

}