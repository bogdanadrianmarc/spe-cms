
const routes = [
  { path: '/',            component: default_page},
  { path: '/home',component: default_page}
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
  },
  methods:{
  }
});
