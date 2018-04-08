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
    private LayoutInflater inflater;
    private Context context;

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
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.announce_row, null);
        TextView text = vi.findViewById(R.id.text);
        text.setText(listAnnounces.get(position).getContent());
        return vi;
    }
}
