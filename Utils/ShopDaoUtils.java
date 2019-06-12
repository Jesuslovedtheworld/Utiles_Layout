package com.baidu.mall.beandaoutils;

import android.content.Context;

import com.baidu.bean.ShopCollectionDao;
import com.baidu.mall.dao.DaoSession;
import com.baidu.mall.dao.ShopCollectionDaoDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class ShopDaoUtils {

    /**
     * 添加数据
     * @param context
     * @param collectionDao
     */
    public static void  insertData(Context context,ShopCollectionDao collectionDao){
       ShopManager.getDaoSession(context).getShopCollectionDaoDao().insert(collectionDao);
    }

    /***
     * 将数据实体实物添加至数据库
     * @param context
     * @param list
     */
    public static void insertData(Context context, List<ShopCollectionDao> list){
        if (null == list || list.size() < 0){
                return;
        }
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().insertInTx(list);
    }

    /**
     * 添加数据至数据库，如果存在，将原来的数据覆盖
     *      * 内部代码判断了如果存在就update(entity);不存在就insert(entity)；
     * @param context
     * @param collectionDao
     */
    public static void saveData(Context context,ShopCollectionDao collectionDao){
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().save(collectionDao);
    }

    /**
     *
     *
     *删除数据至数据库
     * @param context
     * @param collectionDao
     */
    public static void deletaData(Context context,ShopCollectionDao collectionDao){
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().delete(collectionDao);
    }


    /**
     * 根据id删除数据至数据库
     *
     * @param context
     * @param id      删除具体内容
     */
    public static void deleteByKeyData(Context context, long id) {
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().deleteByKey(id);
        //       DbManager.getDaoSession(context, DB_NAME, PASSWPRD).getUserDao().deleteByKey(id);
    }
    /**
     * 删除全部数据
     *
     * @param context
     */
    public static void deleteAllData(Context context) {
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().deleteAll();
        //        DbManager.getDaoSession(context, DB_NAME, PASSWPRD).getUserDao().deleteAll();
    }

    /**
     * 更新数据库
     *
     * @param context
     */
    public static void updateData(Context context, ShopCollectionDao collectionDao) {
        ShopManager.getDaoSession(context).getShopCollectionDaoDao().update(collectionDao);
//        DbManager.getDaoSession(context, DB_NAME, PASSWPRD).getUserDao().update(user);
    }

    /**
     * 查询所有数据
     *
     * @param context
     * @return
     */
    public static List<ShopCollectionDao> queryAll(Context context) {
        QueryBuilder<ShopCollectionDao> builder = ShopManager.getDaoSession(context).getShopCollectionDaoDao().queryBuilder();

        //       QueryBuilder<User> builder = DbManager.getDaoSession(context, DB_NAME, PASSWPRD).getUserDao().queryBuilder();

        return builder.build().list();
    }

    /**
     * 根据id，其他的字段类似
     *
     * @param context
     * @param id
     * @return
     */
    public static List<ShopCollectionDao> queryForId(Context context, long id) {
        QueryBuilder<ShopCollectionDao> builder = ShopManager.getDaoSession(context).getShopCollectionDaoDao().queryBuilder();
        //       QueryBuilder<User> builder = DbManager.getDaoSession(context, DB_NAME, PASSWPRD).getUserDao().queryBuilder();
        /**
         * 返回当前id的数据集合,当然where(这里面可以有多组，做为条件);
         * 这里build.list()；与where(StudentDao.Properties.Id.eq(id)).list()结果是一样的；
         * 在QueryBuilder类中list()方法return build().list();
         *
         */
        // Query<Student> build = builder.where(StudentDao.Properties.Id.eq(id)).build();
        // List<Student> list = build.list();
        return builder.where(ShopCollectionDaoDao.Properties.Id.eq(id)).list();
    }


}
