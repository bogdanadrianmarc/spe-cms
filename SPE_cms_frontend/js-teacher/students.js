const students = {
  data: function () {
    return {
      selections: []
    }
  },
  created: function(){
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/selections_id',
      method: 'POST',
      data: {
        id: 0,
        user: "test_student",
        password: "test_student"
      },
      success: function (dataSelections) {
        $.ajax({
          url: 'http://localhost:8080/projects',
          method: 'POST',
          data: {
            user: "test_student",
            password: "test_student"
          },
          success: function (dataProjects) {
            self.selections = dataSelections.map(select => dataProjects[select.projectId].title);
            console.log(JSON.stringify(self.selections));
          },
          error: function (error) {
            console.log(error);
          }
        });
      },
      error: function (error) {
        console.log(error);
      }
    });
  },
  beforeRouteLeave (to, from, next) {
    console.log(this.selections);
    next();
  },
  template: `
  <div id = "students">
    <!-- loader -->
    <transition name = "fade" mode = "out-in">
      <div v-if = "this.$parent.loading" class = "loader" key="loading">
        <loader_spinner></loader_spinner>
      </div>
      <div v-else key="loaded">
        <div id = "students-filter-bar">
        <h1>Student Usernames</h1>
        <span v-if = "this.$parent.sortOrder === -1" class = "wrapperUp"  v-on:click = "this.$parent.sortStudents">
          <button>
            Alphabetic
          </button>
        </span>
        <span v-else class = "wrapperDown"  v-on:click = "this.$parent.sortStudents">
          <button>
            Alphabetic
          </button>
        </span>
        <span class = "wrapperSearch">
          <input name = "title-input" v-on:input = "this.$parent.sortTitle" type = "text" placeholder = "Student Username"></input>
        </span>
      </div>

      <div id = "students-list">
      <ol id = "usernames">
        <li><router-link to ="/selections"><span>sc16913</span></router-link></li>
        <li><router-link to ="/selections"><span>ri16721</span></router-link></li>
        <li><router-link to ="/selections"><span>bv16812</span></router-link></li>
      </ol>
      </div>
    </div>
   </transition>
  </div>`
};
