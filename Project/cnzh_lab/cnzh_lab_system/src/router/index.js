import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirct in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    children: [{
      path: 'dashboard',
      name: 'Dashboard',
      component: () => import('@/views/dashboard/index'),
      meta: { title: '首页', icon: 'cert' }
    },
    ]
  },

  {
    path: '/charts',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Charts',
        component: () => import('@/views/charts/index'),
        meta: { title: '统计图表', icon: 'chart' }
      }
    ]
  },

  {
    path: '/labapplylist',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Labapplylist',
        component: () => import('@/views/labapplylist/index'),
        meta: { title: '实验室申请列表', icon: 'assessment' }
      }
    ]
  },

  {
    path: '/applyauditlist',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Applyauditlist',
        component: () => import('@/views/applyauditlist/index'),
        meta: { title: '申请审核管理', icon: 'export' }
      }
    ]
  },
  
  {
    path: '/labmanagement',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Labmanagement',
        component: () => import('@/views/labmanagement/index'),
        meta: { title: '实验室管理', icon: 'nested' }
      }
    ]
  },

  {
    path: '/openprojectmanagement',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Openprojectmanagement',
        component: () => import('@/views/openprojectmanagement/index'),
        meta: { title: '开放实验管理', icon: 'example' }
      }
    ]
  },
  

  {
    path: '/usermanagement',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Usermanagement',
        component: () => import('@/views/usermanagement/index'),
        meta: { title: '用户管理', icon: 'user' }
      }
    ]
  },

  {
    path: '/permissionsmanagement',
    component: Layout,
    children: [{
        path: 'index',
        name: 'Permissionsmanagement',
        component: () => import('@/views/permissionsmanagement/index'),
        meta: { title: '权限管理', icon: 'settings' }
      }
    ]
  },


  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})

