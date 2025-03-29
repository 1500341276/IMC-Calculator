package com.fecapccp.trabalho;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.fecapccp.trabalho.IMC.AbaixoDoPesoActivity;
import com.fecapccp.trabalho.IMC.Obesidade1Activity;
import com.fecapccp.trabalho.IMC.Obesidade2Activity;
import com.fecapccp.trabalho.IMC.Obesidade3Activity;
import com.fecapccp.trabalho.IMC.PesoNormalActivity;
import com.fecapccp.trabalho.IMC.SobrepesoActivity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class MainActivity2 extends AppCompatActivity {

    private Button btnT2Set;
    private Button btnT2Reset;
    private Button btnT2Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        btnT2Set = findViewById(R.id.btnT2Set);
        btnT2Reset = findViewById(R.id.btnT2Reset);
        btnT2Back = findViewById(R.id.btnT2Back);

        btnT2Set.setOnClickListener(view -> {

            EditText campoPeso = findViewById(R.id.editT2TextPeso);
            EditText campoAltura = findViewById(R.id.editT2TextAltura);

            String peso = campoPeso.getText().toString();
            String altura = campoAltura.getText().toString();

            if (peso.isEmpty() || altura.isEmpty()) {
                Toast.makeText(this, "Por favor, preencha todos os campos!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                double numPeso = Double.parseDouble(peso);
                double numAltura = Double.parseDouble(altura);
                double numImc = numPeso / (numAltura * numAltura);


                Log.d("IMC_Calculado", "Peso: " + numPeso + " Altura: " + numAltura + " IMC: " + numImc);


                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat df = new DecimalFormat("##.##", symbols);
                String imcFormatado = df.format(numImc);

                // 🔹 选择目标 Activity
                Class<?> targetActivity;
                if (numImc < 18.5) {
                    targetActivity = AbaixoDoPesoActivity.class;
                } else if (numImc < 25) {
                    targetActivity = PesoNormalActivity.class;
                } else if (numImc < 30) {
                    targetActivity = SobrepesoActivity.class;
                } else if (numImc < 35) {
                    targetActivity = Obesidade1Activity.class;
                } else if (numImc < 40) {
                    targetActivity = Obesidade2Activity.class;
                } else {
                    targetActivity = Obesidade3Activity.class;
                }


                Intent intent = new Intent(MainActivity2.this, targetActivity);
                intent.putExtra("peso", numPeso);
                intent.putExtra("altura", numAltura);
                intent.putExtra("imc", imcFormatado);

                startActivity(intent);

            } catch (NumberFormatException e) {
                Toast.makeText(this, "Erro ao calcular IMC! Verifique os valores inseridos.", Toast.LENGTH_SHORT).show();
                Log.e("IMC_Error", "Erro ao converter valores: " + e.getMessage());
            }
        });

        btnT2Reset.setOnClickListener(view -> {
            EditText campoPeso = findViewById(R.id.editT2TextPeso);
            EditText campoAltura = findViewById(R.id.editT2TextAltura);
            campoPeso.setText("");
            campoAltura.setText("");
        });

        btnT2Back.setOnClickListener(view -> {
            finish();
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
