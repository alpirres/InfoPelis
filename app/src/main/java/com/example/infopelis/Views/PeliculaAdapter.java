package com.example.infopelis.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.R;

import java.util.ArrayList;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.PeliculaViewHolder> implements View.OnClickListener {

    private Context context;
    private ArrayList<Pelicula> peliculas;
    private View.OnClickListener listener;

    public PeliculaAdapter(Context context, ArrayList<Pelicula> peliculas) {
        this.context = context;
        this.peliculas = peliculas;
    }

    @NonNull
    @Override
    public  PeliculaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView= LayoutInflater.from(context).inflate(R.layout.recycler_view_item, null);
        return new PeliculaViewHolder(contentView);
    }

    @Override
    public void onBindViewHolder(@NonNull PeliculaViewHolder holder, int position) {
        Pelicula peli = peliculas.get(position);
        holder.PeliculaBind(peli);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {

        ImageView imageAdapter;
        TextView tituloAdapter;
        TextView directorAdapter;

        public PeliculaViewHolder(@NonNull View itemView) {
            super(itemView);
            imageAdapter = (ImageView) itemView.findViewById(R.id.imageRecicler);
            tituloAdapter = (TextView) itemView.findViewById(R.id.tituloRecicler);
            directorAdapter = (TextView) itemView.findViewById(R.id.directorRecicler);
        }

        public void PeliculaBind(Pelicula p){
            tituloAdapter.setText(p.getTitulo());
            directorAdapter.setText(p.getDirector());
            if(p.getImage() != null && !p.getImage().isEmpty() && !p.getImage().trim().equals("")){
                byte[] decodedString = Base64.decode(p.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageAdapter.setImageBitmap(decodedByte);
            }else{
                imageAdapter.setImageResource(R.drawable.imgdefault);
            }
        }
    }
}
