package com.lung.application.utils.zkutils;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zk工具类
 *
 * @author longzhanpeng longzhanpeng@3vjia.com
 * @since 2019-07-10 13:52
 */
@Slf4j
public class ZKUtils implements Watcher {

    private ZooKeeper zooKeeper;

    /**
     * 超时时间
     *
     * @param null
     * @return
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    private static final int SESSION_TIME_OUT = 2000;

    private CountDownLatch countDownLatch = new CountDownLatch(1);

    /**
     * process
     *
     * @param watchedEvent
     * @return void
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            log.info("Watch received event");
            countDownLatch.countDown();
        }
    }

    /**
     * 连接zookeeper
     *
     * @param host
     * @return void
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public void connectZookeeper(String host) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper(host, SESSION_TIME_OUT, this);
        countDownLatch.await();
        log.info("zookeeper connection success");
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @return java.lang.String
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public String createNode(String path, String data) throws KeeperException, InterruptedException {
        return this.zooKeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取路径下面的所有结点
     *
     * @param path
     * @return java.util.List<java.lang.String>
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren(path, false);
        return children;
    }

    /**
     * 获取结点上面的数据
     *
     * @param path
     * @return java.lang.String
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public String getDate(String path) throws KeeperException, InterruptedException {
        byte[] data = zooKeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        return new String(data);
    }

    /**
     * 设置节点信息
     *
     * @param path
     * @param data
     * @return org.apache.zookeeper.data.Stat
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zooKeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    /**
     * 删除结点
     *
     * @param path
     * @return void
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public void deleteNode(String path) throws InterruptedException, KeeperException {
        zooKeeper.delete(path, -1);
    }

    /**
     * 获取创建时间
     *
     * @param path
     * @return java.lang.String
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public String getCTime(String path) throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists(path, false);
        return String.valueOf(exists.getCtime());
    }

    /**
     * 获取某个路径下孩子的数量
     *
     * @param path
     * @return java.lang.Integer
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException {
        int childenNum = zooKeeper.getChildren(path, false).size();
        return childenNum;
    }

    /**
     * 关闭连接
     *
     * @return void
     * @author longzhanpeng longzhanpeng@3vjia.com
     * @since 2019/7/10
     */
    public void closeConnection() throws InterruptedException {
        if (zooKeeper != null) {
            zooKeeper.close();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        ZKUtils zkUtils = new ZKUtils();
        zkUtils.connectZookeeper("182.254.183.22:2181");
        List<String> children = zkUtils.getChildren("/");
        System.out.println(children);
    }

}