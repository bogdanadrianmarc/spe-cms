Vue.component("navbar", {
  template: `
  <div id = "navigation-bar">
    <router-link to = "/"><img alt = "University of Bristol logo" src = "img/logo-white.png" /></router-link>
    <div class = "navigation-items" v-if = "this.$parent.type == 'student'">
      <router-link to = "./home"><span>Home</span></router-link>
      <router-link to = "./selections"><span>My Selections</span></router-link>
      <router-link to = "./projects"><span class = "navigation-items-selected">Projects</span></router-link>
    </div>
    <div class = "navigation-items" v-else-if = "this.$parent.type == 'client'">
      <router-link to = "./home"><span>Home</span></router-link>
      <router-link to = "./projects"><span>My Projects</span></router-link>
      <router-link to = "./newProject"><span class = "navigation-items-selected">New Project</span></router-link>
    </div>
    <div class = "navigation-items" v-else-if = "this.$parent.type == 'teacher'">
        <router-link to = "./home"><span>Home</span></router-link>
        <router-link to = "./students"><span>Students</span></router-link>
        <router-link to = "./projects"><span class = "navigation-items-selected">Projects</span></router-link>
      </div>
    <div v-else></div>
  </div>
  `
});
