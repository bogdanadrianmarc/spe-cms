const projects_list_student = {
  data:function(){
    return {
      projectList: [],
      projectListCopy: [],
      login_token: "",
      // 1 for descending, -1 for ascending
      sortOrder: 1,
      currPriority: 1
    }
  },
  created: function(){
    let self = this;
    self.$parent.loading = true;

    //getting the projects
       $.ajax({
         url: 'http://localhost:8080/projects',
         method: 'POST',
         data: {
           login_token: self.$parent.token
         },
         success: function (data) {
           self.projectList = data;
           self.projectListCopy = data;
           self.$parent.loading = false;
         },
         error: function (error) {
           console.log(error);
           self.$parent.loading = false;
         }
       });

       $.ajax({
         url: 'http://localhost:8080/selections_id',
         method: 'POST',
         data: {
           id: self.$parent.username,
           login_token: self.$parent.token
         },
         success: function (dataSelections) {
           self.currPriority = dataSelections.length +1;
         },
         error: function (error) {
           console.log(error);
         }
       });
  },
  methods: {
    sortApplicants: function() {
      if(this.sortOrder === 1)
      this.projectList.sort(this.applicantsAscending);
      else
      this.projectList.sort(this.applicantsDescending);
      this.sortOrder = this.sortOrder * (-1);
    },
    applicantsAscending(a, b){
      return a.applicantsNr > b.applicantsNr ? 1 : -1;
    },
    applicantsDescending(a, b){
      return a.applicantsNr < b.applicantsNr ? 1 : -1;
    },
    sortTitle: function(){
      const field = document.querySelector("input[name=title-input]").value.replace(/ /g,'').toUpperCase();
      this.projectList = this.projectListCopy.filter(function(project){
        if(field === "")
        return project;
        let title = project.title.replace(/ /g,'');
        title = title.toUpperCase();
        if(title.indexOf(field) != -1)
        return project;
      });
    },
    incrementPriority: function(){
      this.currPriority += 1;
    },
    decreasePriority: function(){
      this.currPriority -= 1;
    },
    sortTags: function(){
      const field = document.querySelector("input[name=tags-input]").value;

      this.projectList = this.projectListCopy.filter(function(project){
        if(field === "")
        return project;
        // split tags by comma, remove whitespaces
        let searchingFor = field.replace(/ /g,'').split(',').map((word) => {
          return word.toUpperCase();
        });
        const searchingIn = project.tags.split(',').map((word) => {
          return word.toUpperCase();
        });

        let notFound = false;
        let notFoundAtAll = true;
        for(let i = 0; i < searchingFor.length; i++){
          notFoundAtAll = true;
          for(let j = 0; j < searchingIn.length; j++){
            if(searchingIn[j].startsWith(searchingFor[i]))
            notFoundAtAll = false;
          }
          if(notFoundAtAll){
            notFound = true;
            break;
          }
        }
        if(!notFound)
        return project;
      });
    }
  },
  template: `
  <div id = "projects-list">
    <!-- loader -->
    <transition name = "fade" mode = "out-in">
      <div v-if = "this.$parent.loading" class = "loader" key="loading">
        <loader_spinner></loader_spinner>
      </div>
      <div v-else key="loaded">
        <projects_list_filters_student></projects_list_filters_student>
        <transition-group name="sort-list">
          <projects_list_item_student
            v-for = "project in this.projectList"
            v-bind:projects = "project"
            v-bind:key = "project.id"
            :priority = "currPriority">
          </projects_list_item_student>
        </transition-group>
      </div>
    </transition>
  </div>`
};
