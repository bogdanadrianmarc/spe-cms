const allocation_teacher = {
  props: ['id'],
  data: function(){
    return {
      project: {
        tags: ""
      },
      studentList: [],
      login_token: ""
    }
  },
  created: function(){
    let self = this;
    self.$parent.loading = true;

    //getting the students
        $.ajax({
          url: 'http://localhost:8080/students',
          method: 'POST',
          data: {
            login_token: self.$parent.token
          },
          success: function (data) {
            self.studentList = data;
          },
          error: function (error) {
            console.log(error);
          }
        });
  },
  mounted: function(){
    var el1 = document.getElementById('studentslist');
    let self = this;
    var sortable1 = Sortable.create(el1,{
      animation: 0,
      sort: true,
      onEnd: function (evt) {
        $.notify("Selection saved!", {
          className: "success",
          autoHideDelay: 2000
        })
      }
    });
    var el2 = document.getElementById('drop-box');
    var sortable2 = Sortable.create(el2,{
      animation: 0,
      sort: true,
      onEnd: function(evt) {
        $.notify("Selection saved!", {
          className: "success",
          autoHideDelay: 2000
        })
      }
    })
  },
  methods: {
    showPanel: function(index){
      var panel = document.getElementsByClassName("panel");
      if (panel[index].style.display === "block"){
        panel[index].style.display = "none";
      }
      else {
        panel[index].style.display = "block";
      }
    }
  },
  template: `
  <div id = "allocate">
  <h1>Project X's group allocation</h1>
  <p> The box on the left contains all the students grouped by their preference for this project. <br>
  Start allocating students into teams by dragging them into the box on the right.</p>
    <div id = "students-container">

  <div class = "acc_list">
    <button class="accordion" v-on:click="showPanel(0)"><span>Choice 1</span></button>
    <div class="panel">
    <div id = "studentslist">
        <div
          v-for = "student in this.studentList"
          v-bind:key = "student.id">
          <ol id = "usernames">
            <li><span>{{student.id}}</span></li>
          </ol>
        </div>
   </div>
    </div>

    <button class="accordion" v-on:click="showPanel(1)"><span>Choice 2</span></button>
    <div class="panel">
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>

    <button class="accordion" v-on:click="showPanel(2)"><span>Choice 3</span></button>
    <div class="panel">
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>
  </div>
  </div>

  <div id = "drop-container">
  <div id = "drop-box">
   <h1>Group #1</h1>
  </div>
  </div>
</div>`
};
