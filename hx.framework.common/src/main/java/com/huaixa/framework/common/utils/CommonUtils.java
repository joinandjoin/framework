package com.huaixa.framework.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.jdbc.support.JdbcUtils;

/**
 * 常用方法工具类 Title: framework.common CommonUtils.java <br>
 * Description: <br>
 * Date: 2016年9月12日 <br>
 * Copyright (c) 2016 Joinandjoin <br>
 * 
 * @author shilei
 */
public class CommonUtils {

    /**
     * 判读参数是否为数字
     * 
     * @param num
     *            T 某个对象
     * @return boolean 是否为数字；true为数字，false非数字
     * @author shilei
     */
    public static <T> boolean isNumber(T num) {
        if ((num instanceof Number)) {
            return true;
        }
        if ((num instanceof String)) {
            try {
                new BigDecimal((String) num);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    /**
     * 判读参数是否为null
     * 
     * @param obj
     *            T 判读对象
     * @return boolean 是否为null；true为null，false非null
     * @author shilei
     */
    public static <T> boolean isNull(T obj) {
        return null == obj;
    }

    /**
     * 判读参数是否为非null
     * 
     * @param obj
     *            T 判读对象
     * @return boolean 是否为null；false为null，true非null
     * @author shilei
     */
    public static <T> boolean isNotNull(T obj) {
        return null != obj;
    }

    /**
     * 判读参数是否为null或者空
     * 
     * @param obj
     *            T 判读对象
     * @return boolean 是否为null或者空；true为null或者空，false非null且非空
     * @author shilei
     */
    public static <T> boolean isEmpty(T obj) {
        if (obj == null) {
            return true;
        }
        if ((obj instanceof String)) {
            String strObj = (String) obj;
            strObj = strObj.trim();
            return strObj.length() == 0 ? true : strObj.isEmpty() ? true : false;
        }
        if ((obj instanceof Collection)) {
            Collection<?> collectionObj = (Collection<?>) obj;
            return (collectionObj.size() <= 0) || (collectionObj.isEmpty());
        }
        if ((obj instanceof Map)) {
            Map<?, ?> mapObje = (Map<?, ?>) obj;
            return (mapObje.size() <= 0) || (mapObje.isEmpty());
        }
        if (obj.getClass().isArray()) {
            return (Array.getLength(obj) <= 0);
        }
        return false;
    }

    /**
     * 判读参数是否为非null或者非空
     * 
     * @param obj
     *            T 判读对象
     * @return boolean 是否不为null或者不为空；false为null或者空，true非null且非空
     * @author shilei
     */
    public static <T> boolean isNotEmpty(T obj) {
        return !isEmpty(obj);
    }

    /**
     * 判读参数是否为null且为空
     * 
     * @param obj
     *            T 判读对象
     * @return 是否为null且为空 ，true既null且空
     * @author shilei
     */
    public static <T> boolean isNullEmpty(T obj) {
        if (isNull(obj) || isEmpty(obj)) {
            return true;
        }
        return false;
    }

    /**
     * 判读参数是否为非null且为非空
     * 
     * @param obj
     *            T 判读对象
     * @return 是否为非null且为非空 ，true既非null且非空
     * @author shilei
     */
    public static <T> boolean isNotNullEmpty(T obj) {
        return !isNullEmpty(obj);
    }

    /**
     * 将一个输入流转换为字节数组
     * 
     * @param in
     *            InputStream 需要转换为字节数组的输入流
     * @param bufferSize
     *            缓存大小
     * @return byte[] 字节数组 转换后的字节数组
     * @throws IOException
     * @author shilei
     */
    public static byte[] InputStreamTOByte(InputStream in, int bufferSize) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] data = new byte[bufferSize];
        int count = -1;
        while ((count = in.read(data, 0, bufferSize)) != -1) {
            outStream.write(data, 0, count);
        }
        data = null;
        return outStream.toByteArray();
    }

    /**
     * 判读传人参数是否代表一个时间sql类型
     * 
     * @param sqlType
     *            int jdbc定义的sql类型常量
     * @return boolean true是时间类型。
     * @author shilei
     */
    public static boolean isTime(int sqlType) {
        return (92 == sqlType) || (93 == sqlType);
    }

    /**
     * 判读传人参数是否代表一个日期的sql类型
     * 
     * @param sqlType
     *            int jdbc定义的sql类型常量
     * @return boolean true是日期类型
     * @author shilei
     */
    public static boolean isDate(int sqlType) {
        return 91 == sqlType;
    }

    /**
     * 通过jdbc定义的sql类型将值转换为对应的对象返回。
     * 
     * @param value
     *            String 字符形式的值
     * @param type
     *            Integer jdbc定义的sql类型常量
     * @return Object 转换后的对象。
     * @author shilei
     */
    public static Object getObjectBySqlType(String value, Integer type) {
        int columnSqlType = type.intValue();
        if (2004 == columnSqlType) {
            return value.getBytes();
        }
        if (2005 == columnSqlType) {
            return value;
        }
        if ((isTime(columnSqlType)) || (isDate(columnSqlType))) {
            return DateUtils.getDate(value);
        }
        if (JdbcUtils.isNumeric(columnSqlType)) {
            return Integer.valueOf(value);
        }
        return value;
    }

    /**
     * 根据传入的类型名称返回对应的java类型
     * 
     * @param type
     *            String
     *            传入的类型名称String，Short，Integer，Long，Double，Float，Byte，Char，Character，Boolean，Date，Time，DateTime，Object
     *            short，int，long，double，float，byte，char，boolean中的一个
     * @return Class<?> 对应的java类
     * @author shilei
     */
    public static Class<?> getJavaClassInner(String type) {
        if (type.equals("String")) {
            return String.class;
        }
        if (type.equals("Short")) {
            return Short.class;
        }
        if (type.equals("Integer")) {
            return Integer.class;
        }
        if (type.equals("Long")) {
            return Long.class;
        }
        if (type.equals("Double")) {
            return Double.class;
        }
        if (type.equals("Float")) {
            return Float.class;
        }
        if (type.equals("Byte")) {
            return Byte.class;
        }
        if ((type.equals("Char")) || (type.equals("Character"))) {
            return Character.class;
        }
        if (type.equals("Boolean")) {
            return Boolean.class;
        }
        if (type.equals("Date")) {
            return java.sql.Date.class;
        }
        if (type.equals("Time")) {
            return Time.class;
        }
        if (type.equals("DateTime")) {
            return Timestamp.class;
        }
        if (type.equals("Object")) {
            return Object.class;
        }
        if (type.equals("short")) {
            return Short.TYPE;
        }
        if (type.equals("int")) {
            return Integer.TYPE;
        }
        if (type.equals("long")) {
            return Long.TYPE;
        }
        if (type.equals("double")) {
            return Double.TYPE;
        }
        if (type.equals("float")) {
            return Float.TYPE;
        }
        if (type.equals("byte")) {
            return Byte.TYPE;
        }
        if (type.equals("char")) {
            return Character.TYPE;
        }
        if (type.equals("boolean")) {
            return Boolean.TYPE;
        }
        try {
            return loadClass(type);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 根据传入的类型名称返回对应的java类型
     * 
     * @param type
     *            String
     *            传入的类型名称String，Short，Integer，Long，Double，Float，Byte，Char，Character，Boolean，Date，Time，DateTime，Object
     *            short，int，long，double，float，byte，char，boolean中的一个
     * @return Class<?> 对应的java类
     * @author shilei
     */
    public static Class<?> getJavaClass(String type) {
        int index = type.indexOf("[]");
        if (index < 0) {
            return getJavaClassInner(type);
        }
        String arrayString = "[";
        String baseType = type.substring(0, index);
        while ((index = type.indexOf("[]", index + 2)) >= 0) {
            arrayString = arrayString + "[";
        }
        Class<?> baseClass = getJavaClassInner(baseType);
        try {
            String baseName = "";
            if (!baseClass.isPrimitive()) {
                return loadClass(arrayString + "L" + baseClass.getName() + ";");
            }
            if ((baseClass.equals(Boolean.class)) || (baseClass.equals(Boolean.TYPE))) {
                baseName = "Z";
            } else if ((baseClass.equals(Byte.class)) || (baseClass.equals(Byte.TYPE))) {
                baseName = "B";
            } else if ((baseClass.equals(Character.class)) || (baseClass.equals(Character.TYPE))) {
                baseName = "C";
            } else if ((baseClass.equals(Double.class)) || (baseClass.equals(Double.TYPE))) {
                baseName = "D";
            } else if ((baseClass.equals(Float.class)) || (baseClass.equals(Float.TYPE))) {
                baseName = "F";
            } else if ((baseClass.equals(Integer.class)) || (baseClass.equals(Integer.TYPE))) {
                baseName = "I";
            } else if ((baseClass.equals(Long.class)) || (baseClass.equals(Long.TYPE))) {
                baseName = "J";
            } else if ((baseClass.equals(Short.class)) || (baseClass.equals(Short.TYPE))) {
                baseName = "S";
            }
            return loadClass(arrayString + baseName);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 通过当前线程的类加载器加载指定的类
     * 
     * @param name
     *            String 指定的类
     * @return Class<?> 加载的类
     * @throws ClassNotFoundException
     * @author shilei
     */
    public static Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> result = null;
        try {
            result = Thread.currentThread().getContextClassLoader().loadClass(name);
        } catch (ClassNotFoundException localClassNotFoundException) {
        }
        if (result == null) {
            result = Class.forName(name);
        }
        return result;
    }

    /**
     * 判断字符是否为小写
     * 
     * @param chr
     *            char
     * @return boolean true为小写
     * @author shilei
     */
    public static boolean isLow(char chr) {
        if ((chr > 'Z') || (chr < 'A')) {
            return true;
        }
        return false;
    }

    /**
     * 判断字符是否为大写
     * 
     * @param chr
     *            char
     * @return boolean true为大写
     * @author shilei
     */
    public static boolean isUpper(char chr) {
        if ((chr <= 'Z') && (chr >= 'A')) {
            return true;
        }
        return false;
    }

    /**
     * 判断参数字符串第二个字符是否为大写
     * 
     * @param chrs
     *            String 字符串
     * @return boolean true
     * @author shilei
     */
    public static boolean isUpperSecondChar(String chrs) {
        char chr = chrs.charAt(1);
        return isUpper(chr);
    }

    /**
     * 将驼峰名称转换为下划线名称 如abcDef->abc_def
     * 
     * @param name
     *            String 驼峰名称
     * @return String 返回带下划线的名称
     * @author shilei
     */
    public static String underscoreName(String name) {
        StringBuilder result = new StringBuilder();
        if ((name != null) && (name.length() > 0)) {
            int idx = 0;
            if (isUpperSecondChar(name)) {
                result.append(name.substring(0, 2));
                idx = 2;
            } else {
                result.append(name.substring(0, 1).toLowerCase());
                idx = 1;
            }
            for (int i = idx; i < name.length(); i++) {
                String s = name.substring(i, i + 1);
                if (s.equals(s.toUpperCase())) {
                    result.append("_");
                    result.append(s.toLowerCase());
                } else {
                    result.append(s);
                }
            }
        }
        return result.toString();
    }

    /**
     * 将下划线名称转化为驼峰名称 如abc_def->abcDef
     * 
     * @param columnName
     *            String 下划线名称
     * @return String 返回带驼峰名称
     * @author shilei
     */
    public static String derscoreColumnName(String columnName) {
        String[] colnames = columnName.split("_");
        StringBuilder result = new StringBuilder();
        result.append(colnames[0].trim().toLowerCase());
        if (colnames.length > 1) {
            for (int idx = 1; idx < colnames.length; idx++) {
                result.append(colnames[idx].substring(0, 1).toUpperCase());
                result.append(colnames[idx].substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /**
     * 将参数打印出来
     * 
     * @param obj
     *            打印对象
     * @return String 打印的字符串
     * @author shilei
     */
    public static String printObject(Object obj) {
        if (obj == null) {
            return "";
        }
        if (((obj instanceof String)) || ((obj instanceof Number))) {
            return obj.toString();
        }
        if ((obj instanceof java.util.Date)) {
            return DateUtils.formatPattern((java.util.Date) obj,
                    DateUtils.PATTERN_LONG_DATETIME_DEFAULT);
        }
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            if (length <= 0) {
                return "[]";
            }
            StringBuffer strBuf = new StringBuffer("[");
            for (int i = 0; i < length; i++) {
                Object inObj = Array.get(obj, i);
                String eleStr = printObject(inObj);
                strBuf.append(eleStr).append(",");
            }
            return strBuf.subSequence(0, strBuf.length() - 1) + "]";
        }
        Object objIn;
        if ((obj instanceof Collection)) {
            Collection<?> collectionObj = (Collection<?>) obj;
            if (collectionObj.size() <= 0) {
                return "[]";
            }
            StringBuffer strBuf = new StringBuffer("[");
            for (Iterator<?> it = collectionObj.iterator(); it.hasNext();) {
                objIn = it.next();
                String eleStr = printObject(objIn);
                strBuf.append(eleStr).append(",");
            }
            return strBuf.subSequence(0, strBuf.length() - 1) + "]";
        }
        if ((obj instanceof Map)) {
            Map<?, ?> mapObje = (Map<?, ?>) obj;
            if (mapObje.size() <= 0) {
                return "{}";
            }
            StringBuffer strBuf = new StringBuffer("{");
            for (objIn = mapObje.entrySet().iterator(); ((Iterator<?>) objIn).hasNext();) {
                Map.Entry<?, ?> entry = (Map.Entry<?, ?>) ((Iterator<?>) objIn).next();
                Object key = entry.getKey();
                Object value = entry.getValue();
                String eleStrKey = printObject(key);
                String eleStrValue = printObject(value);
                strBuf.append(eleStrKey).append(":").append(eleStrValue).append(",");
            }
            return strBuf.subSequence(0, strBuf.length() - 1) + "}";
        }
        return obj.toString();
    }

    /**
     * 将异常堆栈转换为String字符串
     * 
     * @param thr
     *            异常
     * @return String 异常堆栈的字符串表示
     * @author shilei
     */
    public static String printException(Throwable thr) {
        if (thr == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            thr.printStackTrace(new PrintStream(baos));
            baos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (baos != null)
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return baos.toString();
    }

    /**
     * 替换字符串
     * 
     * @param str
     *            原字符串
     * @param pattern
     *            匹配字符，即要替换的字符的正则表达式
     * @param replace
     *            替换字符，即将要替换成的字符
     * @return String 替换后的字符串。 如果 pattern 为空则默认为空格、换行、制表 如果replace 为空则为""字符串
     * @author shilei
     */
    public static String replaceSpecialtyStr(String str, String pattern, String replace) {
        if (isNullEmpty(pattern))
            pattern = "\t|\r|\n";// 去除字符串中空格、换行、制表
        if (isNullEmpty(replace))
            replace = "";
        return Pattern.compile(pattern).matcher(str).replaceAll(replace);
    }
    
    
    /**
     * Pad a string. This method is used for the SQL function RPAD and LPAD.
     *
     * @param string the original string
     * @param n the target length
     * @param padding the padding string
     * @param right true if the padding should be appended at the end
     * @return the padded string
     */
    public static String pad(String string, int n, String padding, boolean right) {
        if (n < 0) {
            n = 0;
        }
        if (n < string.length()) {
            return string.substring(0, n);
        } else if (n == string.length()) {
            return string;
        }
        char paddingChar;
        if (padding == null || padding.length() == 0) {
            paddingChar = ' ';
        } else {
            paddingChar = padding.charAt(0);
        }
        StringBuilder buff = new StringBuilder(n);
        n -= string.length();
        if (right) {
            buff.append(string);
        }
        for (int i = 0; i < n; i++) {
            buff.append(paddingChar);
        }
        if (!right) {
            buff.append(string);
        }
        return buff.toString();
    }
}
