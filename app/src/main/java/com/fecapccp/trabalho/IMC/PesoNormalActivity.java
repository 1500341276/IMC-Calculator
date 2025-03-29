package com.fecapccp.trabalho.IMC;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fecapccp.trabalho.MainActivity;
import com.fecapccp.trabalho.R;


public class PesoNormalActivity extends AppCompatActivity {

    private TextView textResultado;
    private Button btnFechar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_peso_normal);

        textResultado = findViewById(R.id.textResultado);
        btnFechar = findViewById(R.id.btnFechar);

        Bundle bundle = getIntent().getExtras();

        double peso = bundle.getDouble("peso", 0.0);
        double altura = bundle.getDouble("altura", 0.0);
        String imc = bundle.getString("imc", "0.00");

        String resultado = "Peso: " + peso + " kg\n"
                + "Altura: " + altura + " m\n"
                + "IMC: " + imc + "\n"
                + "Você está com Peso Normal!";

        textResultado.setText(resultado);


        btnFechar.setOnClickListener(view ->{
            Intent intent = new Intent(this, MainActivity.class);

            startActivity(intent);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}