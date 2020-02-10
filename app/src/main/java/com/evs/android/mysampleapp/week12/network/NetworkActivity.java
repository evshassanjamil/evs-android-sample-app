package com.evs.android.mysampleapp.week12.network;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.evs.android.mysampleapp.R;
import com.novoda.merlin.Connectable;
import com.novoda.merlin.Disconnectable;
import com.novoda.merlin.Merlin;

public class NetworkActivity extends AppCompatActivity {

    Merlin merlin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        TextView tvStatus = findViewById(R.id.tvNetworkStatus);
        tvStatus.setVisibility(View.GONE);
        ImageView ivNetworkSignal = findViewById(R.id.ivNetworkSignal);
        ivNetworkSignal.setVisibility(View.GONE);


        merlin = new Merlin.Builder()
                .withConnectableCallbacks()
                .withDisconnectableCallbacks()
                .build(this);

        merlin.registerConnectable(new Connectable() {
            @Override
            public void onConnect() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivNetworkSignal.setVisibility(View.VISIBLE);
                        ivNetworkSignal.setImageResource(R.drawable.ic_network_wifi);
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText(getString(R.string.connected));
                        tvStatus.setBackgroundColor(
                                ContextCompat.getColor(NetworkActivity.this, R.color.green));
                    }
                });
            }
        });

        merlin.registerDisconnectable(new Disconnectable() {
            @Override
            public void onDisconnect() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ivNetworkSignal.setVisibility(View.VISIBLE);
                        ivNetworkSignal.setImageResource(R.drawable.ic_signal_wifi_off);
                        tvStatus.setVisibility(View.VISIBLE);
                        tvStatus.setText(getString(R.string.disconnected));
                        tvStatus.setBackgroundColor(
                                ContextCompat.getColor(NetworkActivity.this, R.color.red));
                    }
                });

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        merlin.bind();
    }

    @Override
    protected void onPause() {
        //merlin.unbind();
        super.onPause();
    }
}
