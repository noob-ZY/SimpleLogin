# SimpleLogin

用于小项目简单登录与鉴权，暂时简单实现，不考虑复杂情况(如缓存鉴定同时登录数量，分布式缓存使用等等)

## 工具类操作
* login(User) 登录(调用时去除用户敏感信息)
* logout() 登出
* getToken() 获取当前登录信息token
* getCurrentUser() 获取当前登录用户

## 注解方式对接口校验当前用户对应角色

## 配置

* 登录信息临时保存方式
  * simpleLogin.storageType
    * session
    * cookieToken
    * storageToken(需要调用接口返回token保存用于后续调用) 还没写完实现，毕竟这要先后端代码互相配合的
* JWT配置
  * simpleLogin.JWT
    * Secret
    * ISSUER


* 继承JWTUtil指定泛型并注册至容器，用于用户信息对象与Json文本转换
~~~java
public abstract class JWTUtil<E extends User> {}
~~~
