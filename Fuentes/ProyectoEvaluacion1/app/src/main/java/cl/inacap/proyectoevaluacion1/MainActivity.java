package cl.inacap.proyectoevaluacion1;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cl.inacap.proyectoevaluacion1.dao.EventosDAO;
import cl.inacap.proyectoevaluacion1.dto.Evento;

public class MainActivity extends AppCompatActivity {

    private EditText nombreTxt;
    private EditText valorTxt;
    private Spinner generoSp;
    private Spinner notaSp;
    private EditText fechaTxt;
    private ListView eventosLv;
    private Button agregarBtn;
    private Button limpiarBtn;
    private EventosDAO edao = new EventosDAO();
    String[] generos = { "Rock", "Jazz", "Pop", "Reggaeton", "Salsa" , "Metal" };
    String[] notas = { "1" , "2" , "3" , "4" , "5" , "6" , "7"};
    private ArrayAdapter<Evento> adapterL;
    AdapterList adapterList = new AdapterList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.nombreTxt = findViewById(R.id.nombreTxt);
        this.valorTxt = findViewById(R.id.valorTxt);
        this.generoSp = (Spinner) findViewById(R.id.generoSp);
        this.fechaTxt = findViewById(R.id.fechaTxt);
        this.agregarBtn = findViewById(R.id.agregarBtn);
        this.notaSp = (Spinner) findViewById(R.id.notaSp);
        this.eventosLv = findViewById(R.id.eventosLv);
        this.limpiarBtn = findViewById(R.id.limpiarBtn);

        //this.adapterL = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, edao.getAll());

        ArrayAdapter<String> adapterG =  new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, generos);
        ArrayAdapter<String> adapterN = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, notas);
        adapterG.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterN.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        generoSp.setAdapter(adapterG);
        notaSp.setAdapter(adapterN);


        eventosLv.setAdapter(adapterList);

        fechaTxt.setInputType(InputType.TYPE_NULL);

        this.fechaTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cldr = Calendar.getInstance();
                DatePickerDialog picker = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                fechaTxt.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, cldr.get(Calendar.YEAR), cldr.get(Calendar.MONTH), cldr.get(Calendar.DAY_OF_MONTH));
                picker.show();
            }
        });

        this.limpiarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombreTxt.setText("");
                fechaTxt.setText("");
                valorTxt.setText("");

            }
        });

        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                List<String> errores = new ArrayList<>();
                String nombreStr = nombreTxt.getText().toString().trim();
                String valorStr = valorTxt.getText().toString().trim();
                String generoStr = generoSp.getSelectedItem().toString().trim();
                String notaStr = notaSp.getSelectedItem().toString().trim();
                String fechaStr = fechaTxt.getText().toString().trim();

                int valor = 0;
                int nota = 0;

                if(nombreStr.isEmpty()){
                    errores.add("ingrese un nombre");
                }

                if (valorStr.isEmpty()){
                    errores.add("ingrese un valor");
                }else{
                    try {
                        valor = Integer.parseInt(valorStr);
                        if(valor < 0){
                            errores.add("Ingrese un valor positivo");
                        }
                    }catch (NumberFormatException ex){
                        errores.add("ingrese un valor numerico");
                    }
                }

                if (generoStr.isEmpty()){
                    errores.add("ingrese un Genero Musical");
                }

                if (notaStr.isEmpty()){
                    errores.add("ingrese una Calificacion");
                }else{

                    try {
                        nota = Integer.parseInt(notaStr);
                        if (nota < 1 || nota > 7){
                            errores.add("ingrese un valor entre 1 y 7");
                        }
                    }catch (NumberFormatException ex){
                        errores.add("ingrese un valor numerico");
                    }
                }

                if (fechaStr.isEmpty()){
                    errores.add("ingrese una fecha");
                }else{

                }


                if (errores.isEmpty()){


                    Evento e = new Evento();
                    e.setNombre(nombreStr);
                    e.setFecha(fechaStr);
                    e.setGenero(generoStr);
                    e.setValor(valor);
                    e.setCalificacion(nota);
                    edao.Add(e);
                    adapterList.notifyDataSetChanged();

                }else {
                    mostrarErrores(errores);
                }




            }
        });
    }

    class AdapterList extends BaseAdapter{

        @Override
        public int getCount() {
            return edao.getAll().size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            return null;
        }
    }

    private void mostrarErrores(List<String> errores) {
        String mensaje = "";
        for (String e:errores){
            mensaje += "-" + e + "\n";
        }
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(MainActivity.this);
        alertBuilder.setTitle("Error de Validacion")
                .setMessage(mensaje)
                .setPositiveButton("aceptar",null)
                .create()
                .show();
    }
}