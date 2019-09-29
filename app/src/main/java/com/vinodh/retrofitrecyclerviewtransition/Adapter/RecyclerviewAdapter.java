package com.vinodh.retrofitrecyclerviewtransition.Adapter;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.vinodh.retrofitrecyclerviewtransition.Model.MarvelHeros;
import com.vinodh.retrofitrecyclerviewtransition.NewScreenActivity;
import com.vinodh.retrofitrecyclerviewtransition.R;
import com.vinodh.retrofitrecyclerviewtransition.Utils.AppConstants;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by VINODH KUMAR
 */
public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {

    private Context mContext;
    public static List<MarvelHeros> herosList;

    public RecyclerviewAdapter(Context mContext, List<MarvelHeros> herosList) {
        this.mContext = mContext;
        this.herosList = herosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_adapter,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        MarvelHeros heros = herosList.get(position);
        holder.mName.setText(heros.getName());
        holder.mAuthor.setText(heros.getCreatedby());
        holder.mTeam.setText(heros.getTeam());
        holder.mDesc.setText(heros.getBio());

        Glide.with(mContext)
                .asBitmap()
                .load(heros.getImageurl())
                .into(holder.imageThumbNail);

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions options = null;
                Intent intent = new Intent(mContext, NewScreenActivity.class);
                intent.putExtra(AppConstants.POSITION, position);
                Pair[] pairs = new Pair[6];
                pairs[0] =new Pair(holder.imageThumbNail, "imageTransition");
                pairs[1] = new Pair(holder.mName, "nameTransition");
                pairs[2] = new Pair(holder.mDesc, "descTransition");
                pairs[3] = new Pair(holder.mAuthor, "authorTransition");
                pairs[4] = new Pair(holder.mTeam, "teamTransition");
                pairs[5] = new Pair(holder.bkgImageThumb, "imagebcgTransition");
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    options = ActivityOptions.makeSceneTransitionAnimation((Activity)mContext,pairs);
                }else {
                    mContext.startActivity(intent);
                }
                mContext.startActivity(intent, options.toBundle());
            }
        });
    }

    @Override
    public int getItemCount() {
        return herosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout parentLayout;
        private ImageView bkgImageThumb;
        private CircleImageView imageThumbNail;
        private TextView mName,mDesc,mAuthor,mTeam;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parentLayout = (RelativeLayout) itemView.findViewById(R.id.parent_id);
            imageThumbNail = (CircleImageView) itemView.findViewById(R.id.imageThump_id);
            bkgImageThumb = (ImageView) itemView.findViewById(R.id.bkg_thumb);
            mName = (TextView) itemView.findViewById(R.id.name_id);
            mAuthor = (TextView) itemView.findViewById(R.id.author_id);
            mTeam = (TextView) itemView.findViewById(R.id.team_id);
            mDesc = (TextView) itemView.findViewById(R.id.desc_id);
        }
    }
}
