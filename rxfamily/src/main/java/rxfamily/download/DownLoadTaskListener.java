package rxfamily.download;


import rxfamily.download.db.DownLoadEntity;

/**
 * Created by hly on 16/4/11.
 * email hugh_hly@sina.cn
 */
public interface DownLoadTaskListener {
    /**
     * 开始下载任务
     */
    void onStart();

    /**
     * 取消下载
     * @param downLoadEntity
     */
    void onCancel(DownLoadEntity downLoadEntity);

    /**
     * 下载中
     * @param downSize
     */
    void onDownLoading(long downSize);

    /**
     * 下载完成
     * @param downLoadEntity
     */
    void onCompleted(DownLoadEntity downLoadEntity);

    /**
     * 下载出错
     * @param downLoadEntity
     * @param throwable
     */
    void onError(DownLoadEntity downLoadEntity, Throwable throwable);
}
