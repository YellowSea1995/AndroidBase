package com.example.huanghai91632.androidbase.db.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by huanghai91632 on 2019/2/28.
 */

public class GenerateTableSQLUtil {

   public static List<String> getCreateTableSql(List<String> entityName){
        List<String> sql = new ArrayList<>();
        for (int i = 0; i < entityName.size(); i++) {
            try {
                Class clzz = Class.forName(entityName.get(i));
                sql.add(createTableSQL(clzz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sql;
    }

    public static List<String> getDropTableSql(List<String> entityName){
        List<String> sql = new ArrayList<>();
        for (int i = 0; i < entityName.size(); i++) {
            try {
                Class clzz = Class.forName(entityName.get(i));
                sql.add(dropTableSQL(clzz));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sql;
    }

    private static String dropTableSQL(Class clzz) {
        return "drop table if exists " + clzz.getSimpleName();
    }

    /**
     * 通过实体类生成建表SQL
     * @param clazz
     * @return
     */
    public static String createTableSQL(Class clazz) {

        //实例化一个容器，用来拼接sql语句
        StringBuffer stringBuffer = new StringBuffer();
        //sql语句，第一个字段为_ID 主键自增，这是通用的，所以直接写死
        stringBuffer.append("CREATE TABLE IF NOT EXISTS " + clazz.getSimpleName() + " (" +
                "[id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        //得到实体类中所有的公有属性
        Field[] fields = clazz.getFields();
        //遍历所有的公有属性
        for (Field field : fields) {
            //如果属性不为_id的话，说明是新的字段,AS通过反射获取会自动加上$change和serialVersionUID，应过滤掉
            if (!field.getName().equals("id") && !field.getName().equals("$change") && !field.getName().equals("serialVersionUID")) {
                //得到属性的基本数据类型
                String type = field.getType().getSimpleName();
                //如果是String类型的属性，就把字段类型设置为TEXT
                //如果是int类型的属性，就把字段类型设置为INTEGER
                if (type.equals("int")) {
                    stringBuffer.append(field.getName() + " INTEGER,");
                } else {
                    stringBuffer.append(field.getName() + " TEXT,");
                }
            }
        }
        //将最后的逗号删除
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        //替换成); 表明sql语句结束
        stringBuffer.append(");");
        //返回这条sql语句
        return stringBuffer.toString();
    }

    /**
     * 返回实体类字段名
     * @param t
     * @return
     */
    public static List<String> getEntityFieldName(Class<?> t) {
        List<String> list = new ArrayList<>();
        Field[] fields = t.getDeclaredFields();
        for (Field field : fields) {
            if (!field.getName().equals("id") && !field.getName().equals("$change") && !field.getName().equals("serialVersionUID"))
                list.add(field.getName());
        }
        return list;
    }
}
