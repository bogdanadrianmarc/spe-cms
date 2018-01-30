Vue.component('projects_list_item', {
  props: ['projects'],
  data: function(){
    return {
      tagColors: []
    }
  },
  created: function(){
    this.$parent.$parent.$parent.getTagColors(this.projects,this);
  },
  template: `<div class = "project-list-item">
  <img class = "project-list-item-image" alt = "Project Image" v-bind:src="projects.imgUrl" />
  <div class = "project-list-item-content">
    <div class = "tag-container" v-for = "(tag, index) in projects.tags.split(',')">
      <div class = "tag" v-bind:class="tagColors[index]"> {{tag}} </div>
    </div>
    <h1>  {{ projects.title }}  </h1>
    <!-- truncate string -->
    <p> {{ projects.content.substring(0,500) + "..." }} </p>
    <div class = "project-list-item-applicants">
      No. of applicants <span class = "applicants"> {{ projects.applicantsNr }}</span>
    </div>
    <div class = "project-list-item-id">
      Project #{{ projects.id }}
    </div>
    <div style="clear:both"></div>
    <hr />
    <!-- TODO: Discuss in meeting -->
    <div class = "project-list-item-license">
      <img src = "img/mit-license.png" />
      <p>MIT License</p>
    </div>

    <router-link v-bind:to="projects.projectUrl">
      <div class = "learn-more-btn">Learn More</div>
    </router-link>
  </div>
</div>`
});
