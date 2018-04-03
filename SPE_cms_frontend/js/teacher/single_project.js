const single_project = {
  props: ['id'],
  data: function(){
    return {
      project: {
        tags: ""
      },
      tagColors: [],
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
        login_token: "whvwbwhdfkhu!whvwbwhdfkhu"
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

  },
mounted: function(){
    let self = this;
    // TODO: Remove this setTimeout, demonstration purpose only
    setTimeout(function(){
      self.loading = false;
    },600);

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
            <router-link to = "/allocation"><button class = "CTA-btn" style = "float: right"">Allocate group</button></router-link>
            <h1>Project #{{project.id}} : {{project.title}}</h1>
          </div>
          <div class = "project-list-item-applicants">
            No. of applicants <span class = "applicants"> {{ project.applicantsNr }}</span>
          </div>
        </div>
        <div class = "single-project-content">
          <div v-for="line in project.lines">{{line}}<br /></div>
        </div>
        <div class = "single-project-CTA">
          <h1>Looks good?<br />Go back and browse the others.</h1>
          <router-link to="/projects">
          <div class = "CTA-btn red">All Projects</div>
        </router-link>
      </div>
    </div>
  </div>
</transition>`
}
