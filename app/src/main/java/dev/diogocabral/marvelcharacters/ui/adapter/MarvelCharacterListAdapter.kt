package dev.diogocabral.marvelcharacters.ui.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import dev.diogocabral.marvelcharacters.R
import dev.diogocabral.marvelcharacters.data.model.Results
import kotlinx.android.synthetic.main.marvel_character_item.view.*

class MarvelCharacterListAdapter(
    marvelCharacterList: List<Results>?,
    private val context: Context
) : RecyclerView.Adapter<MarvelCharacterListAdapter.ViewHolder>() {

    private var data = marvelCharacterList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.marvel_character_item, parent, false)
        return ViewHolder(view, context)
    }

    override fun getItemCount(): Int {
        return data?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentBookResponse = data?.get(position)
        currentBookResponse?.let { holder.bindView(it) }
    }

    fun updateData(newData: List<Results>) {
        this.data = newData
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val context: Context) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bindView(item: Results) {

            itemView.character_name.text = "Name: " + item.name
            itemView.comics_available.text = "Comics Available: " + item.comics.available.toString()

            item.thumbnail.path.let {
                Glide.with(context)
                    .load(it + "." + item.thumbnail.extension)
                    .dontAnimate()
                    .into(itemView.character_image)
            }
        }
    }
}