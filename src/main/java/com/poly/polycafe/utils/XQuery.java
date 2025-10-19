package com.poly.polycafe.utils;

import com.poly.polycafe.entity.Users;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Lớp tiện ích hỗ trợ truy vấn và chuyển đổi sang đối tượng
 *
 * @author NghiemN
 * @version 1.0
 */
public class XQuery {

    /**
     * Truy vấn 1 đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> B getSingleBean(Class<B> beanClass, String sql, Object... values) {
        List<B> list = XQuery.getBeanList(beanClass, sql, values);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }

    /**
     * Truy vấn nhiều đối tượng
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param beanClass lớp của đối tượng kết quả
     * @param sql câu lệnh truy vấn
     * @param values các giá trị cung cấp cho các tham số của SQL
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    public static <B> List<B> getBeanList(Class<B> beanClass, String sql, Object... values) {
        List<B> list = new ArrayList<>();
        try {
            ResultSet resultSet = XJdbc.executeQuery(sql, values);
            ResultSetMetaData meta = resultSet.getMetaData();
            for (int i = 1; i <= meta.getColumnCount(); i++) {
                System.out.println(i + "  " + meta.getColumnLabel(i));
            }
            while (resultSet.next()) {
                list.add(XQuery.readBean(resultSet, beanClass));
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    /**
     * Tạo bean với dữ liệu đọc từ bản ghi hiện tại
     *
     * @param <B> kiểu của đối tượng cần chuyển đổi
     * @param resultSet tập bản ghi cung cấp dữ liệu
     * @param beanClass lớp của đối tượng kết quả
     * @return kết quả truy vấn
     * @throws RuntimeException lỗi truy vấn
     */
    private static <B> B readBean(ResultSet resultSet, Class<B> beanClass) throws Exception {
        B bean = beanClass.getDeclaredConstructor().newInstance();
        Method[] methods = beanClass.getDeclaredMethods();

        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("set") && method.getParameterCount() == 1) {
                String column = name.substring(3);
                Class<?> paramType = method.getParameterTypes()[0];

                try {
                    Object value = resultSet.getObject(column);

                    // ⚡ Chuyển đổi kiểu đặc biệt
                    if (value != null) {
                        if (paramType == LocalDateTime.class && value instanceof Timestamp ts) {
                            value = ts.toLocalDateTime();
                        } else if (paramType == LocalDate.class && value instanceof Date dt) {
                            value = dt.toLocalDate();
                        } else if (paramType == Double.class || paramType == double.class) {
                            value = ((Number) value).doubleValue();
                        } else if (paramType == Float.class || paramType == float.class) {
                            value = ((Number) value).floatValue();
                        } else if (paramType == Integer.class || paramType == int.class) {
                            value = ((Number) value).intValue();
                        }
                    }

                    method.invoke(bean, value);
                } 
                catch (SQLException sqlEx) {
                    System.err.printf("[SQL ERROR] Column '%s' → %s%n", column, sqlEx.getMessage());
                } 
                catch (IllegalArgumentException argEx) {
                    System.err.printf("[TYPE MISMATCH] Setter '%s' expected %s but got incompatible type%n",
                            method.getName(),
                            paramType.getSimpleName());
                } 
                catch (InvocationTargetException | IllegalAccessException e) {
                    System.err.printf("[INVOKE ERROR] When setting '%s': %s%n", column, e.getMessage());
                }
            }
        }

        return bean;
    }

    
    public static void main(String[] args) {
        demo1();
        demo2();
    }

    private static void demo1() {
        String sql = "SELECT * FROM Users WHERE Username=? AND Password=?";
        Users user = XQuery.getSingleBean(Users.class, sql, "NghiemN", "123456");
    }

    private static void demo2() {
        String sql = "SELECT * FROM Users WHERE Fullname LIKE ?";
        List<Users> list = XQuery.getBeanList(Users.class, sql, "%Nguyễn %");
    }
}