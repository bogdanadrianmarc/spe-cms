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
        <p>Lorem ipsum dolor sit amet conesectetur ceva cuvinte sa fac dracu haidi waaaaai si eu odata doua randuri de text.</p>
        <button>Left</button><button>Right</button>
        </div>
    </div>
       <div class = "browse-students">
         <h2>Browse projects and start deciding.</h2>
         <router-link to = "/projects">
           <div class = "browse-btn">Projects</div>
         </router-link>
       </div>
       <div class = "browse-projects">
         <h2>Manage your preferences.</h2>
         <router-link to = "/selections">
           <div class = "browse-projects-btn">Selections</div>
         </router-link>
       </div>
    </div>
  </div>`
};
