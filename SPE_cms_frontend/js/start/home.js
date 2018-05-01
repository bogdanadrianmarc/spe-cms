const home_page = {
  data: function () {
    return {
      username: "",
      password: "",
      login_token: "",
      type: "",
      attributes: "",
      types: ["student", "teacher", "client"],
      clientDetails: "",
      showFieldsClient: false,
      showFieldStudent: true
    }
  },
  beforeCreate: function () {
    this.$parent.username = "Nobody";
    this.$parent.type = "None";
    this.$parent.token = "None";
  },
  methods: {
    onChange : function(data){
        var selectedType = document.getElementById('type').value;
        if (selectedType === this.types[2]){
          this.showFieldsClient = true;
          this.showFieldStudent = false;
        }
        else {
          this.showFieldsClient = false;
          this.showFieldStudent = true;
        }
    },
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
            self.$parent.username=self.username;
            self.$parent.type=self.type;
            self.$parent.token = self.login_token;
            switch (self.type) {
              case "student":
                router.push('student/home');
                break;
              case "teacher":
                router.push('teacher/home');
                break;
              case "client":
                router.push('client/home');
                break;
              default:
                swal({text:"Incorrect details", dangerMode: true});
                break;
            }
      },
      error: function(error){
        swal({text:"Incorrect details", dangerMode: true});
      }
      });
   },
   checkDetailsEmpty(array){
     console.log(array.length);
     for (var i=0; i<array.length; i++){
       console.log(array[i]);
       if (array[i] == ""){
         return true;
       }
     }
     return false;
   },
   isTypeCorrect: function(element){
     return this.type === element;
   },
   getDetails: function (){
     this.type = document.getElementById('type').value;
     if (this.type == "student"){
       this.getStudentDetails();
     }
     else {
       this.getClientDetails();
     }
   },
   getClientDetails: function() {
     var user = document.getElementById('user').value;
     var pass = document.getElementById('pass').value;
     this.clientDetails = document.getElementById('orgName').value + ";" + document.getElementById('orgPhone').value + ";" +
     document.getElementById('orgAddress').value + ";" + document.getElementById('persName').value + ";" +
     document.getElementById('persEmail').value + ";" + document.getElementById('persPhone').value;

     this.attributes = user + ";"
                     + pass + ";"
                     + this.clientDetails;
   },
   getStudentDetails: function() {
     var user = document.getElementById('user').value;
     var pass = document.getElementById('pass').value;
     var name = document.getElementById('fullname').value;

     this.attributes = user + ";"
                     + pass + ";"
                     + name;
   },
   register: function (){
      let self = this;
      var pass = document.getElementById('pass').value;
      var confirm_pass = document.getElementById('pass-confirm').value;

     this.getDetails();

     if (this.checkDetailsEmpty(this.attributes.split(";"))){
       swal("Please fill in all fields.", {dangerMode: true});
     }
     else if (pass != confirm_pass){
       swal("Passwords don't match.", {dangerMode: true});
     }
     else if (!this.types.some(this.isTypeCorrect)){
       swal("Choose an user from the dropdown menu.", {dangerMode: true});
     }
     else {
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
    <div class="back">
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
        <form id="register" onsubmit="return false;">
          <h3>Username</h3>
            <input type="text" id="user" placeholder="Pick a username" />
          <div id = "nameField" v-if="this.showFieldStudent">
            <h3>Full name</h3>
            <input type="text" id="fullname" placeholder="Your full name" />
          </div>
          <h3>Password</h3>
            <input type="password" id="pass" placeholder="Create a password" style = "margin-bottom: 5px !important;"/>
            <input type="password" id="pass-confirm" placeholder="Confirm Password" />
          <h3>I'm a:</h3>
            <div class = "dropdown">
            <select id="type" @change="onChange(this)">
              <option disable hidden value = "">Select user type</option>
              <option value = "student">Student</option>
              <option value = "client">Client</option>
            </select>
            </div>
            <div id = "clientFields" v-if="this.showFieldsClient">
              <input type="text" id="orgName" placeholder="Organisation Name" />
              <input type="text" id="orgAddress" placeholder="Organisation Address" />
              <input type="tel" id="orgPhone" placeholder="Organisation Number" />
              <input type="text" id="persName" placeholder="Personal Name" />
              <input type="tel" id="persPhone" placeholder="Personal Number" />
              <input type="text" id="persEmail" placeholder="Personal Email" />
            </div>
          <h4>* All fields are required.</h4>
          <button v-on:click = "register()">Register</button>
        </form>
        </div>
      </div>
  </div>`
};
