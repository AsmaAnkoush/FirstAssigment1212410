package com.example.asmaaassigmentone;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Theorem extends AppCompatActivity {
    private VideoView vi;
    private int currentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theorem);
        vi = findViewById(R.id.vidio);
        MediaController controller = new MediaController(this);
        controller.setMediaPlayer(vi);
        vi.setMediaController(controller);
        vi.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.asma));
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Check if there's a saved instance state
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("currentPosition", 0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Resume video playback from the saved position
        vi.seekTo(currentPosition);
        vi.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pause video and save current position
        vi.pause();
        currentPosition = vi.getCurrentPosition();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentPosition", currentPosition);
    }
}
