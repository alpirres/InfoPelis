package com.example.infopelis.Views;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.infopelis.Models.Pelicula;
import com.example.infopelis.R;

import java.util.ArrayList;

public class PeliculaAdapter
        extends RecyclerView.Adapter<PeliculaAdapter.AcontecimientoViewHolder>
        implements View.OnClickListener {

    private ArrayList<Pelicula> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class AcontecimientoViewHolder
            extends RecyclerView.ViewHolder {

        private TextView TextView_id;
        private TextView TextView_nombre;
        private ImageView imageView_image;

        public AcontecimientoViewHolder(View itemView) {
            super(itemView);
            TextView_id = (TextView) itemView.findViewById(R.id.textView_titulo);
            TextView_nombre = (TextView) itemView.findViewById(R.id.textView_duracion);
            imageView_image= (ImageView) itemView.findViewById(R.id.imageView2);
        }

        public void AcontecimientoBind(Pelicula item) {
            TextView_id.setText(String.valueOf(item.getId()) );
            TextView_nombre.setText(item.getTitulo());
            byte[] decodedString = Base64.decode(item.getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            imageView_image.setImageBitmap(decodedByte);
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public PeliculaAdapter(@NonNull ArrayList<Pelicula> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public AcontecimientoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        row.setOnClickListener(this);

        AcontecimientoViewHolder avh = new AcontecimientoViewHolder(row);
        return avh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(AcontecimientoViewHolder viewHolder, int position) {
        Pelicula item = items.get(position);
        viewHolder.AcontecimientoBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }
}
