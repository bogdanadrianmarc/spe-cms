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
      url: 'http://localhost:8080/project?id=' + self.id,
      method: 'GET',
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
      <div class = "single-project-teacher">
        <!-- Move tagColors to global scope -->
        <div class = "single-project-teacher-header">
          <div>
            <span class = "tag-container" v-for = "(tag,index) in project.tags.split(',')">
              <div class = "tag" v-bind:class="tagColors[index]"> {{tag}} </div>
            </span>
          </div>
          <h1>Project #{{project.id}} : {{project.title}}</h1>
          <div class = "project-list-item-applicants-teacher">
            No. of applicants <span class = "applicants"> {{ project.applicantsNr }}</span>
          </div>
        </div>
        <div class = "single-project-teacher-content">
          <div v-for="line in project.lines">{{line}}<br /></div>
        </div>
        <div class = "single-project-choices">
          <h1>Like this project?<br /> Choose it!</h1>
          <router-link to="/dashboard">
          <div class = "CTA-btn">Go to dashboard</div>
        </router-link>
      </div>
    </div>
  </div>
</transition>`
}
