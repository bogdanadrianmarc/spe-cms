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
      $.ajax({
        url: 'http://localhost:8080/projects',
        method: 'POST',
        data: {username, password},
        success: function (data) {
          console.log(data);
        },
        error: function (error) {
          console.log(error);
        }
      });
    },
    isUndefined: function(item){
      return typeof item === "undefined";
    }
  },
  template: `
  <div id = "user-dashboard">

    <div id = "welcome-image">
      <h1>SPE 2018</h1>
      <img src = "img/university-on-film.jpg"/>
    </div>

    <div id = "login-info">
      <label class="control-label" for="username">Username</label>
      <input v-model="username" type="text" id = "username" placeholder="edit me">
      <label class="control-label" for="password">Password</label>
      <input v-model="password" type="text" id = "password" placeholder="edit me">
      <p>Username is: {{ username }}</p>
      <p>Password is: {{ password }}</p>
    </div>

    <div class = "select-project-btn">
      <button v-on:click="clickBTN(username, password)"><span>Log in</span> </button>
    </div>

  </div>`
};
