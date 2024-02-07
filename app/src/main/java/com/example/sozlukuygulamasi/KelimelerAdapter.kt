package com.example.sozlukuygulamasi

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class KelimelerAdapter(private val mContext:Context,private val kelimeler:List<Kelimeler>):
    RecyclerView.Adapter<KelimelerAdapter.CardTasarimTutucu>() {


    inner class CardTasarimTutucu(view:View):RecyclerView.ViewHolder(view){
        var kelime_card:CardView
        var textViewIngilizce:TextView
        var textViewTurkce:TextView

        init {
            kelime_card = view.findViewById(R.id.cardviewKelime)
            textViewIngilizce = view.findViewById(R.id.textViewIngilizce)
            textViewTurkce = view.findViewById(R.id.textViewTurkce)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardTasarimTutucu {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.cart_tasarim,parent,false)
        return CardTasarimTutucu(tasarim)
    }

    override fun getItemCount(): Int {
       return kelimeler.size
    }

    override fun onBindViewHolder(holder: CardTasarimTutucu, position: Int) {
        var kelime = kelimeler.get(position)

        holder.textViewIngilizce.text = kelime.ingilizce
        holder.textViewTurkce.text = kelime.turkce

        holder.kelime_card.setOnClickListener {
            val intent = Intent(mContext,DetayActivity::class.java)
            intent.putExtra("nesne",kelime)
            mContext.startActivity(intent)
        }
    }
}