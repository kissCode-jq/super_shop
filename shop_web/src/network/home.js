import { request } from './request'

export function getMenuInfo() {
    return request({
        url: 'getMenu'
    })
}