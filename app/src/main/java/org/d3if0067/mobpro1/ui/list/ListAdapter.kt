package org.d3if0067.mobpro1.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if0067.mobpro1.R
import org.d3if0067.mobpro1.data.Tiket
import org.d3if0067.mobpro1.databinding.ItemListKonserBinding
import org.d3if0067.mobpro1.network.TiketApi

class ListAdapter : RecyclerView.Adapter<ListAdapter.ViewHolder>() {
    private val data = mutableListOf<Tiket>()

    fun updateData(newData: List<Tiket>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemListKonserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tiket: Tiket) = with(binding) {
            namaTextView.text = tiket.nama
            lokasiTextView.text = tiket.lokasi

            Glide.with(imageView.context)
                .load(TiketApi.getTiketUrl(tiket.imageId))
                .error(R.drawable.baseline_broken_image_24)
                .into(imageView)

            root.setOnClickListener{
                val message = root.context.getString(R.string.message, tiket.nama)
                Toast.makeText(root.context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemListKonserBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }
}