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
    <div class = "welcome-image">
      <h2>Dear Lecturer,</h2>
      <h1>Welcome to SPE 2018</h1>
      <p> This is the place where you can view and approve projects posted by clients,<br>
      look at students' choices and get help for allocating them into groups,
      </br> view messages between clients and students in order to help when assessing, <br>
       and give feedback to students.</p>
       <div class = "browse-students">
         <h2>Browse students and their choices.</h2>
         <router-link to = "/students">
           <div class = "browse-btn">Students</div>
         </router-link>
       </div>
       <div class = "browse-projects">
         <h2>Browse and delete projects.</h2>
         <router-link to = "/projects_list">
           <div class = "browse-projects-btn">Projects</div>
         </router-link>
       </div>
    </div>
  </div>`
};
