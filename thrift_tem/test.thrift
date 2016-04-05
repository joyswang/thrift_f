namespace java com.wgq.thrift.common.service

include "user.thrift"
typedef user.User User

service TestService{
    void first_test();
    bool checkNull(1:string str);
    User getUser();
    void saveUser(1:User user);
}

service ThriftService{
    void sout();
}