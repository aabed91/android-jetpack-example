package com.firstcode.interviewapp.util

import android.app.Activity
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.firstcode.interviewapp.R

class DialogUtils {

    companion object{

        fun showMessageDialog(activity: Activity, message: String){
            val alert = AlertDialog.Builder(activity)

            alert.setMessage(message)

            alert.setPositiveButton(R.string.ok){
                dialogInterface, i ->
                dialogInterface.dismiss()
            }

            alert.create().show()
        }

        fun showMessageDialog(activity: Activity, message: String, onClickListener: DialogInterface.OnClickListener){
            val alert = AlertDialog.Builder(activity)

            alert.setMessage(message)

            alert.setPositiveButton(R.string.ok,onClickListener)

            alert.create().show()
        }

        fun showErrorDialog(activity: Activity){
            val alert = AlertDialog.Builder(activity)

            alert.setMessage(R.string.err_intenet_connection)

            alert.setPositiveButton(R.string.ok){
                    dialogInterface, i ->
                dialogInterface.dismiss()
            }

            alert.create().show()
        }


    }
}