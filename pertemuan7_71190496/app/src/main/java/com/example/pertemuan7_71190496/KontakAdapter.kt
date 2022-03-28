package com.example.pertemuan7_71190496

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class KontakAdapter(var listContact: ArrayList<Kontak>): RecyclerView.Adapter<KontakAdapter.KontakHolder>() {
    class KontakHolder(val view: View): RecyclerView.ViewHolder(view){
        var kontak: Kontak? = null

        fun bindView(contact: Kontak){
            this.kontak = kontak
            view.findViewById<ImageView>(R.id.imvIcon).setImageResource(contact.icon)
            view.findViewById<TextView>(R.id.txvName).text = contact.name
//            view.findViewById<TextView>(R.id.tvNoHp).text = contact.noHp
//            view.findViewById<TextView>(R.id.tvEmail).text = contact.email
            view.setOnClickListener {
                val i = Intent(view.context,DetailKontak::class.java)
                i.putExtra("name", contact.name)
                i.putExtra("noHp", contact.noHp)
                i.putExtra("email", contact.email)
                view.context.startActivity(i)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KontakHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_kontak, parent, false)
        return KontakHolder(v)
    }

    override fun onBindViewHolder(holder: KontakHolder, position: Int) {
        holder.bindView(listContact[position])
    }

    override fun getItemCount(): Int = listContact.size
}