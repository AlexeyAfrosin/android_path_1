package com.afrosin.android_part_1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class SettingsActivity extends AppCompatActivity {

    private static final String NAME_SHARED_PREFERENCE = "SettingsActivity";
    private static final int APP_THEME_DARK_STYLE = 0;
    private static final String KEY_NEED_SET_APP_THEME = "KEY_NEED_SET_APP_THEME";
    // Имя параметра в настройках
    private static final String APP_THEME = "APP_THEME";
    private static final String APP_SWITCH_STATUS = "APP_SWITCH_STATUS";
    private boolean needSetAppTheme;

    private SwitchMaterial switchTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(this);
        setContentView(R.layout.activity_settings);

        switchTheme = findViewById(R.id.switch_theme);
        initVariables(savedInstanceState);

        switchTheme.setOnCheckedChangeListener((compoundButton, b) -> {
            if (needSetAppTheme) {
                if (compoundButton.isChecked()) {
                    saveSettings(APP_THEME_DARK_STYLE, true);
                } else {
                    saveSettings(R.style.Theme_Android_part_1, false);
                }
                setTheme(this);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle instanceState) {
        super.onSaveInstanceState(instanceState);
        instanceState.putBoolean(KEY_NEED_SET_APP_THEME, needSetAppTheme);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle instanceState) {
        super.onRestoreInstanceState(instanceState);
        initVariables(instanceState);
    }

    private void initVariables(Bundle instanceState) {
        if (instanceState != null) {
            needSetAppTheme = instanceState.getBoolean(KEY_NEED_SET_APP_THEME, true);
        } else {
            needSetAppTheme = true;
        }
        switchTheme.setChecked(getSwitchStatus(false));
    }

    private static int getAppTheme(Activity activity, int codeStyle) {
        return codeStyleToStyleId(getCodeStyle(activity, codeStyle));
    }

    private static int codeStyleToStyleId(int codeStyle) {
        if (codeStyle == APP_THEME_DARK_STYLE) {
            return AppCompatDelegate.MODE_NIGHT_YES;
        }
        return AppCompatDelegate.MODE_NIGHT_NO;
    }

    // Сохранение настроек
    private void saveSettings(int codeStyle, boolean switchStatus) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        // Настройки сохраняются посредством специального класса editor.
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(APP_THEME, codeStyle);
        editor.putBoolean(APP_SWITCH_STATUS, switchStatus);
        editor.apply();
    }

    // Чтение настроек, параметр «тема»
    private static int getCodeStyle(Activity context, int codeStyle) {
        SharedPreferences sharedPref = context.getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPref.getInt(APP_THEME, codeStyle);
    }

    private boolean getSwitchStatus(boolean defaultSwitchStatus) {
        SharedPreferences sharedPref = getSharedPreferences(NAME_SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPref.getBoolean(APP_SWITCH_STATUS, defaultSwitchStatus);
    }

    public static void setTheme(Activity context) {
        AppCompatDelegate.setDefaultNightMode(getAppTheme(context, AppCompatDelegate.MODE_NIGHT_NO));
    }
}