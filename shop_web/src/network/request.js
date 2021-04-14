import axios from 'axios'

export function request(config) {
    // 1.创建axios的实例
    const instance = axios.create({
        baseURL: 'http://127.0.0.1:8888',
        timeout: 5000
    })

    // 2.axios的拦截器
    // 2.1.请求拦截的作用
    instance.interceptors.request.use(config => {
        //1.比如config中一些信息不符合服务器的要求

        //2.比如每次发送请求的时候显示一个请求的图标

        //3.某些网络请求（比如登录）必须携带token
        //
        config.headers.Authorization = window.sessionStorage.getItem("token");
        return config
    }, err => {
        console.log(err)
    })

    // 2.2.响应拦截
    instance.interceptors.response.use(res => {
        return res.data
    }, err => {
        console.log(err)
    })

    // 3.发送真正的网络请求
    return instance(config)
}