package rxfamily.net;


public interface ResponseCallback<T> {

    /**
     * 请求成功
     * @param t
     */
    void onSuccess(T t);

    /**
     * 网络请求失败结果
     * @param msg
     */
    void onFaild(String msg);
}
