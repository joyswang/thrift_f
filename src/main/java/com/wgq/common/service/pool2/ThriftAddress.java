package com.wgq.common.service.pool2;

/**
 * Created by guoweipeng on 16-3-22.
 */
public class ThriftAddress {
    private String ip ;
    private int post ;

    private Class serviceClass ;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPost() {
        return post;
    }

    public void setPost(int post) {
        this.post = post;
    }

    public Class getServiceClass() {
        return serviceClass;
    }

    public void setServiceClass(Class serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThriftAddress that = (ThriftAddress) o;

        if (post != that.post) return false;
        return ip.equals(that.ip);

    }

    @Override
    public int hashCode() {
        int result = ip.hashCode();
        result = 31 * result + post;
        return result;
    }

    @Override
    public String toString() {
        return "ThriftAddress{" +
                "post=" + post +
                ", ip='" + ip + '\'' +
                '}';
    }
}
