package com.techprostudio.kuberinternational.Location;

public interface LocationSetting {
    /**
     * When user cancels the gps alert dialog.
     */
    void onLocationSettingAlertCancelled();
    /**
     * When user click to open GPS Settings screen.
     */
    void onLocationSettingActivityOpened();

    /**
     * If GPS is already enabled.
     */
    void onGpsAlreadyEnabled();
}
