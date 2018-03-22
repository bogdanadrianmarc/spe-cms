const default_page = {
  data: function () {
    return {
      username: null,
      password: null
    }
  },
  created: function(){
  },
  methods: {
    getLoginDetails: function() {
      var username = document.getElementById("firstname").value;
      var password = document.getElementById("password").value;
      console.log(username, password);
    }
  },
  template: `
  <div class = "default-page">
    <div class="hero">
      <div class="tint"></div>
      <div class="text">
        <h1>Software Product Engineering</h1>
        <form id="login" onsubmit="return false;">
        <input type="text" id="firstname" placeholder="Username" />
        <input type="password" id="password" placeholder="Password" />
        </form>
        <button v-on:click="getLoginDetails()">Log in</button>
      </div>
    </div>
  </div>`
};
