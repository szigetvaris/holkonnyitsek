package com.example.holkonnyitsek.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.holkonnyitsek.R
import com.example.holkonnyitsek.data.WCObject
import com.example.holkonnyitsek.data.WCRating

class AddWCFragment : DialogFragment() {
    private lateinit var nameEditText: EditText
    private lateinit var openingHoursEditText: EditText
    private lateinit var isFreeCheckBox: CheckBox
    private lateinit var priceEditText: EditText

    interface AddWCDialogListener {
        fun onWCCreated(newWC: WCObject)
    }

    private lateinit var listener: AddWCDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? AddWCDialogListener
            ?: throw RuntimeException("Activity must implement the AddWCDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Illemhely felvétele")
            .setView(getContentView())
            .setPositiveButton("Hozzáadás") { dialogInterface, i ->
                if (isValid()) {
                    listener.onWCCreated(getWC());
                }
            }
            .setNegativeButton("Mégse", null)
            .create()
    }

    private fun isValid() = nameEditText.text.isNotEmpty()

    private fun getWC() = WCObject(
        longitude = 0.0f,
        latitude = 0.0f,
        name = nameEditText.text.toString(),
        opening_hours = openingHoursEditText.text.toString(),
        ratings = mutableListOf<WCRating>(),
        isfree = isFreeCheckBox.isChecked,
        Price = priceEditText.text.toString(),
        id = ""
    )

    private fun getContentView(): View {
        val contentView =
            LayoutInflater.from(context).inflate(R.layout.dialog_add_wc, null)
        nameEditText = contentView.findViewById(R.id.editTxName)
        openingHoursEditText = contentView.findViewById(R.id.editTxOpeningHours)
        isFreeCheckBox = contentView.findViewById(R.id.checkBoxIsFree)
        priceEditText = contentView.findViewById(R.id.editTxPrice)
        return contentView
    }

    companion object {
        const val TAG = "AddWCDialogFragment"
    }
}