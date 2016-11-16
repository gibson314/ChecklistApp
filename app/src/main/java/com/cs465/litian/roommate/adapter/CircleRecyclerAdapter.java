package com.cs465.litian.roommate.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cs465.litian.roommate.R;

import me.khrystal.library.widget.CircleRecyclerView;

/**
 * Created by litia on 11/15/2016.
 */

public class CircleRecyclerAdapter extends CircleRecyclerView.Adapter<VH>{
    private String[] cg = null;
    private MyOnItemClickListener itemClickListener;
    public CircleRecyclerAdapter(String [] s) {
        this.cg = s;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH h = null;

        h = new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_h, parent, false));

        return h;
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        final VH myViewHolder = holder;
        holder.tv.setText(cg[position % cg.length]);
//            Glide.with(getContext())
//                    .load(mImgList.get(position % mImgList.size()))
//                    .bitmapTransform(new CropCircleTransformation(getContext()))
//                    .into(holder.iv);

        if (itemClickListener != null) {
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemClickListener.OnItemClickListener(myViewHolder.itemView, myViewHolder.getLayoutPosition());
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
    /**
     * 列表点击事件
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(MyOnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }


    /**
     * item点击接口
     */
    public interface MyOnItemClickListener {
        void OnItemClickListener(View view, int position);
    }




}
class VH extends RecyclerView.ViewHolder {

    TextView tv;
    //ImageView iv;

    public VH(View itemView) {
        super(itemView);
        tv = (TextView) itemView.findViewById(R.id.item_text);
        //iv = (ImageView) itemView.findViewById(R.id.item_img);
    }
}


