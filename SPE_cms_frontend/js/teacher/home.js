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
        <p>Welcome to the Software Project Engineering unit webpage.
        Here you can view students' choices, assign them to groups
        and later on assess their work. So what are you waiting for?
        Start looking at what projects are on offer
        by clicking the button.</p>
        <router-link to="students">
          <button>Students</button>
        </router-link>
        <router-link to="projects">
          <button class = "right">Projects</button>
        </router-link>
      </div>
    </div>
  </div>`
};
