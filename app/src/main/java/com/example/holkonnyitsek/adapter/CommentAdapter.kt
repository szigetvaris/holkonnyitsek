package com.example.holkonnyitsek.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.holkonnyitsek.DMI
import com.example.holkonnyitsek.R
import com.example.holkonnyitsek.data.WCObject
import com.example.holkonnyitsek.data.WCRating


class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private val context: Context
    constructor(context: Context) : super() {
        this.context = context
    }
//    private val comments: MutableList<WCRating> = DMI.SelectedWC.ratings
    private val comments: MutableList<WCRating> = mutableListOf<WCRating>(
        WCRating("Bela", "2022.08.33",5, "Szuper jo volt!!" ),
        WCRating("Juci", "2022.08.33", 3, "Voltam mar jobban is.."),
        WCRating("Juci", "2022.08.34", 5, "Masodjara sokkal jobban tetszett :)"),
        WCRating("Bela", "2022.08.35",5, "Mindig szeretek ide szarni!" ),
        WCRating("Bandi", "2022.08.33", 2, "Lorem ipsum subi dubi legyen ez a komment tuti$$ szerintem lehetne jobb is rosszabb is most 2 csillagot adtam ra es csak jartatom a szam")
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater
            .from(context)
            .inflate(R.layout.comment_row, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        var comment = comments[position]

        holder.tvName.text = comment.username
        holder.tvDate.text = comment.date
        holder.tvStars.text = comment.stars.toString()
        holder.tvContent.text = comment.content
    }

    override fun getItemCount() = comments.size

    inner class CommentViewHolder(commentView: View) : RecyclerView.ViewHolder(commentView) {
        var tvName = commentView.findViewById<TextView>(R.id.tvName)
        var tvDate = commentView.findViewById<TextView>(R.id.tvDate)
        var tvStars = commentView.findViewById<TextView>(R.id.tvStars)
        var tvContent = commentView.findViewById<TextView>(R.id.tvContent)

    }

}