package com.example.sistemasdepedidosapk

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ProductoAdapter(private val productos: List<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(R.id.tvNombre)
        val descripcion: TextView = view.findViewById(R.id.tvDescripcion)
        val precio: TextView = view.findViewById(R.id.tvPrecio)
        val categoria: TextView = view.findViewById(R.id.tvCategoria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val p = productos[position]
        holder.nombre.text = p.nombre
        holder.descripcion.text = p.descripcion
        holder.precio.text = "$ ${p.precio}"
        holder.categoria.text = "${p.categoria} • Stock: ${p.stock}"
    }

    override fun getItemCount() = productos.size
}