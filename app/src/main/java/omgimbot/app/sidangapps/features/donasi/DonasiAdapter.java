package omgimbot.app.sidangapps.features.donasi;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import omgimbot.app.sidangapps.App;
import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.Utils.Utils;
import omgimbot.app.sidangapps.features.donatur.model.Donasi;
import omgimbot.app.sidangapps.features.taman_baca.buku.model.Buku;


public class DonasiAdapter extends RecyclerView.Adapter<DonasiAdapter.ViewHolder> {
    public List<Donasi> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    String classname;
//    private DonasiAdapter.onSelected listener;

//    public interface onSelected {
//        void onDonasi(Buku data);
//    }


    public DonasiAdapter(List<Donasi> data, Activity context, String classname) {
        this.ruts = data;
        this.context = context;
        this.classname = classname;
//        this.listener = listener ;
    }

    @Override
    public DonasiAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riwayat_donasi, parent, false);
        DonasiAdapter.ViewHolder viewHolder = new DonasiAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Donasi data = ruts.get(position);

        holder.mJudul.setText(data.getJudul());
        holder.mJumlah.setText(String.valueOf(data.getJumlah()));
//        holder.mTamanBaca.setText("Taman Baca : " + data.getTamanBaca());
        holder.mPengiriman.setText(data.getPengiriman());
        holder.mTime.setText(Utils.convertMongoDate(data.getCreated_at()));
        if (classname.equals("DashboardDonaturActivity")) {
            holder.layoutButton.setVisibility(View.GONE);
            holder.layoutStatus.setVisibility(View.VISIBLE);
            if (data.getStatus().equals("Dikirim")) {
                holder.indicator.setImageResource(R.drawable.shape_indicator_orange);
                holder.mStatus.setText(data.getStatus());
            } else {
                holder.indicator.setImageResource(R.drawable.shape_indicator_active);
                holder.mStatus.setText(data.getStatus());
            }

        } else {
            holder.layoutButton.setVisibility(View.VISIBLE);
            holder.layoutStatus.setVisibility(View.GONE);

            if (data.getStatus().equals("Dikirim")) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mSubmit.setBackground(App.getApplication().getDrawable(R.drawable.button_confirm));
                }
                holder.mSubmit.setEnabled(true);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    holder.mSubmit.setBackground(App.getApplication().getDrawable(R.drawable.button_confirm));
                }
                holder.mSubmit.setEnabled(false);
            }
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
        TextView mJudul, mJumlah, mTamanBaca, mPengiriman, mStatus, mTime;
        ImageView icon_image, indicator;
        LinearLayout layoutButton, layoutStatus;
        Button mSubmit, mEdit, mDonasi;


        ViewHolder(View view) {
            super(view);
//            onClick = view.findViewById(R.id.main_layout);
            mJudul = view.findViewById(R.id.mJudulBuku);
            mJumlah = view.findViewById(R.id.mJumlah);
            mStatus = view.findViewById(R.id.mStatus);
            mTime = view.findViewById(R.id.mTime);
//            mTamanBaca = view.findViewById(R.id.mTamanBaca);
            icon_image = view.findViewById(R.id.icon_image);
            indicator = view.findViewById(R.id.indicator);
            mPengiriman = view.findViewById(R.id.mPengiriman);
            layoutButton = view.findViewById(R.id.layoutButton);
            layoutStatus = view.findViewById(R.id.layoutStatus);
            mSubmit = view.findViewById(R.id.mSubmit);
        }

    }


}
