package com.example.ervi.lab1_compresion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VentanaEleccionCompresion extends AppCompatActivity {

    Button BotonHuffman;
    Button BotonLZW;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_eleccion_compresion);
        BotonHuffman=(Button) findViewById(R.id.btnHuffman);
        BotonLZW= (Button) findViewById(R.id.btnLZW);
        BotonHuffman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cambioHuffman();
            }
        });
        BotonLZW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                cambioLZW();
            }
        });
    }

    public void cambioHuffman()
    {
        Intent ventanaHuffman = new Intent(this,MainActivity.class);
        startActivity(ventanaHuffman);
    }
    public void cambioLZW()
    {
        Intent ventanaLZW = new Intent(this,LZWeleccion.class);
        startActivity(ventanaLZW);
    }
}
