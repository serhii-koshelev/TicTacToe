package tictactoe.serhiikoshelev.ua.kh.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity  {

    Button singlePlayer;
    Button multiPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        singlePlayer=(Button)findViewById(R.id.singlePlayer);
        multiPlayer=(Button) findViewById(R.id.multiPlayer);
    }


    public void sendMessage(View view)
    {
        if(view == singlePlayer){
            Intent intent = new Intent(this, SinglePlayer.class);
            startActivity(intent);
        }
        if(view == multiPlayer){
            Intent intent = new Intent(this,MultiPlayer.class);
            startActivity(intent);
        }

    }
}
