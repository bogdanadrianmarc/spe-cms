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
    <div class = "hero">
      <h2>Dear Student,</h2>
      <h1>Welcome to SPE 2018</h1>
      <p> This is the place where you can browse and select projects posted by clients,<br>
      sort your choices in order of preference and submit your decision,</br>
      message your clients as well as your group partners, <br>
      and get feedback from lecturers.</p>
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
