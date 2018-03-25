const default_page = {
  data: function () {
    return {
      username: "",
      password: "",
      login_token: "",
      type: ""
    }
  },
  created: function(){
  },
  methods: {
    check: function() {
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
   decryptToken: function(token){
     token_and_type = token.split(";");
     self.login_token = token_and_type[0];
     self.type = token_and_type[1];
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
        <button class = "start" v-on:click = "check()">Log in</button>
      </div>
      <div class = "registration">
        <h2>Not registered yet?</h2>
        <form id="register" onsubmit="return false;">
          <h3>Pick a fancy username*</h3>
          <input type="text" id="username" placeholder="Username" />
          <h3>Come up with a strong password*</h3>
          <input type="password" id="password" placeholder="Password" />
          <input type="password" id="password" placeholder="Confirm Password" />
          <h3>Enter your email address*</h3>
          <input type="email" id="password" placeholder="Email" />
          <h4>* All fields marked with this symbol are required.</h4>
          <button v-on:click = "check()">Register</button>
        </form>
      </div>
    </div>
  </div>`
};
