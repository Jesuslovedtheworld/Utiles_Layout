package com.jiyun.bitmap;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.widget.ImageView;

/**
 * Created by $lzj on 2019/3/19.
 */
public class ImageUtils {

    /**
     * 缩放图片
     */
    public static void bitmapScale(Bitmap bitmap, float x, float y, ImageView img2) {
        // 因为要将图片放大，所以要根据放大的尺寸重新创建Bitmap,Android不允许对原图进行处理
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (bitmap.getWidth() * x),
                (int) (bitmap.getHeight() * y), bitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        // 初始化Matrix对象
        Matrix matrix = new Matrix();
        // 根据传入的参数设置缩放比例
        matrix.setScale(x, y);
        // 根据缩放比例，把图片draw到Canvas上
        canvas.drawBitmap(bitmap, matrix,new Paint());
        img2.setImageBitmap(afterBitmap);
    }

    /**
     * 图片旋转
     */
    public static void bitmapRotate(Bitmap baseBitmap,float degrees,ImageView iv_after) {
        // 创建一个和原图一样大小的图片
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth(),
                baseBitmap.getHeight(), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 根据原图的中心位置旋转
        matrix.setRotate(degrees, baseBitmap.getWidth() / 2,
                baseBitmap.getHeight() / 2);
        canvas.drawBitmap(baseBitmap, matrix, new Paint());
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 图片移动
     */
    public static  void bitmapTranslate(Bitmap baseBitmap,float dx, float dy,ImageView iv_after) {
        // 需要根据移动的距离来创建图片的拷贝图大小
        Bitmap afterBitmap = Bitmap.createBitmap(
                (int) (baseBitmap.getWidth() + dx),
                (int) (baseBitmap.getHeight() + dy), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置移动的距离
        matrix.setTranslate(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, new Paint());
        iv_after.setImageBitmap(afterBitmap);
    }

    /**
     * 倾斜图片
     */
    public static void bitmapSkew(Bitmap baseBitmap,float dx, float dy,ImageView iv_after) {
        // 根据图片的倾斜比例，计算变换后图片的大小，
        Bitmap afterBitmap = Bitmap.createBitmap(baseBitmap.getWidth()
                + (int) (baseBitmap.getWidth() * dx), baseBitmap.getHeight()
                + (int) (baseBitmap.getHeight() * dy), baseBitmap.getConfig());
        Canvas canvas = new Canvas(afterBitmap);
        Matrix matrix = new Matrix();
        // 设置图片倾斜的比例
        matrix.setSkew(dx, dy);
        canvas.drawBitmap(baseBitmap, matrix, new Paint());
        iv_after.setImageBitmap(afterBitmap);
    }

    public static void bitmapNoRed(Bitmap mBitmap,ImageView mIvNew){
        Bitmap bitmap = Bitmap.createBitmap(mBitmap.getWidth(), mBitmap.getHeight(), mBitmap.getConfig());
        //去掉红色
       float[] mMatrix = new float[]{
               0.33f, 0.59f, 0.11f, 0, 0,
               0.33f, 0.59f, 0.11f, 0, 0,
               0.33f, 0.59f, 0.11f, 0, 0,
               0, 0, 0, 1, 0,
       };
        //色彩矩阵
        ColorMatrix colorMatrix = new ColorMatrix(mMatrix);
        //画板
        Canvas canvas = new Canvas(bitmap);
        //画笔
        Paint paint = new Paint();
        //给画笔设置颜色过滤器,里面使用色彩矩阵
        paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        //将mBitmap临摹到bitmap上,使用含有色彩矩阵的画笔
        canvas.drawBitmap(mBitmap, 0, 0, paint);
        mIvNew.setImageBitmap(bitmap);
    }
}
