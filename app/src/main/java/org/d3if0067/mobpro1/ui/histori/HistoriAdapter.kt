package org.d3if0067.mobpro1.ui.histori

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if0067.mobpro1.R
import org.d3if0067.mobpro1.databinding.ItemHistoriBinding
import org.d3if0067.mobpro1.db.TiketEntity
import org.d3if0067.mobpro1.model.KategoriTiket
import org.d3if0067.mobpro1.model.hitungTiket
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter :
    ListAdapter<TiketEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<TiketEntity>() {
                override fun areItemsTheSame(
                    oldData: TiketEntity, newData: TiketEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: TiketEntity, newData: TiketEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemHistoriBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoriBinding
    ) : RecyclerView.ViewHolder(binding.root){
        private val dateFormatter = SimpleDateFormat("dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: TiketEntity) = with(binding) {
            val hasilTiket = item.hitungTiket()
            kategoriTextView.text = hasilTiket.kategoriTiket.toString().substring(0, 1)
            val colorRes = when(hasilTiket.kategoriTiket) {
                KategoriTiket.BAIK -> R.color.baik
                else ->  R.color.buruk
            }
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(ContextCompat.getColor(root.context, colorRes))

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            totalTextView.text = root.context.getString(R.string.hasil_x,
                hasilTiket.total, hasilTiket.kategoriTiket.toString())

            val seat = root.context.getString(
                if (item.isCat1) R.string.txtCat1 else R.string.txtCat2)
            dataTextView.text = root.context.getString(R.string.data_x,
                item.jumlah, seat)
        }
    }
}