import Vue from 'vue';
import VueRouter from 'vue-router';

Vue.use(VueRouter)
 
const routes = [
    {
        path: '/',
        component: () => import('../views/login.vue'),
        meta:{
            show:true
        }
    },
    {
        path: '/home',
        component: () => import('../views/home.vue'),
    },
    {
        path: '/statistics',
        component: () => import('../views/statistics.vue'),
    },
    {
        path: '/account',
        component: () => import('../views/account.vue'),
    },
    {
        path: '*',
        component: () => import('../views/404.vue'),
    },
];

const router = new VueRouter({
    routes,
    mode: 'history',
})


export default router;
