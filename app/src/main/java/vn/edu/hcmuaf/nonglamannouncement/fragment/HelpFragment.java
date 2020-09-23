package vn.edu.hcmuaf.nonglamannouncement.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import vn.edu.hcmuaf.nonglamannouncement.R;

public class HelpFragment extends Fragment {
    private View helpView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        helpView = inflater.inflate(R.layout.fragment_help, container, false);
        return helpView;
    }
}
