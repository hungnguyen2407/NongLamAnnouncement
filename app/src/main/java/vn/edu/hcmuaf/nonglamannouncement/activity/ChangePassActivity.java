package vn.edu.hcmuaf.nonglamannouncement.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.dao.CustomConnection;
import vn.edu.hcmuaf.nonglamannouncement.model.MemoryName;
import vn.edu.hcmuaf.nonglamannouncement.model.NameOfResources;

public class ChangePassActivity extends AppCompatActivity {
    private Activity changePassActivity;
    private String oldPass, newPass, newPassRepeat;
    private EditText etOldPass, etNewPass, etNewPassRepeat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        changePassActivity = this;
        changPassAcitivityHander();
    }

    private void changPassAcitivityHander() {
        etOldPass = findViewById(R.id.change_pass_et_old_pass);
        etNewPass = findViewById(R.id.change_pass_et_new_pass);
        etNewPassRepeat = findViewById(R.id.change_pass_et_new_pass_repeat);
        oldPass = etOldPass.getText().toString();
        newPass = etNewPass.getText().toString();
        newPassRepeat = etNewPassRepeat.getText().toString();
        Button btnSubmit = findViewById(R.id.change_pass_submit_btn);
        ImageButton btnReturn = findViewById(R.id.change_pass_back_btn);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog logoutDialog = new AlertDialog.Builder(changePassActivity)
                        .setTitle("Xác nhận thay đổi mật khẩu")
                        .setMessage("Bạn chắc chắn muốn thay đổi mật khẩu ? ")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Có", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                checkValid();
                            }
                        })
                        .setNegativeButton("Không", null).show();
                logoutDialog.getButton(AlertDialog.BUTTON_POSITIVE).setBackgroundColor(Color.GRAY);
                logoutDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        });

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void checkValid() {
        closeKeyBoard();
        etOldPass.setError(null);
        etNewPass.setError(null);
        etNewPassRepeat.setError(null);
        oldPass = etOldPass.getText().toString();
        newPass = etNewPass.getText().toString();
        newPassRepeat = etNewPassRepeat.getText().toString();
        if (TextUtils.isEmpty(oldPass)) {
            etOldPass.setError(getResources().getString(R.string.error_field_required));
        }
        if (TextUtils.isEmpty(newPassRepeat)) {
            etNewPassRepeat.setError(getResources().getString(R.string.error_field_required));
        }

        if (TextUtils.isEmpty(newPass)) {
            etNewPass.setError(getResources().getString(R.string.error_field_required));
        } else if (newPass.length() <= 4) {
            etNewPass.setError(getResources().getString(R.string.error_invalid_password));
            Toast.makeText(getApplicationContext(), "Mật khẩu cần dài hơn 4 kí tự", Toast.LENGTH_LONG).show();
        } else if (!newPass.equals(newPassRepeat)) {
            etNewPassRepeat.setError(getResources().getString(R.string.error_not_match_password));
        } else {
            new ChangePassTask().execute();
        }
    }

    private void closeKeyBoard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private class ChangePassTask extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Void... voids) {

            SharedPreferences sp = getSharedPreferences(MemoryName.TEMP_DATA.toString(), Context.MODE_PRIVATE);
            CustomConnection.makePUTConnectionWithParameter(changePassActivity,
                    CustomConnection.URLPostfix.CHANGE_PASS,
                    NameOfResources.CHANGE_PASS_MESSAGE.toString(),
                    sp.getString(NameOfResources.USER_ID.toString(), ""),
                    oldPass, newPass);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Log.d("response", sp.getString(NameOfResources.CHANGE_PASS_MESSAGE.toString(), "false"));
            return Boolean.valueOf(sp.getString(NameOfResources.CHANGE_PASS_MESSAGE.toString(), "false"));
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean)
                Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thành công", Toast.LENGTH_LONG).show();
            else
                Toast.makeText(getApplicationContext(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_LONG).show();


        }
    }


}
