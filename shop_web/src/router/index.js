import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Home from '../components/Home.vue'
import WelCome from '../components/WelCome'
import Users from '../components/user/Users.vue'
import Rights from '../components/power/Rights.vue'
import Roles from '../components/power/Roles.vue'
import Attrs from '../components/power/Attrs.vue'
import Goods from '../components/goods/Goods.vue'

Vue.use(VueRouter)

const routes = [{
        path: '',
        redirect: '/login'
    },
    {
        path: '/login',
        component: Login
    },
    {
        path: '/home',
        component: Home,
        redirect: '/WelCome',
        //子路由
        children: [
            { path: '/WelCome', component: WelCome },
            { path: '/Users', component: Users },
            { path: '/Rights', component: Rights },
            { path: '/Roles', component: Roles },
            { path: '/Attrs', component: Attrs },
            { path: '/Goods', component: Goods }
        ]

    }


]



const router = new VueRouter({

    routes

})

//挂载路由导航守卫
router.beforeEach((to, from, next) => {
    // to 将要访问的路径
    //from 代表从哪个路径跳转而来
    //next是一个函数,表示放行
    //next 放行 net('/Login) 强制跳转
    if (to.path === '/login') return next();
    //获取token
    const tokenStr = window.sessionStorage.getItem("token");
    if (!tokenStr) return next("/login");
    next();
});

export default router