package com.example.ervi.lab1_compresion;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VentanaDescomprimir extends AppCompatActivity {


    CaracteresArchivo miArbol = new CaracteresArchivo();
    public static List<Caracter> caracteres = new ArrayList<>();
    private static final int PERMISSION_REQUEST_STORAGE = 1000;
    private static final int READ_REQUEST_CODE = 42;
    private static String pathArchivo2;
    private static String pathArchivo3;
    String textoDescomprimido;
    Button agregarArchivo;
    Button Descomprimir;
    TextView tvPath;
    TextView tvDescomprimido;
    String Caracteres;
    String Ascii;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ventana_descomprimir);
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_STORAGE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1000);
        }
        agregarArchivo =(Button) findViewById(R.id.btnBuscarArchivo);
        tvPath =(TextView) findViewById(R.id.tvPathArchivo);
        tvDescomprimido =(TextView) findViewById(R.id.tvContenidoArchivo);
        Descomprimir = (Button) findViewById(R.id.btnDescomprimir);
        agregarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                performFileSearch();
            }
        });

    }


    private String readText(String input)
    {
        File file = new File(Environment.getExternalStorageDirectory(),input);
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            int contador = 1;
            while ((line=br.readLine())!=null)
            {

                if (contador==1)
                    Caracteres = line;
                else
                    Ascii=line;
                contador++;
            }
            br.close();

        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }

        return text.toString();
    }
    private void performFileSearch()
    {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("text/*");
        startActivityForResult(intent,READ_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode== READ_REQUEST_CODE && resultCode == RESULT_OK)
        {
            if (data!=null)
            {
                Uri uri = data.getData();
                String path = uri.getPath();
                path = path.substring(path.indexOf(":") + 1);
                Toast.makeText(this,"" + path,Toast.LENGTH_SHORT).show();

                pathArchivo2 = path;
                tvPath.setText(path);
                pathArchivo3 =path.substring(0,path.indexOf("."));
                /*
                ;
                tvBinario.setText(miArbol.getBinarioVentana());
                tvAscii.setText(miArbol.getPasarAscii());
                */
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode==PERMISSION_REQUEST_STORAGE)
        {
            if (grantResults[0]== PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this,"permission granted!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Permission not granted",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void Descomprimir(View view)
    {
        readText(pathArchivo2);
        String[] separar = Caracteres.split("/");
        for (int i = 0;i<separar.length;i++)
        {
            separarLLaveValor(separar[i]);
        }
        llenarNodos();

    }

    public void separarLLaveValor(String Objeto)
    {
        String[] objeto = Objeto.split(",");
        Caracter miCaracter = new Caracter();
        if (objeto[0].equals("!n"))
        {
            miCaracter.setValorCaracter('\n');
        }
        else
        {
            miCaracter.setValorCaracter(objeto[0].charAt(0));
        }

        miCaracter.setConteo(Integer.parseInt(objeto[1]));
        caracteres.add(miCaracter);
    }

    //////////////////////////////////////////////////////////////
    //esto manda a realizar el proceso de descompresión

    public void llenarNodos()
    {
        //este método te devuelve un STRING CON EL TEXTO DESCOMPRIMIDO, PARA ESCRIBIRLO EN UN NUEVO ARCHIVO

       textoDescomprimido = miArbol.Descomprimir(Ascii, caracteres);
       tvDescomprimido.setText(textoDescomprimido);
       escribirArchivo();
    }


    public void escribirArchivo()
    {

        String fileName = pathArchivo3+"Descomprimido.txt";
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);

            String guardar = textoDescomprimido;
            fos.write(guardar.getBytes());
            fos.close();
            Toast.makeText(this,"Guardado",Toast.LENGTH_SHORT).show();
            }

         catch (FileNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this,"Archivo no encontrado",Toast.LENGTH_SHORT).show();
        }
        catch(IOException e)
        {
            e.printStackTrace();
            Toast.makeText(this,"ERROR Guardando",Toast.LENGTH_SHORT).show();
        }



    }

}
