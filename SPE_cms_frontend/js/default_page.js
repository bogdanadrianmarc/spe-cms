const default_page = {
  data: function () {
    return {
      username: null,
      password: null
    }
  },
  created: function(){
    let self = this;
  },
  methods: {
    clickBTN: function(username, password){
      let self = this;
      console.log(username, password)
    },
    isUndefined: function(item){
      return typeof item === "undefined";
    }
  },
  template: `
  <div class = "default-page">
    <div class="hero">
      <div class="tint"></div>
      <div class="text">
        <h1>Software Product Engineering</h1>
        <p>Nostrud
        coniunctione est proident, laborum fore incurreret sed aute fabulas a quem
        noster, admodum elit singulis. Cernantur labore quid ut legam.</p>
        <router-link to="selections">
          <button>Selections</button>
        </router-link>
        <router-link to="projects">
          <button class = "right">Projects</button>
        </router-link>
      </div>
    </div>
  </div>`
};
