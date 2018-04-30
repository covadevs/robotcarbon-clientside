package robotcarbon.com.br.robotcarbon_clientside;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.PrintStream;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private Button espaco;
    private PrintStream saida;
    private Socket cliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        espaco = findViewById(R.id.space);
        TarefaConectar tarefaConectar = new TarefaConectar();
        tarefaConectar.execute("objeto");

        espaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EnviarMensagem enviarMensagem = new EnviarMensagem();
                enviarMensagem.execute("objeto");
            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            saida.close();
            cliente.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    class TarefaConectar extends AsyncTask<Object, Void, Void> {
        @Override
        protected Void doInBackground(Object... objects) {
            try {
                cliente = new Socket("192.168.0.19", 12345);
                saida = new PrintStream(cliente.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class EnviarMensagem extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(Object... objects) {
            saida.println("space");
            return null;
        }
    }
}
