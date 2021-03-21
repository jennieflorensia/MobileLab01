package jennie.umn.ac.musicplayer.ListLagu;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.LinkedList;

import jennie.umn.ac.musicplayer.R;

public class DaftarLaguAdapter extends
        RecyclerView.Adapter<DaftarLaguAdapter.ItemLaguViewHolder> {
    private ArrayList<LaguSource> mDaftarLagu;
    private LayoutInflater mInflater;
    private Context mContext;

    public DaftarLaguAdapter(Context context,
                             ArrayList<LaguSource> daftarLagu) {
        this.mContext = context;
        this.mDaftarLagu = daftarLagu;
        this.mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemLaguViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.music_list_layout,
                parent, false);
        return new ItemLaguViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemLaguViewHolder holder,
                                 int position) {
        LaguSource mSumberLagu = mDaftarLagu.get(position);
        holder.tvTitle.setText(mSumberLagu.getTitle());

        byte[] image = getSongThumbnail(mSumberLagu.getPath());

        if(image != null)
        {
            Glide.with(mContext).asBitmap().load(image).into(holder.thumbnailLagu);
        }
        else
        {
            Glide.with(mContext).load(R.drawable.songthumbnail).into(holder.thumbnailLagu);
        }

        //holder.tvUri.setText(mSumberLagu.getVideoURI());
        //holder.thumbnailLagu.setVideoURI(
                //Uri.parse(mSumberLagu.getVideoURI()));
        //holder.kotakVideo.seekTo(100);
    }

    @Override
    public int getItemCount() {
        return mDaftarLagu.size();
    }

    class ItemLaguViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        private ImageView thumbnailLagu;
        private TextView tvTitle;
        private DaftarLaguAdapter mAdapter;
        public int mPosisi;
        private LaguSource mSumberLagu;
        public ItemLaguViewHolder(@NonNull View itemView,
                                   DaftarLaguAdapter adapter) {
            super(itemView);
            mAdapter = adapter;
            thumbnailLagu = (ImageView) itemView.findViewById(R.id.thumbnailLagu);
            tvTitle = (TextView) itemView.findViewById(R.id.judulLagu);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            mPosisi = getLayoutPosition();
            mSumberLagu = mDaftarLagu.get(mPosisi);
            Intent detailIntent = new Intent(mContext,
                    DetailLaguActivity.class);

            detailIntent.putExtra("position", mPosisi);
            mContext.startActivity(detailIntent);
        }
    }

    private byte[] getSongThumbnail(String uri)
    {
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}
