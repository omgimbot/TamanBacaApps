package omgimbot.app.sidangapps.features.pengaduan.list_pengaduan;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.pengaduan.model.Pengaduan;


public class PengaduanAdapter extends RecyclerView.Adapter<PengaduanAdapter.ViewHolder> {
    public List<Pengaduan> ruts;
    Activity context;

    public PengaduanAdapter(List<Pengaduan> data, Activity context) {
        this.ruts = data;
        this.context = context;
    }

    @Override
    public PengaduanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_aduan, parent, false);
        PengaduanAdapter.ViewHolder viewHolder = new PengaduanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final PengaduanAdapter.ViewHolder holder, final int position) {
        final Pengaduan data = ruts.get(position);
        holder.mNama.setText(data.getJudul());
        holder.mDeskripsi.setText(data.getDeskripsi());
        if (data.getStatus().startsWith("Menunggu Konfirmasi")) {
            holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
            holder.mStatus.setText(data.getStatus());
        } else {
            holder.indicator.setImageResource(R.drawable.shape_indicator_active);
            holder.mStatus.setText(data.getStatus());
        }

    }

    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout onClick;
        TextView mNama, mKategori,mDeskripsi,mStatus;
        ImageView icon_image , indicator;
        Button mDelete , mEdit ,mDonasi;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mDeskripsi = view.findViewById(R.id.mDeskripsi);
            mStatus = view.findViewById(R.id.mStatus);
            icon_image = view.findViewById(R.id.icon_image);
            indicator = view.findViewById(R.id.indicator);

        }

    }
}
