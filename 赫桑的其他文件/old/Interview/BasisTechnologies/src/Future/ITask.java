package Future;

import java.util.Map;

/**
 * @Author He Zhu
 * @Date 2022-05-06
 * @Version 0.1
 */
public interface ITask<T, E> {
    T execute(E e, Map<String, Object> params);
}
