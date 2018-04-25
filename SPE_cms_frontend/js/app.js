
const routes = [
  { path: '/student/projects',    component: projects_list_student },
  { path: '/student/home',component: default_page_student},
  { path: '/student/project:id', component: single_project_student, props: true},
  { path: '/student/selections',  component: selections_student},
  { path: '/',            component: home_page},
  { path: '/home',component: home_page},
  { path: '/client/projects',    component: projects_list_client },
  { path: '/client/',            component: default_page_client},
  { path: '/client/home',component: default_page_client},
  { path: '/client/project:id', component: single_project_client, props: true},
  { path: '/client/newProject',  component: form_project_client},
  { path: '/teacher/projects',    component: projects_list_teacher, props: true},
  { path: '/teacher/',            component: default_page_teacher},
  { path: '/teacher/home',component: default_page_teacher},
  { path: '/teacher/project:id', component: single_project_teacher, props: true},
  { path: '/teacher/selections:id',  component: single_selections_teacher, props: true},
  { path: '/teacher/students',    component: students_teacher, props: true},
  { path: '/teacher/allocation',  component: allocation_teacher, props: true}
];

const router = new VueRouter({
  routes, // short for `routes: routes`,
 relative: true
});






const app = new Vue({
  el: '#app',
  router,
  data: function(){
    return{
      loading: false,
      username: "Nobody",
      type: "None",
      token: "None"
    }
  },
  methods:{
    getTagColors: function (projects,self){
      const tagsList   = ["Webapp", "Spring", "PHP", "JS", "React"];
      const colors     = ["orange", "lime", "php-purple", "pink", "baby-blue"];

      const projectTags = projects.tags.split(',');
      for(let i = 0; i < projectTags.length; i++)
      self.tagColors.push(colors[tagsList.indexOf(projectTags[i])]);
    }
  }
});
