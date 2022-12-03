package com.teamfive.fridge;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.firestore.FirebaseFirestore;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class KeyActivity extends AppCompatActivity {


    private FirebaseFirestore db;
    private Button logout;
    private TextView encodeTextView;
    private TextView decodeTextView;
    private String encodeStr="";
    private String decodeStr="";
    private Button copyButton;
    private Button editButton;
    private EditText encodeEdit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.key_activity);

        db = FirebaseFirestore.getInstance();

        encodeTextView = (TextView)findViewById(R.id.encode_text);
        copyButton = (Button)findViewById(R.id.copy_button);
        editButton = (Button)findViewById(R.id.setting_edit_button);

        String user = SaveSharedPreferences.getUserName(KeyActivity.this);

        encodeTextView.setText(SaveSharedPreferences.getCryptoUserName(KeyActivity.this));

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar3);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        copyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", SaveSharedPreferences.getCryptoUserName(KeyActivity.this));
                clipboard.setPrimaryClip(clip);
                Toast.makeText(view.getContext(), "클립보드에 복사되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PopupActivity.class);
                intent.putExtra("data", SaveSharedPreferences.getCryptoUserName(KeyActivity.this));
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.w("KeyActivity", "넘어왔어요~");
        //Log.w("SettingActivity","resultCode="+resultCode+" requestC")
/*
        Toast.makeText(this, "넘어왔어요.?!?!?", Toast.LENGTH_SHORT).show();
        encodeTextView.setText("넘어왔어요~");


        String result = data.getStringExtra("changeText");
        if (result != null) {
            encodeTextView.setText(result);
        }*/

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("changeText");
                Log.w("KeyActivity","Conditions All Ok");

                if (result != null) {

                    String decode = null;

                    try {
                        decode = AES256.AES_Decode(result);
                    } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                        e.printStackTrace();
                    }

                    if (decode == null){
                        Toast.makeText(this, "유효하지 않은 냉장고 고유키입니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }else {
                        try {
                            if (db.collection("items").document(decode.trim()) == null) {
                                Toast.makeText(this, "유효하지 않은 냉장고 고유키입니다.", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        } catch (NullPointerException e) {
                            Toast.makeText(this, "유효하지 않은 냉장고 고유키입니다.", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            return;
                        } catch (IllegalArgumentException e){
                            Toast.makeText(this, "유효하지 않은 냉장고 고유키입니다.", Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                            return;
                        }

                    }
                    SaveSharedPreferences.setCryptoUser(KeyActivity.this, result);

                    Log.w("SettingActivity","result is not null "+result);

                    try {
                        SaveSharedPreferences.setKeyForDB(KeyActivity.this, SaveSharedPreferences.getCryptoUserName(KeyActivity.this));
                    } catch (InvalidAlgorithmParameterException | NoSuchPaddingException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | InvalidKeyException e) {
                        Toast.makeText(this, "냉장고 고유키가 변경에 실패했습니다.", Toast.LENGTH_SHORT).show();
                    }
                    encodeTextView.setText(SaveSharedPreferences.getCryptoUserName(KeyActivity.this));
                    Toast.makeText(this, "냉장고 고유키가 변경되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Log.w("KeyActivity","result is null");
                }
            }
        }
    }
}