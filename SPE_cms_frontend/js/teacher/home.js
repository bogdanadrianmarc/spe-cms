const default_page_teacher = {
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
        <p>Nostrud coniunctione est proident, laborum fore incurreret sed aute fabulas a quem noster, admodum elit singulis.</p>
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
