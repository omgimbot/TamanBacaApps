package omgimbot.app.sidangapps.features.taman_baca.buku.listbuku;

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
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;
import omgimbot.app.sidangapps.features.taman_baca.model.TamanBaca;

public class BukuAdapter extends RecyclerView.Adapter<BukuAdapter.ViewHolder> {
    public List<Buku> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private BukuAdapter.onSelected listener;

    public interface onSelected {
        void edit(Buku data);
        void delete(Buku data);
    }

    public BukuAdapter(List<Buku> data, Activity context, BukuAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener ;
    }

    @Override
    public BukuAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buku, parent, false);
        BukuAdapter.ViewHolder viewHolder = new BukuAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final BukuAdapter.ViewHolder holder, final int position) {
        final Buku data = ruts.get(position);
        holder.mNama.setText(data.getJudul());
        holder.mDeskripsi.setText(data.getDeskripsi());
        holder.mKategori.setText(data.getKategori());

        holder.mDelete.setOnClickListener(View -> listener.delete(data));
        holder.mEdit.setOnClickListener(View -> listener.edit(data));
        holder.mDonasi.setVisibility(View.GONE);
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
        TextView mNama, mKategori,mDeskripsi;
        ImageView icon_image;
        Button mDelete , mEdit ,mDonasi;

        ViewHolder(View view) {
            super(view);
            mNama = view.findViewById(R.id.mNama);
            mKategori = view.findViewById(R.id.mKategori);
            mDeskripsi = view.findViewById(R.id.mDeskripsi);
            icon_image = view.findViewById(R.id.icon_image);
            mDelete = view.findViewById(R.id.mDelete);
            mDonasi = view.findViewById(R.id.mDonasi);
            mEdit = view.findViewById(R.id.mEdit);

        }

    }
}
