import { request } from './request'

export function getRightsInfo() {
    return request({
        url: 'getRights'
    })
}