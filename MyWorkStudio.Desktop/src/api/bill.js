import request from '@/utils/request'

const baseURL = 'http://localhost:8088';

//获取所有账单
export function fetchBills(data) {
    return request({
        url: '/api/bill/',
        method: 'get',
        data: data
    })
}

//获取所有账单用途类别
export function fetchCategories(data) {
    return request({
        url: '/api/bill/category/',
        method: 'get',
        data: data
    })
}

//添加账单
export function addBill(data) {
    return request({
        url: '/api/bill/add',
        method: 'post',
        data: data
    })
}

//编辑账单
export function updateBill(data) {
    return request({
        url: '/api/bill/update/{id}',
        method: 'post',
        data: data
    })
}

//删除账单
export function deleteBill(data) {
    return request({
        url: '/api/bill/delete/{id}',
        method: 'post',
        data: data
    })
}
