package com.lightricks.mightyrecycler.animator;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lightricks.mightyrecycler.model.MaterialColor;
import com.lightricks.mightyrecycler.model.ColorPalette;

import java.util.List;

public class ItemAnimatorViewModel extends ViewModel {
    private final MutableLiveData<Boolean> isAddButtonEnabled = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isRemoveButtonEnabled = new MutableLiveData<>();
    private final MutableLiveData<List<MaterialColor>> materialColors = new MutableLiveData<>();
    private final ColorPalette palette = new ColorPalette();
    private final int maxColors;
    private int numColors;

    public ItemAnimatorViewModel() {
        maxColors = palette.getAllColors().size();
        numColors = maxColors / 3;
        materialColors.setValue(palette.getNColors(numColors));
        isAddButtonEnabled.setValue(true);
        isRemoveButtonEnabled.setValue(true);
    }

    public LiveData<Boolean> getIsAddButtonEnabled() {
        return isAddButtonEnabled;
    }

    public LiveData<Boolean> getIsRemoveButtonEnabled() {
        return isRemoveButtonEnabled;
    }

    LiveData<List<MaterialColor>> getMaterialColors() {
        return materialColors;
    }

    public void handleAddButtonClick(@SuppressWarnings("unused") View v) {
        materialColors.postValue(palette.getNColors(++numColors));

        isRemoveButtonEnabled.setValue(true);

        if (numColors == maxColors) {
            isAddButtonEnabled.setValue(false);
        }
    }

    public void handleRemoveButtonClick(@SuppressWarnings("unused") View v) {
        materialColors.postValue(palette.getNColors(--numColors));

        isAddButtonEnabled.setValue(true);

        if (numColors == 0) {
            isRemoveButtonEnabled.setValue(false);
        }
    }
}
