package com.example.user.barcodeexample;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import info.vividcode.android.zxing.CaptureActivity;
import info.vividcode.android.zxing.CaptureActivityIntents;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.tv_scanresult);

        Button btScan = (Button) findViewById(R.id.bt_scan);
        btScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Membuat intent baru untuk memanggil CaptureActivity bawaan ZXing
                Intent captureIntent = new Intent(MainActivity.this, CaptureActivity.class);

                // Kemudian kita mengeset pesan yang akan ditampilkan ke user saat menjalankan QRCode scanning
                CaptureActivityIntents.setPromptMessage(captureIntent, "Barcode scanning...");

                // Melakukan startActivityForResult, untuk menangkap balikan hasil dari QR Code scanning
                startActivityForResult(captureIntent, 0);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == Activity.RESULT_OK && data != null) {
                String value = data.getStringExtra("SCAN_RESULT");
                textView.setText(value);
            } else if (resultCode == Activity.RESULT_CANCELED) {
                textView.setText("Scanning Gagal, mohon coba lagi.");
            }
        } else {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
