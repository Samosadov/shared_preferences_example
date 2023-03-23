package ru.synergy.sharedpreferencesexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText et;
    Button btnSave, btnLoad;
    SharedPreferences sharedPreferences;
    final String SAVED_TEXT = "save text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.editText);
        btnLoad = findViewById(R.id.btnLoad);
        btnSave = findViewById(R.id.btnSave);

//        btnSave.setOnClickListener(this);
//        btnLoad.setOnClickListener(this);
        loadText();

        et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                saveText();
                Log.d(SAVED_TEXT, et.getText().toString());
                return true;
            }
        });
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSave:
//                saveText();
//                break;
//            case R.id.btnLoad:
//                loadText();
//                break;
//            default:
//                break;
//        }
//    }

    private void loadText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        String saveText = sharedPreferences.getString(SAVED_TEXT, "There is noting...");
        et.setText(saveText);
        Toast.makeText(this, "Text was loaded", Toast.LENGTH_LONG).show();
    }

    private void saveText() {
        sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(SAVED_TEXT, et.getText().toString());
        ed.commit();
        Toast.makeText(this, "Text was saved", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }
}