package com.example.budalajedna.nfctryout.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;

import com.example.budalajedna.nfctryout.R;

public class MenuItems extends View {

    private Paint paint;
    private Bitmap buttonShape;
    private Bitmap facebook;
    private Bitmap whatsApp;
    private Bitmap gmail;

    private double width;
    private float buttonWidth=0;
    private float buttonHeight=0;

    private int pictureSize;

    public MenuItems(Context context) {
        super(context);
        init(null);
    }

    public MenuItems(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public MenuItems(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public MenuItems(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set) {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                width = getWidth() * 0.5;
                buttonHeight=getHeight();
                pictureSize = (int) (width * 0.4);
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        buttonShape = BitmapFactory.decodeResource(getResources(), R.drawable.ic_debug);

        facebook = BitmapFactory.decodeResource(getResources(), R.mipmap.bic_facebook);
        whatsApp = BitmapFactory.decodeResource(getResources(), R.mipmap.bic_whatsapp);
        gmail = BitmapFactory.decodeResource(getResources(), R.mipmap.bic_gmail);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap buttonShape = this.buttonShape;
        buttonWidth=buttonShape.getWidth();
        Bitmap bitmap=Bitmap.createBitmap(resize(filter(facebook, buttonShape), pictureSize, pictureSize));
        canvas.drawBitmap(resize(filter(facebook, buttonShape), pictureSize, pictureSize), getX(getWidth() / 2, 8, width, 0), getY(getHeight() / 2, 8, width, 0), paint);
        buttonShape = rotate(buttonShape, -45);
        canvas.drawBitmap(resize(filter(whatsApp, buttonShape), pictureSize, pictureSize), getX(getWidth() / 2, 8, width, 1), getY(getHeight() / 2, 8, width, 1), paint);
        buttonShape = rotate(buttonShape, -45);
        canvas.drawBitmap(resize(filter(gmail, buttonShape), pictureSize, pictureSize), getX(getWidth() / 2, 8, width, 2), getY(getHeight() / 2, 8, width, 2), paint);
    }

    private float getX(float xc, int m, double width, int i) {
        float correction=0;
        if(i==0){
            correction=(float)Math.sin(Math.PI/4)*(64f/117f)*buttonWidth;
            return correction;
        }
        else{
            correction = (64f/117f)*buttonWidth;
            return correction;
        }
        //return (float) (xc + Math.cos((i+1) * 2 *Math.PI / m) * 1 / 2 * width)-correction;

    }

    private float getY(float yc, int m, double width, int i) {
        float correction=0;
        if(i==0){
            correction=(float)Math.sin(Math.PI/4)*(64f/117f)*buttonWidth;
            return correction;
        }
        //return (float) (yc - Math.sin((i+1)*2*Math.PI / m) * 1 / 2 * width)+correction;
        return 0;
    }

    private Bitmap filter(Bitmap source, Bitmap filter) {
        Bitmap filtered = Bitmap.createBitmap(source.getWidth(), source.getHeight(), source.getConfig());

        source = resize(source, filter.getWidth(), filter.getHeight());

        Paint paint = new Paint();
        Canvas canvas = new Canvas(filtered);

        canvas.drawBitmap(source, 0, 0, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        canvas.drawBitmap(filter, 0, 0, paint);

        return filtered;
    }

    private Bitmap resize(Bitmap image, int reqWidth, int reqHeight) {
        Matrix matrix = new Matrix();

        RectF src = new RectF(0, 0, image.getWidth(), image.getHeight());
        RectF req = new RectF(0, 0, reqWidth, reqHeight);

        matrix.setRectToRect(src, req, Matrix.ScaleToFit.CENTER);

        return Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
    }

    private Bitmap rotate(Bitmap image, int angle) {

        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(image, 0, 0, image.getWidth(), image.getHeight(), matrix, true);
    }
}
