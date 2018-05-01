const single_selections_teacher = {
  props: ['id'],
  data: function () {
    return {
      student: {
        id: ""
      },
      selections: [],
      username: ""
    }
  },
  mounted: function(){
    let self = this;
    $.ajax({
      url: 'http://localhost:8080/selections_id',
      method: 'POST',
      data: {
        id: self.id,
        login_token: self.$parent.token
      },
      success: function (dataSelections) {
        $.ajax({
          url: 'http://localhost:8080/projects',
          method: 'POST',
          data: {
            login_token: self.$parent.token
          },
          success: function (dataProjects) {
            this.username = dataSelections.studentId;
            this.selections = dataSelections;
            console.log(this.selections);
            // sort based on priority
            dataSelections = dataSelections.sort(function(a, b){
              return a.priority - b.priority;
            });
            // map project ids to actual project objects
            self.selections = dataSelections.map(select => {
              let returnObj = dataProjects[select.projectId];
              returnObj.priority = dataSelections.filter(obj => obj.projectId === select.projectId)[0].priority;
              return returnObj;
            });
          },
          error: function (error) {
            console.log(error);
          }
        });
      },
      error: function (error) {
        console.log(error);
      }
    });
  },
  template: `
  <div id = "selections-page">
  <h1>{{this.id}}'s selections</h1>
  <p>
  These are {{this.id}}'s project selections.<br />
  The first three projects have the highest priority, but all other submitted choices could be taken into account as well.
  </p>
  <ol id="items">
  <transition-group name="slide" id="items-transition">
  <li v-for = "selection in selections" v-bind:key = "selection.priority" class = "no-hover">
  {{ selection.title }}
  </li>
  </transition-group>
  </ol>
  <router-link to = "/projects">
  </router-link>
  </div>`
};
