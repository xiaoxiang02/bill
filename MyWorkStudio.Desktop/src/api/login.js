import request from '@/utils/request'

const baseURL = 'http://localhost:8088';

//登录
export function login(data) {
    return request({
        url: '/api/login',
        method: 'post',
        data: data
    })
}