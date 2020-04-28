import axios from 'axios'

// 创建axios实例
const Axios = axios.create({
    baseURL: 'localhost:8000', // api的base_url  
    timeout: 100000, // 请求超时时间 10s
    withCredentials: true, // 跨域携带cookie
})

//请求拦截器
Axios.interceptors.request.use(
    config=> {
        // 每次请求带上token和用户编号
        // if (store.getters.token) {
        // config.headers['Token'] = getToken()
        // config.headers['Authorization'] = store.getters.userId
        // }
        config.headers['Content-Type'] = 'application/json;charset=UTF-8'
        return config
    },
    error=> {
        console.log("request.js  请求错误");
        return Promise.reject(error)
    }
)


// 添加响应拦截器 
Axios.interceptors.response.use(
    response => {
        if (response.status==200) {
            return Promise.resolve(response);
        }
        else {
            return Promise.reject(new Error('网络异常，请稍后重试'))
        }
    }
)

export default Axios;