const single_project_client = {
  props: ['id'],
  data: function(){
    return {
      project: {
        tags: ""
      },
      priority: 1,
      tagColors: [],
      buttonText: "Select Project",
      loading: true
    }
  },
  created: function(){
    let self = this;
    console.log(self.project.tags);
    $.ajax({
      url: 'http://localhost:8080/project_id',
      method: 'POST',
      data: {
        id: self.id,
        login_token: self.$parent.token
      },
      success: function (data) {
        self.project = data;
        self.$parent.getTagColors(self.project,self);
        self.project.lines = self.project.content.split('\n');
      },
      error: function (error) {
        console.log(error);
      }
    });
    $.ajax({
      url: 'http://localhost:8080/selections_id',
      method: 'POST',
      data: {
        id: "test_student",
        login_token: "whvwbvwxghqw!whvwbvwxghqw"
      },
      success: function (dataSelections) {
        self.priority = dataSelections.length +1;
        dataSelections.map(obj => {
          if(obj.projectId === self.id-1){
            self.buttonText = "Forget Selection";
          }
        });
      },
      error: function (error) {
        console.log(error);
      }
    });
  },
mounted: function(){
    let self = this;
    // TODO: Remove this setTimeout, demonstration purpose only
    setTimeout(function(){
      self.loading = false;
    },600);

  },
  methods: {
    deleteProj: function(){
      $.ajax({
        url: 'http://localhost:8080/project_delete',
        method: 'POST',
        data: {
          projectId: this.id,
          login_token: this.$parent.token
        },
        success: function(data){
          console.log(data);
          $.notify("Deleted!", {
            className: "removed",
            autoHideDelay: 1500,
            globalPosition: 'top center'
          });
          router.push('./projects');
        },
        error: function(error){
          console.log(error);
        }
      });

    },
    incrementPriority: function(){
      this.priority += 1;
    },
    decrementPriority: function(){
      this.priority -= 1;
    },
    clickBTN: function(){
      let self = this;
      if(this.buttonText === "Select Project"){
        $.ajax({
          url: 'http://localhost:8080/selection_save',
          method: 'POST',
          data: {
            studentId: "test_student",
            // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
            projectId: self.id-1,
            priority: self.priority,
            login_token: "whvwbvwxghqw!whvwbvwxghqw"
          },
          success: function (data) {
            self.incrementPriority();
            self.buttonText = "Forget Selection";
            console.log(data);
          },
          error: function (error) {
            console.log(error);
          }
        });
      }
      else{
        $.ajax({
          url: 'http://localhost:8080/selection_delete',
          method: 'POST',
          data: {
            studentId: "test_student",
            // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
            projectId: self.id-1,
            login_token: "whvwbvwxghqw!whvwbvwxghqw"
          },
          success: function (data) {
            self.decrementPriority();
            self.buttonText = "Select Project";
            console.log(data);
          },
          error: function (error) {
            console.log(error);
          }
        });
      }
    }
  },
  template: `
  <transition name = "fade" mode = "out-in">
    <div v-if = "loading" class = "loader" key="loading">
      <loader_spinner></loader_spinner>
    </div>
    <div v-else key="loaded">
      <div class = "single-project">
        <!-- Move tagColors to global scope -->
        <div class = "single-project-header">
          <div>
            <span class = "tag-container" v-for = "(tag,index) in project.tags.split(',')">
              <div class = "tag" v-bind:class="tagColors[index]"> {{tag}} </div>
            </span>
          </div>
          <div class = "line">
            <button class = "CTA-btn" v-on:click = "deleteProj()" style = "float: right">Delete Project</button>
            <h1 style = "float:left">Project #{{project.id}} : {{project.title}}</h1>
          </div>
          <div class = "project-list-item-applicants">
            No. of applicants <span class = "applicants"> {{ project.applicantsNr }}</span>
          </div>
        </div>
        <div class = "single-project-content">
          <div v-for="line in project.lines">{{line}}<br /></div>
        </div>
        <div class = "single-project-CTA">
          <h1>This one looks alright?</h1>
          <router-link to="./projects">
          <div class = "CTA-btn red">My Projects</div>
         </router-link>
      </div>
    </div>
  </div>
</transition>`
}
