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
   checkEmpty(username, password, email){
     return username == "" || password == "" || email == "";
   },
   isTypeCorrect: function(element){
     return this.type === element;
   },
   register: function (){
     let self = this;
     this.type = document.getElementById('user-type').value;
     var user = document.getElementById('user').value;
     var pass = document.getElementById('pass').value;
     var confirm_pass = document.getElementById('pass-confirm').value;
     var email = document.getElementById('email').value;
     if (this.checkEmpty(user, pass, email)){
       swal("Please fill in all fields.", {dangerMode: true});
     }
     else if (pass != confirm_pass ){
       console.log(pass);
       console.log(confirm_pass);
       swal("Passwords don't match.", {dangerMode: true});
     }
     else if (!this.types.some(this.isTypeCorrect)){
       swal("Choose an user from the dropdown menu.", {dangerMode: true});
     }
     else {
       this.attributes = user + ";"
                       + pass + ";"
                       + email ;
       $.ajax({
         url: 'http://localhost:8080/register',
         method: 'POST',
         data: {
           type: this.type,
           attributes: this.attributes
         },
         success: function(data){
           swal("You've registered successfully!", {icon: "success"});
         },
         error: function(error){
           console.log(error);
         }
       });
     }
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
          <h3>*Pick a fancy username</h3>
            <input type="text" id="user" placeholder="Username" />
          <h3>*Come up with a strong password</h3>
            <input type="password" id="pass" placeholder="Password" />
            <input type="password" id="pass-confirm" placeholder="Confirm Password" />
          <h3>*Enter your email address</h3>
            <input type="email" id="email" placeholder="Email" />
          <h3>*I'm a:</h3>
            <input list="type" id="user-type" placeholder="Choose your type"/>
            <datalist id="type">
              <option value = "student"></option>
              <option value = "teacher"></option>
              <option value = "client"></option>
            </datalist>
          <h4>* All fields marked with this symbol are required.</h4>
          <button v-on:click = "register()">Register</button>
        </form>
        </div>
      </div>
  </div>`
};
