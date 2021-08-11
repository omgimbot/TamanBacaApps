package omgimbot.app.sidangapps.features.taman_baca;

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
import omgimbot.app.sidangapps.Utils.Utils;
import omgimbot.app.sidangapps.features.taman_baca.model.TamanBaca;


public class TamanBacaAdapter extends RecyclerView.Adapter<TamanBacaAdapter.ViewHolder> {
    public List<TamanBaca> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private TamanBacaAdapter.onSelected listener;
    public interface onSelected {
        void onDownload(TamanBaca data);
    }


    public TamanBacaAdapter(List<TamanBaca> data, Activity context) {
        this.ruts = data;
        this.context = context;
        this.listener = listener ;
    }

    @Override
    public TamanBacaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taman_baca, parent, false);
        TamanBacaAdapter.ViewHolder viewHolder = new TamanBacaAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final TamanBacaAdapter.ViewHolder holder, final int position) {
        final TamanBaca data = ruts.get(position);
        holder.mNama.setText(data.getNamaPerpus());
        holder.mAlamat.setText(data.getAlamat());
        holder.mDeskripsi.setText(data.getDeskripsi());

    }

    @Override
    public int getItemCount() {
        if (ruts == null)
            return 0;
        else
            return ruts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mNama, mAlamat,mDeskripsi;
        ImageView icon_image;


        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mAlamat = view.findViewById(R.id.mAlamat);
            mDeskripsi = view.findViewById(R.id.mDeskripsi);
            icon_image = view.findViewById(R.id.icon_image);

        }

    }


}
