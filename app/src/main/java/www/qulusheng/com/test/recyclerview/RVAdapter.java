package www.qulusheng.com.test.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.lang.ref.WeakReference;
import java.util.List;

import www.qulusheng.com.test.R;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.ViewHolder> {
    private List<Bitmap> list ;
    private Context mContext ;

    public RVAdapter(Context mContext,List<Bitmap> list ) {
        this.list = new WeakReference<List<Bitmap>>(list).get();
        this.mContext = new WeakReference<Context>(mContext).get();
    }

    @Override
    public RVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_rv, parent, false) ;
        return new ViewHolder(view);
    }

    @Override
    public void onViewRecycled(ViewHolder holder) {
        super.onViewRecycled(holder);
        //Log.e("Lucian",holder.iv.getTag().toString()) ;
    }

    @Override
    public void onBindViewHolder(RVAdapter.ViewHolder holder, int position) {
        holder.iv.setImageBitmap(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null?0:list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv;
        public ViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv) ;

        }
    }
}