package com.example.sistemasdepedidosapk

import retrofit2.Call
import retrofit2.http.GET

data class Producto(
    val id: Int,
    val nombre: String,
    val descripcion: String,
    val precio: String,
    val categoria: String,
    val stock: Int
)

interface ApiService {
    @GET("api/productos")
    fun obtenerProductos(): Call<List<Producto>>
}