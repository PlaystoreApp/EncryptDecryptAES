package com.example.adlinux02.endecry;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.adlinux02.endecry.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private static final String KEY = "ThisIsThe24BitKeyUsedFor";
    private static final String VECTOR = "ThisIsOfLength16";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.encryptKey.setText(KEY);
        binding.decryptKey.setText(KEY);

        binding.buttonEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = binding.encryptKey.getText().toString().trim();
                String text = binding.textToEncrypt.getText().toString().trim();
                if (!key.isEmpty() && !text.isEmpty()) {
                    String encryptedHexString = AESAlgorithmClass.encrypt(key, VECTOR, text);
                    binding.encryptedDecryptedText.setText(encryptedHexString);
                }
            }
        });

        binding.buttonDecrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String key = binding.decryptKey.getText().toString().trim();
                String text = binding.textToDecrypt.getText().toString().trim();
                if (!key.isEmpty() && !text.isEmpty()) {
                    String decryptedHexString = AESAlgorithmClass.decrypt(key, VECTOR, text);
                    binding.encryptedDecryptedText.setText(decryptedHexString);
                }
            }
        });
    }
}
