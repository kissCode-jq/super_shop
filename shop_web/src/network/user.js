import { request } from './request'

export function getUserInfo(queryInfo) {
    return request({
        url: 'getUser',
        params: {
            'query': queryInfo.query,
            'pageNum': queryInfo.pageNum,
            'pagesize': queryInfo.pagesize
        }
    })
}

export function upUserStatus(user) {
    return request({
        url: 'upUserStatus',
        method: 'put',
        params: {
            'status': user.status,
            'id': user.id
        }
    })
}

export function addUser(user) {
    return request({
        url: 'addUser',
        method: 'post',
        params: {
            'username': user.username,
            'age': user.age,
            'loginname': user.loginname,
            'password': user.password
        }
    })
}

export function getUserBean(id) {
    return request({
        url: 'getUserBean',
        method: 'get',
        params: {
            'id': id
        }
    })
}

export function upUser(user) {
    return request({
        url: 'upUser',
        method: 'post',
        params: {
            'id': user.id,
            'password': user.password,
            'age': user.age,
            'loginName': user.loginname,
        }
    })
}


export function delUser(id) {
    return request({
        url: 'delUser',
        method: 'post',
        params: {
            'id': id
        }
    })
}