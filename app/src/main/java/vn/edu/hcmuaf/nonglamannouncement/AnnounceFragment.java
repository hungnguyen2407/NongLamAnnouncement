package vn.edu.hcmuaf.nonglamannouncement;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/*
Xu ly hien thi cho trang thong bao
 */
public class AnnounceFragment extends Fragment {

    private View announceView;
    private Activity mainActivity;
    private ArrayAdapter adapter;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        //Lay activity
        mainActivity = getActivity();

        //Khoi tao view
        announceView = inflater.inflate(R.layout.announcement_layout, container, false);

        //Lay list view
        listView = announceView.findViewById(R.id.list_view);

        //Demo hien thi
        listViewHandler();

        return announceView;
    }

    /*
    Gan danh sach cac item vao list view
     */
    private void listViewHandler() {

        ArrayList<String> array = new ArrayList<>();
        array.add("Item 1");
        array.add("Item 2");
        array.add("Item 3");
        array.add("Item 3");
        array.add("Item 3");
        array.add("Item 3");
        array.add("Item 3");
        array.add("Item 3");


        adapter = new ArrayAdapter(mainActivity, R.layout.item_custom, array);

        listView.setAdapter(adapter);

    }
}
