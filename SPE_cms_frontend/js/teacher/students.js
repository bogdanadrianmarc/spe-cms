const students_teacher = {
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

    //getting the students
        $.ajax({
          url: 'http://localhost:8080/students',
          method: 'POST',
          data: {
            login_token: self.$parent.token
          },
          success: function (data) {
            self.studentList = data;
            let i = 0;
            const candidates = ["28755", "55310", "33989", "70123", "24343"];
            const studentNo = ["sl13783", "sc16244", "mt16435", "ma16222", "az16761"];
            for(i = 0; i < self.studentList.length; i++){
              self.studentList[i].candidateId = candidates[i];
              self.studentList[i].studentNo = studentNo[i];
            }
            console.log(self.studentList);
            self.studentListCopy = data;
            self.$parent.loading = false;
          },
          error: function (error) {
            console.log(error);
            self.$parent.loading = false;
          }
        });
  },
  methods: {
    getLink: function(name){
      return "./selections" + name;
    },
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
        <students_list_filters_teacher></students_list_filters_teacher>
        <div id = "students-list">

          <ol id = "usernames" :key = "01">
          <transition-group name="sort-list">
            <li style = "padding-left: 20px;"
              v-for = "student in this.studentList"
              v-bind:students = "student"
              v-bind:key = "student.id"
              :priority = "currPriority">

                <router-link :to ="getLink(student.id)" style = "padding-left: 20px;">
                  <span class = "name">{{student.fullName}}</span>
                  (<span class = "candidate">{{student.candidateId}}</span>)
                </router-link>

            </li>
             </transition-group>
            </ol>

       </div>
     </div>
   </transition>
  </div>`
};
