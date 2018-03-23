const default_page = {
  data: function () {
    return {
      type: ""
    }
  },
  created: function(){

  },
  methods: {
    check: function() {
      var username = document.getElementById('username').value;
      var password = document.getElementById('password').value;
      if (username == "sc" && password == "hey"){
        type = "student";
      }
      if (username == "ab" && password == "hey"){
        type = "teacher";
      }
      if (username == "cd" && password == "hey"){
        type = "client";
      }

      if (type == "student"){
        window.open("studentView.html", "_self");
      }
      else if (type == "teacher"){
        window.open("teacherView.html", "_self");
      }
      else if (type == "client"){
        window.open("clientView.html", "_self");
      }
      else{
        swal({text:"Incorrect details", dangerMode: true});
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
        <button class = "start" v-on:click = "check()">Log in</button>
      </div>
    </div>
  </div>`
};
