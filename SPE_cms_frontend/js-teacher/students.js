const students = {
  data: function () {
    return {
      studentList: [],
      studentListCopy: [],
      login_token: "",
      sortOrder: 1,
      currPriority: 1
    }
  },
  created: function(){
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/login',
      method: 'POST',
      data: {
        type: "teacher",
        username: "test_teacher",
        password: "test_teacher"
      },
      success: function (data) {
        self.login_token = data;
        $.ajax({
          url: 'http://localhost:8080/students',
          method: 'POST',
          data: {
            login_token: data
          },
          success: function (dataStudents) {
            self.studentList = dataStudents;
            self.studentListCopy = dataStudents;
            console.log(dataStudents);
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
  methods: {
    sortStudents: function() {
      if(this.sortOrder === 1)
      this.projectList.sort(this.studentsAscending);
      else
      this.projectList.sort(this.studentsDescending);
      this.sortOrder = this.sortOrder * (-1);
    },
    studentsAscending(a, b){
      return a.studentsNr > b.studentsNr ? 1 : -1;
    },
    studentsDescending(a, b){
      return a.studentsNr < b.studentsNr ? 1 : -1;
    },
    sortTitle: function(){
      const field = document.querySelector("input[name=title-input]").value.replace(/ /g,'').toLowerCase();
      this.studentList = this.studentListCopy.filter(function(student){
        if(field === "")
        return student;
        let title = student.title.replace(/ /g,'');
        title = title.toLowerCase();
        if(title.startsWith(field))
        return project;
      });
    },
    incrementPriority: function(){
      this.currPriority += 1;
    },
    decreasePriority: function(){
      this.currPriority -= 1;
    },
  },
  beforeRouteLeave (to, from, next) {
    console.log(this.studentList);
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
      <students_list_filters></students_list_filters>
      <div id = "students-list">
      <ol id = "usernames">
        <li><router-link to ="/selections"><span>sc16913</span></router-link></li>
        <li><router-link to ="/selections"><span>Other students...</span></router-link></li>
      </ol>
      </div>
    </div>
   </transition>
  </div>`
};
