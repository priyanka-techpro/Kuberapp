package com.techprostudio.kuberinternational.ImageCompression;

public interface ImageCompressionListener {
    void onStart();

    void onCompressed(String filePath);
}
