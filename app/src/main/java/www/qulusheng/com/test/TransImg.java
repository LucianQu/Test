package www.qulusheng.com.test;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.InputStream;

public class TransImg extends android.support.v7.widget.AppCompatImageView {
    private Bitmap bitmap = null ;
    public TransImg(Context context) {
        super(context);
        @SuppressLint("ResourceType") InputStream inputStream = getResources().openRawResource(R.drawable.ic_launcher) ;
        bitmap = BitmapFactory.decodeStream(inputStream) ;
    }

    public TransImg(Context context, AttributeSet attrs) {
        super(context, attrs);
        @SuppressLint("ResourceType") InputStream inputStream = getResources().openRawResource(R.drawable.ic_launcher) ;
        bitmap = BitmapFactory.decodeStream(inputStream) ;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint() ;
        paint.setAlpha(100);
        canvas.drawBitmap(bitmap, new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                new Rect(0,0,bitmap.getWidth(), bitmap.getHeight()), paint);
    }
}