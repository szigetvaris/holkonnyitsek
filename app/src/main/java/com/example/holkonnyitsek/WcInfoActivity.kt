package com.example.holkonnyitsek

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.example.holkonnyitsek.adapter.CommentAdapter
import com.example.holkonnyitsek.data.WCObject
import com.example.holkonnyitsek.data.WCRating
import com.example.holkonnyitsek.fragments.AddWCFragment
import com.example.holkonnyitsek.fragments.EditWCFragment
import kotlinx.android.synthetic.main.activity_wc_info.*

class WcInfoActivity : AppCompatActivity(), EditWCFragment.EditWCDialogListener{

    lateinit var commentAdapter: CommentAdapter

    lateinit var iTvName: TextView
    lateinit var iTvRating: TextView
    lateinit var iTvOpeningHours: TextView
    lateinit var iTvPrice: TextView

    lateinit var bRate: ImageButton
    lateinit var bEdit: ImageButton
    lateinit var bDelete: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_wc_info)

        iTvName = this.findViewById<TextView>(R.id.tNameValue)
        iTvRating = this.findViewById<TextView>(R.id.tRatingsValue)
        iTvOpeningHours = this.findViewById<TextView>(R.id.tOpeningHoursValue)
        iTvPrice = this.findViewById<TextView>(R.id.tPriceValue)

        bRate = this.findViewById(R.id.bRate) as ImageButton
        bRate.setOnClickListener {
            Toast.makeText(this, "Anyad nyomkodd! Ertekeles", Toast.LENGTH_SHORT).show()
        }
        bEdit = this.findViewById<ImageButton>(R.id.bEdit)
        bEdit.setOnClickListener {
            //Toast.makeText(this, "Szerkesztes", Toast.LENGTH_SHORT).show()
            EditWCFragment().show(
                supportFragmentManager,
                EditWCFragment.TAG
            )
        }
        bDelete = this.findViewById<ImageButton>(R.id.bDelete)
        bDelete.setOnClickListener {
            //Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            DMI.delWC(DMI.SelectedWC)
        }


        updateInfo()
        commentAdapter = CommentAdapter(this)
        recyclerComment.adapter = commentAdapter
    }

    fun updateInfo() {
        iTvName.text = DMI.SelectedWC.name
        var sum = 0
        var n = 0
        for (rating: WCRating in DMI.SelectedWC.ratings) {
            sum += rating.stars
            n += 1
        }

        if(n != 0){
            iTvRating.text = (Math.round((sum / n *10).toDouble())/10).toString()
        }
        else{
            iTvRating.text = "Nem értékelt"
        }
        if(DMI.SelectedWC.opening_hours != null)
            iTvOpeningHours.text = DMI.SelectedWC.opening_hours
        else
            iTvOpeningHours.text = "Ismeretlen"
        if(DMI.SelectedWC.Price != null)
            iTvPrice.text = DMI.SelectedWC.Price.toString()
        else
            iTvPrice.text = "Ismeretlen"

    }

    override fun onWCEdited(newWC: WCObject) {
        println("modosult a klotyo")
        DMI.editWC(newWC)    }
}