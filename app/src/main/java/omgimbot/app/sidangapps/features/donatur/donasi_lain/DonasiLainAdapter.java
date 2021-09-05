package omgimbot.app.sidangapps.features.donatur.donasi_lain;

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

import java.util.ArrayList;
import java.util.List;

import omgimbot.app.sidangapps.R;
import omgimbot.app.sidangapps.features.auth.login.model.Users;


public class DonasiLainAdapter extends RecyclerView.Adapter<DonasiLainAdapter.ViewHolder> {
    public List<Users> ruts;
    Activity context;
    AlertDialog.Builder dialogBuilder;
    AlertDialog alertDialog;
    private DonasiLainAdapter.onSelected listener;

    public interface onSelected {
        void onDonasi(Users data);
    }

    public DonasiLainAdapter(List<Users> data, Activity context, DonasiLainAdapter.onSelected listener) {
        this.ruts = data;
        this.context = context;
        this.listener = listener ;
    }

    @Override
    public DonasiLainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_taman_baca, parent, false);
        DonasiLainAdapter.ViewHolder viewHolder = new DonasiLainAdapter.ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("NewApi")
    @Override
    public void onBindViewHolder(final DonasiLainAdapter.ViewHolder holder, final int position) {
        final Users data = ruts.get(position);
        holder.mNama.setText(data.getNama());
        holder.mNotlp.setText(data.getNoTelpon());
        holder.mAlamat.setText(data.getAlamat());
        holder.mDonasi.setOnClickListener(View -> listener.onDonasi(data));
    }

    public void setFilter(ArrayList<Users> filterList) {
        ruts = new ArrayList<>();
        ruts.addAll(filterList);
        notifyDataSetChanged();
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
        TextView mNama, mNotlp,mAlamat;
        ImageView icon_image;
        Button mDelete , mEdit , mDonasi;


        ViewHolder(View view) {
            super(view);
//            onClick = view.findViewById(R.id.main_layout);
            mNama = view.findViewById(R.id.mNama);
            mNotlp = view.findViewById(R.id.mNotlp);
            mAlamat = view.findViewById(R.id.mAlamat);

            mDonasi = view.findViewById(R.id.mDonasi);

        }

    }


}
