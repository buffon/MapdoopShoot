package com.harry.mapreduce.bean;

/**
 * Created with IntelliJ IDEA.
 * User: chenyehui
 * Date: 14-3-12
 * Time: 下午9:13
 * To change this template use File | Settings | File Templates.
 */
public class Html {

    private Head head;
    private Body body;

    private Integer shoot;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public Integer getShoot() {
        return shoot;
    }

    public void setShoot(Integer shoot) {
        this.shoot = shoot;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }
}
