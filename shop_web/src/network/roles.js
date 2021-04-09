import { request } from './request'

export function getRolesInfo(queryInfo) {
    return request({
        url: 'getRoles',
        params: {
            pageNum: queryInfo.pageNum,
            pageSize: queryInfo.pageSize
        }
    })
}

export function getRolesInfoById(id) {
    return request({
        url: 'getRolesById',
        method: 'get',
        params: {
            'id': id
        }
    })
}



export function addRoles(roles) {
    return request({
        url: 'addRoles',
        method: 'post',
        params: {
            'roleName': roles.roleName,
            'roleDescript': roles.roleDescrit,
        }
    })
}



export function upRoles(roles) {
    return request({
        url: 'upRolesById',
        method: 'post',
        params: {
            'id': roles.id,
            'roleName': roles.roleName,
            'roleDescript': roles.roleDescrit,
        }
    })
}

export function delRolesById(delRolesById) {
    return request({
        url: 'delRolesById',
        method: 'get',
        params: {
            'id': delRolesById
        }
    })
}