Vue.component('projects_list_item', {
  props: ['projects','priority'],
  data: function(){
    return {
      tagColors: [],

    }
  },
  created: function(){
    this.$parent.$parent.$parent.getTagColors(this.projects,this);
  },
  methods: {
    clickBTN: function(index){
      let self = this;
      $.ajax({
        url: 'http://localhost:8080/selection_save',
        method: 'POST',
        data: {
          studentId: "test_student",
          // -1 accounts for difference in zero-indexing on backend and one-indexing on frontend
          projectId: index-1,
          priority: self.priority,
          login_token: "whvwbvwxghqw!whvwbvwxghqw"
        },
        success: function (data) {
          self.$parent.$parent.incrementPriority();
          console.log(data);
        },
        error: function (error) {
          console.log(error);
        }
      });
      //this.currPriority += 1;
    },
    isUndefined: function(item){
      return typeof item === "undefined";
    }
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

    <div class = "select-project-btn">
      <button v-on:click="clickBTN(projects.id)">Choose</button>
    </div>

    <router-link v-bind:to="projects.projectUrl">
      </i><div class = "learn-more-btn">Details</div>
    </router-link>
  </div>
</div>`
});
