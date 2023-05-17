package com.example.mlkit_extracttext;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mlkit_extracttext.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private final Context context = this;
    private String uriString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        setOnClickListener();
    }

    private void setOnClickListener() {
        binding.buttonPickImage.setOnClickListener(view -> {
            getContent.launch("image/*");
        });

        binding.buttonExtractText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ResultActivity.class);
                intent.putExtra("uri", uriString);
                startActivity(intent);
            }
        });
    }

    ActivityResultLauncher<String> getContent = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            new ActivityResultCallback<android.net.Uri>() {
                @Override
                public void onActivityResult(android.net.Uri result) {
                    binding.imageView.setImageURI(result);
                    uriString = result.toString();
                }
            }
    );

}