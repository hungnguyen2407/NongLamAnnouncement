package vn.edu.hcmuaf.nonglamannouncement.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import vn.edu.hcmuaf.nonglamannouncement.R;
import vn.edu.hcmuaf.nonglamannouncement.model.Announce;

public class AnnounceAdapter extends BaseAdapter {

    private ArrayList<Announce> listAnnounces;
    private Announce announce;
    private LayoutInflater inflater;
    private Context context;
    private TextView tvHeader, tvAuthor, tvMore;
    public AnnounceAdapter(Context context, int resource, List<Announce> listAnnounce) {
        this.context = context;
        this.listAnnounces = (ArrayList) listAnnounce;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return listAnnounces.size();
    }

    @Override
    public Announce getItem(int position) {
        return listAnnounces.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null)
            view = inflater.inflate(R.layout.announce_row, null);
        tvHeader = view.findViewById(R.id.announce_tv_header);
        tvAuthor = view.findViewById(R.id.announce_tv_author);
        announce = listAnnounces.get(position);


        tvHeader.setText(announce.getHeader());
        tvAuthor.setText(announce.getAuthor());
        return view;
    }
}
