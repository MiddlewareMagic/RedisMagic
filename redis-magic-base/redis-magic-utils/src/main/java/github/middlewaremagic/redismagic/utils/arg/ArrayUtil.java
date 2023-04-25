package github.middlewaremagic.redismagic.utils.arg;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * @program: `redisRewrite
 * @description: 数组工具类
 * @author: gaoxiang
 * @email: 630268696@qq.com
 * @create: 2023-04-25 20:20
 **/
public class ArrayUtil {

    private ArrayUtil() {
    }

    /**
     * 空字符串列表
     * @since 0.1.49
     */
    public static final String[] STRING_EMPTY = new String[0];

    /**
     * 数组是否为空
     *
     * @param objects 数组对象
     * @return 是否为空
     */
    public static boolean isEmpty(Object[] objects) {
        if (null == objects
                || objects.length <= 0) {
            return true;
        }

        return false;
    }

    /**
     * 数组是否不为空
     *
     * @param objects 数组对象
     * @return 是否为空
     */
    public static boolean isNotEmpty(Object[] objects) {
        return !isEmpty(objects);
    }

    /**
     * 数组转换为列表
     *
     * @param objects 数组
     * @param <T> 泛型
     * @return 列表
     * @since 0.1.41
     */
    public static <T> List<T> toList(final T[] objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return Collections.emptyList();
        }

        List<T> objectList = new ArrayList<>(objects.length);
        objectList.addAll(Guavas.newArrayList(objects));
        return objectList;
    }

    /**
     * 转换为数组
     * @param objectList 集合
     * @return 对象数组
     */
    public static Object[] toArray(final List<?> objectList) {
        if (CollectionUtil.isEmpty(objectList)) {
            return new Object[0];
        }

        Object[] objects = new Object[objectList.size()];
        for (int i = 0; i < objects.length; i++) {
            objects[i] = objectList.get(i);
        }
        return objects;
    }

    /**
     * 数组的并集
     * @param values 集合
     * @param others 其他数组信息
     * @return 对象数组
     * @param <K> 泛型
     * @since 0.1.32
     */
    @SuppressWarnings("unchecked")
    public static <K> K[] union(final K[] values, final K ... others) {
        if(ArrayUtil.isEmpty(values)) {
            return others;
        }
        if (ArrayUtil.isEmpty(others)) {
            return values;
        }

        K[] resultArray = (K[]) new Object[others.length];

        // 从 values 的下标开始添加
        System.arraycopy(others, 0, resultArray, values.length, others.length);
        return resultArray;
    }

    /**
     * 是否包含数据
     * @param array 数组信息
     * @param objectToFind 待发现的对象
     * @return 是否包含
     * @since 0.0.1
     */
    public static boolean contains(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind) != -1;
    }

    /**
     * 是否不包含数据
     * @param array 数组信息
     * @param objectToFind 待发现的对象
     * @return 是否不包含
     * @since 0.0.1
     */
    public static boolean notContains(Object[] array, Object objectToFind) {
        return !contains(array, objectToFind);
    }

    /**
     * 查询对应元素的下标
     * @param array 数组信息
     * @param objectToFind 待发现的对象
     * @return 下标
     * @since 0.0.1
     */
    public static int indexOf(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0);
    }

    /**
     * 查询对应元素的下标
     * @param array 数组信息
     * @param objectToFind 待发现的对象
     * @param startIndex 开始下标
     * @return 下标
     * @since 0.0.1
     */
    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return -1;
        } else {
            if (startIndex < 0) {
                startIndex = 0;
            }

            int i;
            if (objectToFind == null) {
                for (i = startIndex; i < array.length; ++i) {
                    if (array[i] == null) {
                        return i;
                    }
                }
            } else if (array.getClass().getComponentType().isInstance(objectToFind)) {
                for (i = startIndex; i < array.length; ++i) {
                    if (objectToFind.equals(array[i])) {
                        return i;
                    }
                }
            }

            return -1;
        }
    }

    /**
     * 数组转列表
     * （1）如果为空，则直接转为空。
     * @param array 数组
     * @param <E> 泛型原型
     * @return 列表
     * @since 0.1.6
     */
    public static <E> List<E> arrayToList(final E... array) {
        if(ArrayUtil.isEmpty(array)) {
            return Guavas.newArrayList();
        }
        return Guavas.newArrayList(array);
    }

    /**
     * 获取开始的下标
     * （1）默认为0
     * （2）如果为负数，或者超过 arrays.length-1，则使用 0
     * （3）正常返回 startIndex
     * @param startIndex 开始下标
     * @param arrays 数组信息
     * @return 尽可能安全的数组范围。如果为空，则返回 0;
     * @since 0.1.14
     */
    public static int getStartIndex(final int startIndex,
                                    final Object[] arrays) {
        if(ArrayUtil.isEmpty(arrays)) {
            return 0;
        }
        if(startIndex < 0
                || startIndex > arrays.length-1) {
            return 0;
        }

        return startIndex;
    }

    /**
     * 获取开始的下标
     * （1）默认为0
     * （2）如果为负数，或者超过 arrays.length-1，则使用 arrays.length-1
     * （3）正常返回 endIndex
     * @param endIndex 结束下标
     * @param arrays 数组信息
     * @return 尽可能安全的数组范围。如果为空，则返回 0;
     * @since 0.1.14
     */
    public static int getEndIndex(final int endIndex,
                                  final Object[] arrays) {
        if(ArrayUtil.isEmpty(arrays)) {
            return 0;
        }
        final int maxIndex = arrays.length-1;
        if(endIndex < 0
                || endIndex > maxIndex) {
            return maxIndex;
        }

        return endIndex;
    }

    /**
     * 找到第一个不为 null 的元素
     *
     * @param objects 对象
     * @return 不为 null 的元素
     * @since 0.1.18
     */
    public static Optional<Object> firstNotNullElem(Object[] objects) {
        if (ArrayUtil.isEmpty(objects)) {
            return Optional.empty();
        }

        for (Object elem : objects) {
            if (ObjectUtil.isNotNull(elem)) {
                return Optional.of(elem);
            }
        }
        return Optional.empty();
    }

    /**
     * 返回数组信息
     * @param objects 数组
     * @return 结果列表
     * @since 0.1.20
     */
    public static Object[] newArray(Object ... objects) {
        return objects;
    }

    /**
     * 执行平移操作
     * 1. 数组判断空。如果为空，直接返回空
     * 2. 添加偏移量的 mod
     * @param array 原始数组
     * @param offset 偏移量 任意整数。
     * @return 结果
     * @since 0.1.66
     */
    public static Object[] shift(final Object[] array, final int offset) {
        if(ArrayUtil.isEmpty(array)) {
            return array;
        }

        final int arrayLength = array.length;
        int actualOffset = offset;
        if(actualOffset < 0) {
            actualOffset += arrayLength;
        }

        Object[] newArray = new Object[arrayLength];
        for(int i = 0; i < arrayLength; i++) {
            int realIndex = (i+actualOffset) % arrayLength;
            newArray[i] = array[realIndex];
        }

        return newArray;
    }
}
