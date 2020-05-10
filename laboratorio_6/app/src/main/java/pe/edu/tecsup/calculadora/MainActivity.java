package pe.edu.tecsup.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnUno, btnDos, btnTres, btnCuatro, btnCinco, btnSeis, btnSiete, btnOcho, btnNueve,
            btnCero, btnSuma, btnResta, btnMultiplicar, btnDividir, btnClean, btnBorrar, btnPunto, btnIgual;
    TextView ResultadoView, HistorialView;
    double resultado, acumulado;
    String operador, display, reserva;

    private void mostrarEnTextView(String value) {
        display = ResultadoView.getText().toString();
        display = display + value;
        ResultadoView.setText(display);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUno = findViewById(R.id.Uno);
        btnDos = findViewById(R.id.Dos);
        btnTres = findViewById(R.id.Tres);
        btnCuatro = findViewById(R.id.Cuatro);
        btnCinco = findViewById(R.id.Cinco);
        btnSeis = findViewById(R.id.Seis);
        btnSiete = findViewById(R.id.Siete);
        btnOcho = findViewById(R.id.Ocho);
        btnNueve = findViewById(R.id.Nueve);
        btnCero = findViewById(R.id.Cero);
        btnSuma = findViewById(R.id.Suma);
        btnResta = findViewById(R.id.Resta);
        btnMultiplicar = findViewById(R.id.Multiplica);
        btnDividir = findViewById(R.id.Divide);
        btnClean = findViewById(R.id.Clean);
        btnBorrar = findViewById(R.id.Borrar);
        btnPunto = findViewById(R.id.Punto);
        btnIgual = findViewById(R.id.Igual);
        ResultadoView = findViewById(R.id.Resultado);
        HistorialView = findViewById(R.id.Historial);

        btnUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarEnTextView("1");
            }
        });

        btnDos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("2");
            }
        });

        btnTres.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("3");
            }
        });

        btnCuatro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("4");
            }
        });

        btnCinco.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("5");
            }
        });

        btnSeis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("6");
            }
        });

        btnSiete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("7");
            }
        });

        btnOcho.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("8");
            }
        });

        btnNueve.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("9");
            }
        });

        btnCero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mostrarEnTextView("0");
            }
        });

        btnSuma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operador = "+";
                if (!ResultadoView.getText().toString().isEmpty()) {
                    acumulado = Double.parseDouble(ResultadoView.getText().toString());
                    reserva = ResultadoView.getText().toString();
                }
                HistorialView.setText(reserva);
                ResultadoView.setText("");
            }
        });

        btnResta.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operador = "-";
                if (!ResultadoView.getText().toString().isEmpty()) {
                    acumulado = Double.parseDouble(ResultadoView.getText().toString());
                    reserva = ResultadoView.getText().toString();
                }
                HistorialView.setText(reserva);
                ResultadoView.setText("");
            }
        });

        btnMultiplicar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operador = "*";
                if (!ResultadoView.getText().toString().isEmpty()) {
                    acumulado = Double.parseDouble(ResultadoView.getText().toString());
                    reserva = ResultadoView.getText().toString();
                }
                HistorialView.setText(reserva);
                ResultadoView.setText("");
            }
        });

        btnDividir.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                operador = "/";
                if (!ResultadoView.getText().toString().isEmpty()) {
                    acumulado = Double.parseDouble(ResultadoView.getText().toString());
                    reserva = ResultadoView.getText().toString();
                }
                HistorialView.setText(reserva);
                ResultadoView.setText("");
            }
        });

        btnPunto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                display = ResultadoView.getText().toString();
                if (display.contains(".") || display.isEmpty()) {
                    display = display + "";
                } else {
                    display = display + ".";
                }
                ResultadoView.setText(display);
            }
        });

        btnClean.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                display = "";
                ResultadoView.setText(display);
                HistorialView.setText("");
                reserva = "";
                operador = "";
                acumulado = 0;
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                display = ResultadoView.getText().toString();
                display = display.isEmpty() ? "" : display.substring(0,display.length()-1);
                ResultadoView.setText(display);
            }
        });

        btnIgual.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                System.out.println("OPERACIÓN A APLICAR: " + operador);
                switch (operador) {
                    case "-":
                        if (!ResultadoView.getText().toString().isEmpty()) {
                            resultado = Double.parseDouble(reserva) - Double.parseDouble(ResultadoView.getText().toString());
                            reserva = String.valueOf(resultado);
                            ResultadoView.setText("");
                            HistorialView.setText(String.valueOf(resultado));
                        }
                        operador = "";
                        break;
                    case "+":
                        if (!ResultadoView.getText().toString().isEmpty()) {
                            resultado = Double.parseDouble(reserva) + Double.parseDouble(ResultadoView.getText().toString());
                            reserva = String.valueOf(resultado);
                            ResultadoView.setText("");
                            HistorialView.setText(String.valueOf(resultado));
                        }
                        /*else {
                            acumulado = resultado;
                            HistorialView.setText(String.valueOf(resultado));
                        }*/
                        operador = "";
                        break;
                    case "/":
                        if (!ResultadoView.getText().toString().isEmpty()) {
                            resultado = Double.parseDouble(reserva) / Double.parseDouble(ResultadoView.getText().toString());
                            reserva = String.valueOf(resultado);
                            ResultadoView.setText("");
                            HistorialView.setText(String.valueOf(resultado));
                        }
                        operador = "";
                        break;
                    case "*":
                        if (!ResultadoView.getText().toString().isEmpty()) {
                            resultado = Double.parseDouble(reserva) * Double.parseDouble(ResultadoView.getText().toString());
                            reserva = String.valueOf(resultado);
                            ResultadoView.setText("");
                            HistorialView.setText(String.valueOf(resultado));
                        }
                        operador = "";
                        break;
                    default:
                        display = ResultadoView.getText().toString();
                        System.out.println("A MOSTRAR: " + display);
                        ResultadoView.setText("");
                        // display = display + "1";
                        String texto = ResultadoView.getText() == null || ResultadoView.getText() == "." ? "" : ResultadoView.getText().toString();
                        HistorialView.setText(texto);
                        break;
                }
            }
        });
    }
}
