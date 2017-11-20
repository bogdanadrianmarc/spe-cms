const single_project = {
  props: ['id'],
  data: function(){
    return {
      project: {
        tags: ""
      }
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
      },
      error: function (error) {
        console.log(error);
      }
    });
  },
  template: `
  <div class = "single-project">
    <!-- Move tagColors to global scope -->
    <div class = "single-project-header">
      <div>
        <span class = "tag-container" v-for = "(tag,index) in project.tags.split(',')">
          <!--            <div class = "tag" v-bind:class="tagColors[index]"> {{tag}} </div> -->
          <span class = "tag orange">{{tag}}</span>
        </span>
      </div>
      <h1>Project #{{project.id}} : {{project.title}}</h1>
      <div class = "project-list-item-applicants">
        No. of applicants <span class = "applicants"> {{ project.applicantsNr }}</span>
      </div>
    </div>
    <p>{{project.content}}</p>
    <div class = "single-project-CTA">
      <h1>Like this project?<br /> Choose it!</h1>
      <router-link to="/dashboard">
      <div class = "CTA-btn">Go to dashboard</div>
    </router-link>
  </div>
</div>`
}