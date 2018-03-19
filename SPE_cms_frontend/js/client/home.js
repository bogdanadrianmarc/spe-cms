const default_page = {
  data: function () {
    return {
      username: null,
      password: null
    }
  },
  created: function(){

  },
  methods: {

  },
  template: `
  <div class = "default-page">
    <div class="hero">
      <div class="tint"></div>
      <div class="text">
        <h1>Software Product Engineering</h1>
        <p>Welcome to the page where you can add projects
        for this unit, edit them and also contact the
        students that will be working for you in the future.
        So what are you waiting for? Start adding projects by
        pressing the button below.</p>
        <router-link to="projects">
          <button>Projects</button>
        </router-link>
        <router-link to="newProject">
          <button class = "right">New Project</button>
        </router-link>
      </div>
    </div>
  </div>`
};
