package id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.R;
import id.sch.smktelkom_mlg.project.xiirpl201112131.kosonline.model.kosModel;

/**
 * Created by putri aditya on 11/1/2016.
 */
public class kosAdapter extends RecyclerView.Adapter<kosAdapter.ViewHolder> {
    ArrayList<kosModel> kosList;
    private OnClickListener listener;
    private String mineId;

    public kosAdapter(ArrayList<kosModel> kosList, OnClickListener listener, String mineId) {
        this.kosList = kosList;
        this.listener = listener;
        this.mineId = mineId;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        kosModel kos = kosList.get(position);
        holder.click(kos, listener);
        holder.tvJudul.setText(kos.getKosNama());
        holder.tvDes.setText(kos.getKosAlamat());
        if (kos.getIdPemilik().equals(mineId))
            holder.btnDelete.setVisibility(View.VISIBLE);
        else
            holder.btnDelete.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        if (kosList != null)
            return kosList.size();
        return 0;
    }

    public interface OnClickListener {
        void onClick(kosModel kos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvJudul, tvDes;
        Button btnDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDes = (TextView) itemView.findViewById(R.id.textViewDeskripsi);
            btnDelete = (Button) itemView.findViewById(R.id.buttonDelete);
        }

        public void click(final kosModel kos, final OnClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(kos);
                }
            });
        }
    }
}

