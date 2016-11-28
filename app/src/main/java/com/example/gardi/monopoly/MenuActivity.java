package com.example.gardi.monopoly;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.w3c.dom.Text;

public class MenuActivity extends AppCompatActivity {

    private TextView newGameTextView;
    private TextView helpTextView;
    private TextView exitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        newGameTextView = (TextView) findViewById(R.id.newGameTextView);
        helpTextView = (TextView) findViewById(R.id.helpTextView);
        exitTextView = (TextView) findViewById(R.id.exitTextView);

        addTextViewClickListeners();
    }

    public void addTextViewClickListeners() {

        newGameTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNewGameClick();
            }
        });
    }

    public void onNewGameClick() {
        String[] roles = {"Сервер","Клиент"};

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MenuActivity.this);
        alertDialogBuilder.setTitle("Выберите роль");
        alertDialogBuilder.setItems(roles, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which == 0) {
                    onServerClick();
                    dialog.cancel();
                }
                else {
                    onClientClick();
                    dialog.cancel();
                }
            }
        });

        alertDialogBuilder.show();
    }

    public void onClientClick() {
        Intent findDeviceIntent = new Intent(MenuActivity.this, FindDeviceActivity.class);
        findDeviceIntent.putExtra("Role","Client");

        startActivity(findDeviceIntent);
    }

    public void onServerClick() {
        Intent lobbyIntent = new Intent(MenuActivity.this, LobbyActivity.class);
        lobbyIntent.putExtra("Role","Server");

        startActivity(lobbyIntent);
    }
}
