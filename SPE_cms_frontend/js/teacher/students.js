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
    self.$parent.loading = true;
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
          success: function (data) {
            self.studentList = data;
            self.studentListCopy = data;
            self.$parent.loading = false;
          },
          error: function (error) {
            console.log(error);
            self.$parent.loading = false;
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
      this.studentList.sort(this.studentsAscending);
      else
      this.studentList.sort(this.studentsDescending);
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
        let title = student.id.replace(/ /g,'');
        title = title.toLowerCase();
        if(title.startsWith(field))
        return student;
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
      <transition-group name="sort-list">
        <div
          v-for = "student in this.studentList"
          v-bind:key = "student.id"
          :priority = "currPriority">
          <ol id = "usernames">
            <router-link to ="/selections"><li><span>{{student.id}}</span></li></router-link>
          </ol>
        </div>
     </transition-group>
     </div>
     </div>
   </transition>
  </div>`
};
