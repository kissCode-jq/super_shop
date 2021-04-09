import { request } from './request'

export function getAttrListInfo(type) {
    return request({
        url: 'getAttrs',
        params: {
            "type": type
        }
    })
}

//根据ID更新参数
export function upAttrsById(id, attrMemo, type) {
    return request({
        url: 'upAttrsById',
        method: "post",
        params: {
            "id": id,
            "type": type,
            "attrMemo": attrMemo
        }
    })
}

//新增参数信息
export function addAttrs(type, attrName) {
    console.log(type, attrName);
    return request({
        url: 'addAttr',
        method: "post",
        params: {
            "type": type,
            "attrName": attrName
        }
    })
}

//根据ID获取参数数据
export function getAttrById(type, id) {
    return request({
        url: 'getAttrById',
        params: {
            "type": type,
            "id": id
        }
    })
}
//根据ID更新参数
export function upAttrInfoById(type, attr) {
    return request({
        url: 'upAttrInfoById',
        method: "post",
        params: {
            "id": attr.id,
            "type": type,
            "attrName": attr.attrName
        }
    })
}

//根据ID删除数据
export function deleteAttrById(type, id) {
    return request({
        url: 'deleteAttrById',
        params: {
            "type": type,
            "id": id
        }
    })
}