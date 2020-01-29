package com.evs.android.mysampleapp.week9.dialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.evs.android.mysampleapp.R;

/**
 * Created by hassanjamil on 01/29/2020.
 *
 * @author hassanjamil
 */
public class DialogActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        findViewById(R.id.btnAskQuestion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DialogActivity.this);
                builder.setTitle(getString(R.string.dialog_title));
                builder.setCancelable(false);
                builder.setMessage(getString(R.string.question));
                builder.setIcon(R.drawable.ic_help);
                builder.setPositiveButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((ImageView) findViewById(R.id.ivResult)).setImageResource(R.drawable.ic_check_circle);
                        findViewById(R.id.btnAskQuestion).setVisibility(View.GONE);
                        findViewById(R.id.ivResult).setVisibility(View.VISIBLE);
                    }
                });
                builder.setNegativeButton(getString(R.string.no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ((ImageView) findViewById(R.id.ivResult)).setImageResource(R.drawable.ic_cancel);
                        findViewById(R.id.btnAskQuestion).setVisibility(View.GONE);
                        findViewById(R.id.ivResult).setVisibility(View.VISIBLE);
                    }
                });

                builder.create().show();
            }
        });

    }
}
