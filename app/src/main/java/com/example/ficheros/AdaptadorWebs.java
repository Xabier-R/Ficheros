package com.example.ficheros;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdaptadorWebs {

    private Context context ;
    private Web[] Webs;

    public AdaptadorWebs(@NonNull Context context, Web[] resource) {
        super(context, R.layout.listview_webs,resource);

        this.context = context;
        this.Webs = resource;

    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_webs, null);

        TextView tvNomchat = item.findViewById(R.id.TextViewNombre);
        TextView tvDescripcion = item.findViewById(R.id.TextViewEnlace);
        ImageView image = item.findViewById(R.id.imageView);
        TextView identificador = item.findViewById(R.id.TextViewIdenti);

        tvNomchat.setText(Webs[position].getNombre());
        tvDescripcion.setText(Webs[position].getEnlace());
        image.setImageResource(Webs[position].getLogo());
        identificador.setText(Webs[position].getIdentificador());
        return item;
    }

}
}
