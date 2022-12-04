package com.example.holkonnyitsek.fragments

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.holkonnyitsek.DMI
import com.example.holkonnyitsek.R
import com.example.holkonnyitsek.data.WCObject
import com.example.holkonnyitsek.data.WCRating


class EditWCFragment : DialogFragment() {
    private lateinit var nameEditText: EditText
    private lateinit var openingHoursEditText: EditText
    private lateinit var isFreeCheckBox: CheckBox
    private lateinit var priceEditText: EditText

    interface EditWCDialogListener {
        fun onWCEdited(newWC: WCObject)
    }

    private lateinit var listener: EditWCDialogListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? EditWCDialogListener
            ?: throw RuntimeException("Activity must implement the EditWCDialogListener interface!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setTitle("Illemhely szerkesztése")
            .setView(getContentView())
            .setPositiveButton("Módosít") { dialogInterface, i ->
                if (isValid()) {
                    listener.onWCEdited(editWC());
                }
            }
            .setNegativeButton("Mégse", null)
            .create()
    }

    private fun isValid() = nameEditText.text.isNotEmpty()

    private fun editWC(): WCObject{
        DMI.SelectedWC.name = nameEditText.text.toString()
        DMI.SelectedWC.opening_hours = openingHoursEditText.text.toString()
        DMI.SelectedWC.isfree = isFreeCheckBox.isChecked
        DMI.SelectedWC.Price = priceEditText.text.toString()
        return DMI.SelectedWC
    }

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
        const val TAG = "EditWCDialogFragment"
    }
}