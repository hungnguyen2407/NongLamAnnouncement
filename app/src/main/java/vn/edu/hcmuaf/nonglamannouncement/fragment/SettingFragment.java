package vn.edu.hcmuaf.nonglamannouncement.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.activity.ChangePassActivity;

public class SettingFragment extends Fragment {

    private View settingView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        settingView = inflater.inflate(R.layout.fragment_setting, container);
        settingViewHandler();
        return super.onCreateView(inflater, container, savedInstanceState);

    }

    private void settingViewHandler() {
        Button changePassBtn = settingView.findViewById(R.id.setting_change_pass_btn);
        changePassBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent changePassIntent = new Intent(getActivity(), ChangePassActivity.class);
                startActivity(new Intent(getActivity(), ChangePassActivity.class));
            }
        });
    }
}
