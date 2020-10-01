package cl.inacap.proyectoevaluacion1.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.proyectoevaluacion1.R;
import cl.inacap.proyectoevaluacion1.dto.Evento;

public class CustomArrayAdapter extends ArrayAdapter<Evento> {


    private Activity activity;
    private List<Evento> eventos;



    public CustomArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Evento> objects) {
        super(context, resource, objects);
        this.activity = context;
        this.eventos = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();

        View view = inflater.inflate(R.layout.customlayout, null, true);

        ImageView mImageView = view.findViewById(R.id.imageView);
        TextView mTextFecha = view.findViewById(R.id.twFecha);
        TextView mTextNombre = view.findViewById(R.id.twNombre);
        TextView mTextValor = view.findViewById(R.id.twValor);



        int cal = eventos.get(position).getCalificacion();

        if (cal < 4){

            mImageView.setImageResource(R.drawable.triste);
        } else if (cal < 6) {
            mImageView.setImageResource(R.drawable.meh);
        }else {
            mImageView.setImageResource(R.drawable.crazy);
        }

        mTextNombre.setText(eventos.get(position).getNombre());
        mTextFecha.setText(eventos.get(position).getFecha());
        mTextValor.setText(String.valueOf(eventos.get(position).getValor()));


        return view;


    }
}
