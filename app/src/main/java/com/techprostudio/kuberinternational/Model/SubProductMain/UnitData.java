package com.techprostudio.kuberinternational.Model.SubProductMain;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UnitData {
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("unit_text")
    @Expose
    private String unitText;
    @SerializedName("complete_unit")
    @Expose
    private String completeUnit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitText() {
        return unitText;
    }

    public void setUnitText(String unitText) {
        this.unitText = unitText;
    }

    public String getCompleteUnit() {
        return completeUnit;
    }

    public void setCompleteUnit(String completeUnit) {
        this.completeUnit = completeUnit;
    }
}
