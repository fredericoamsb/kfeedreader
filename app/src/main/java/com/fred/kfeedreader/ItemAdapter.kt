package com.fred.kfeedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(private val list: ArrayList<MainActivity.Item>, private val context: Context) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titulo: TextView = view.findViewById(R.id.titulo)
        val autor: TextView = view.findViewById(R.id.autor)
        val data: TextView = view.findViewById(R.id.data)
        val imagem: ImageView = view.findViewById(R.id.imagem)
        val btnVerMais: TextView = view.findViewById(R.id.btnVerMais)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_list, parent, false)

        return ItemViewHolder(v)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = list[position]

        holder.titulo.text = item.titulo
        holder.autor.text = item.autor
        holder.data.text =
            SimpleDateFormat("dd/MM/yy", Locale("pt", "BR")).format(Date(item.data))
        holder.btnVerMais.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, item.link)
            context.startActivity(intent)

        }

        DownloadImageTask(holder.imagem).execute(item.imagem)
    }

    override fun getItemCount(): Int = list.size
}