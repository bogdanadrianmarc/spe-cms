
const routes = [
  { path: '/projects',    component: projects_list },
  { path: '/dashboard',   component: user_dashboard},
  { path: '/',            component: default_page},
  { path: '/project/:id', component: single_project, props: true}
];

const router = new VueRouter({
  routes // short for `routes: routes`
});






const app = new Vue({
  el: '#app',
  router,
  data: function(){
    return{
      loading: false
    }
  }
});
