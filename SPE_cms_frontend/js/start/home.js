const default_page = {
  data: function () {
    return {
      username: "",
      password: "",
      login_token: "",
      type: "",
      attributes: [],
      types: ["student", "teacher", "client"],
      showDropDown: false
    }
  },
  created: function(){
  },
  methods: {
    login: function() {
      let self = this;
      self.username = document.getElementById('username').value;
      self.password = document.getElementById('password').value;
      $.ajax({
       url: 'http://localhost:8080/login',
       method: 'POST',
       data: {
         username: self.username,
         password: self.password
       },
       success: function(data){
            var token_and_type = data.split(";");
            self.login_token = token_and_type[0];
            self.type = token_and_type[1];
            console.log(self.login_token);
            switch (self.type) {
              case "student":
                window.open("studentView.html", "_self");
                break;
              case "teacher":
                window.open("teacherView.html", "_self");
                break;
              case "client":
                window.open("clientView.html", "_self");
                break;
              default:
                swal({text:"Incorrect details", dangerMode: true});
                break;
            }
      },
      error: function(data){
        swal({text:"Incorrect details", dangerMode: true});
      }
      });
   },
   register: function (){
     let self = this;
     this.attributes = document.getElementById('user').value + ";"
                     + document.getElementById('pass').value + ";"
                     + document.getElementById('email').value;
     console.log(this.attributes);
    $.ajax({
     url: 'http://localhost:8080/register',
     method: 'POST',
     data: {
       type: "student",
       attributes: self.attributes
     },
     success: function(data){
       console.log(data);
       swal({text:"You've registered successfully!"});
     },
     error: function(error){
       console.log(error);
     }
     });
   }
  },
  template: `
  <div class = "default-page">
    <div class="hero">
      <div class="tint"></div>
      <div class="text">
        <h1>Software Product Engineering</h1>
        <form id="login" onsubmit="return false;">
          <input type="text" id="username" placeholder="Username" />
          <input type="password" id="password" placeholder="Password" />
        </form>
        <button class = "start" v-on:click = "login()">Log in</button>
      </div>
      <div class = "registration">
        <h2>Just getting started? Register here.</h2>
        <form id="register" onsubmit="return false;">
          <h3>Pick a fancy username*</h3>
            <input type="text" id="user" placeholder="Username" />
          <h3>Come up with a strong password*</h3>
            <input type="password" id="pass" placeholder="Password" />
            <input type="password" id="pass" placeholder="Confirm Password" />
          <h3>Enter your email address*</h3>
            <input type="email" id="email" placeholder="Email" />

          <div class = "menu">
            <a href="#" v-on:click.prevent="showDropDown=!showDropDown">
            <h3>I'm a:*</h3>
            <i :class="{ 'fa-caret-up': showDropDown, 'fa-caret-down': !showDropDown }" class="fa" aria-hidden="true"></i>
            </a>
            <div v-if="showDropDown">
              <ul class="menu list pl0 pa0 ma0">
                <li v-for="type in types" class="list">
                <a href="#" class="dd-type pointer hover-bg-moon-gray">{{type}}</a>
                </li>
              </ul>
            </div>
          </div>

          <h4>* All fields marked with this symbol are required.</h4>
          <button v-on:click = "register()">Register</button>
        </form>
        </div>
      </div>
  </div>`
};
