package com.example.sistemasdepedidosapk

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var rvProductos: RecyclerView
    private lateinit var progresoCarga: ProgressBar
    private lateinit var layoutError: View
    private lateinit var tvError: TextView
    private lateinit var apiService: ApiService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProductos = findViewById(R.id.rvProductos)
        progresoCarga = findViewById(R.id.progresoCarga)
        layoutError = findViewById(R.id.layoutError)
        tvError = findViewById(R.id.tvError)
        val btnReintentar = findViewById<Button>(R.id.btnReintentar)

        rvProductos.layoutManager = LinearLayoutManager(this)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://192.168.1.211:3000/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        apiService = retrofit.create(ApiService::class.java)

        btnReintentar.setOnClickListener { cargarProductos() }
        cargarProductos()
    }

    private fun cargarProductos() {
        // Estado: cargando
        progresoCarga.visibility = View.VISIBLE
        rvProductos.visibility = View.GONE
        layoutError.visibility = View.GONE

        apiService.obtenerProductos().enqueue(object : Callback<List<Producto>> {
            override fun onResponse(call: Call<List<Producto>>, response: Response<List<Producto>>) {
                progresoCarga.visibility = View.GONE
                val lista = response.body()

                if (response.isSuccessful && lista != null) {
                    if (lista.isEmpty()) {
                        mostrarError("No hay productos registrados")
                    } else {
                        // Estado: éxito
                        rvProductos.adapter = ProductoAdapter(lista)
                        rvProductos.visibility = View.VISIBLE
                    }
                } else {
                    mostrarError("Error del servidor (código ${response.code()})")
                }
            }

            override fun onFailure(call: Call<List<Producto>>, t: Throwable) {
                progresoCarga.visibility = View.GONE
                mostrarError("No se pudo conectar al servidor.\nVerifica tu conexión y que la API esté encendida.")
            }
        })
    }

    private fun mostrarError(mensaje: String) {
        // Estado: error
        tvError.text = mensaje
        layoutError.visibility = View.VISIBLE
    }
}