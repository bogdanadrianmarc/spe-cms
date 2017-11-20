var ItemsVue = new Vue({
    el: '#app',
    data: {
        projectList: [],
        projectListCopy: [],
        // 1 for descending, -1 for ascending
        sortOrder: 1
    },
    mounted: function () {
        var self = this;
        $.ajax({
            url: 'http://localhost:8080/projects',
            method: 'GET',
            success: function (data) {
                self.projectList = data;
                self.projectListCopy = data;
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
          return a.app_nr > b.app_nr ? 1 : -1;
      },
      applicantsDescending(a, b){
          return a.app_nr < b.app_nr ? 1 : -1;
      },
      sortTags: function(){
        const field = document.querySelector("input[name=tags-input]").value;

        this.projectList = this.projectListCopy.filter(function(project){
          if(field === "")
            return project;
          // split tags by comma, remove whitespaces
          let searchingFor = field.replace(/ /g,'').split(',').map(function (word){
            return word.toUpperCase();
          });
          const searchingIn = project.tags.split(',').map(function (word){
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
    }
  });


